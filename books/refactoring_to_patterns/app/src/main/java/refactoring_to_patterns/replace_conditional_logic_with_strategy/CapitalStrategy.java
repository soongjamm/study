package refactoring_to_patterns.replace_conditional_logic_with_strategy;

import java.util.Date;
import java.util.Iterator;

import static refactoring_to_patterns.replace_conditional_logic_with_strategy.Loan.*;

public class CapitalStrategy {
    private static final int MILLIS_PER_DAY = 86400000;
    private static final int DAYS_PER_YEAR = 365;

    public double capital(Loan loan) {
        if (loan.getExpiry() == null && loan.getMaturity() != null) {
            return loan.getCommitment() * duration(loan) * riskFactorFor(loan);
        }
        if (loan.getExpiry() != null && loan.getMaturity() == null) {
            if (loan.getUnusedPercentage() != 1.0) {
                return loan.getCommitment() * loan.getUnusedPercentage() * duration(loan) * riskFactorFor(loan);
            } else {
                return (loan.getOutstanding() * duration(loan) * riskFactorFor(loan))
                        + (loan.unusedRiskAmount() * duration(loan) * unusedRiskFactorFor(loan));
            }
        }
        return 0.0;
    }

    public double duration(Loan loan) {
        if (loan.getExpiry() == null && loan.getMaturity() != null) {
            return weightedAverageDuration(loan);
        } else if (loan.getExpiry() != null && loan.getMaturity() == null) {
            return yearsTo(loan.getExpiry(), loan);
        }
        return 0.0;
    }

    private double riskFactorFor(Loan loan) {
        return RiskFactor.getFactors().forRating(loan.getRiskRating());
    }

    private double unusedRiskFactorFor(Loan loan) {
        return UnusedRiskFactors.getFactors().forRating(loan.getRiskRating());
    }

    private double yearsTo(Date endDate, Loan loan) {
        Date beginDate = (loan.getToday() == null ? loan.getStart() : loan.getToday());
        return ((endDate.getTime() - beginDate.getTime()) / MILLIS_PER_DAY) / DAYS_PER_YEAR;
    }

    private double weightedAverageDuration(Loan loan) {
        double duration = 0.0;
        double weightedAverage = 0.0;
        double sumOfPayments = 0.0;
        Iterator loanPayments = loan.getPayments().iterator();
        while (loanPayments.hasNext()) {
            Loan.Payment payment = (Loan.Payment)loanPayments.next();
            sumOfPayments += payment.amount();
            weightedAverage += yearsTo(payment.date(), loan) * payment.amount();

            if (loan.getCommitment() != 0.0) {
                duration = weightedAverage / sumOfPayments;
            }
            return duration;
        }
        return 0;
    }
}
