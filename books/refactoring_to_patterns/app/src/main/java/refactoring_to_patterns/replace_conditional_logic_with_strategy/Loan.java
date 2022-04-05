package refactoring_to_patterns.replace_conditional_logic_with_strategy;

import java.util.Date;
import java.util.Iterator;

public class Loan {
    private static final long MILLIS_PER_DAY = 1;
    private static final long DAYS_PER_YEAR = 1;
    private double outstanding;
    private double commitment;
    private Date expiry;
    private Object maturity;
    private long weightedAverage;
    private Date today;
    private Date start;
    private Object riskRating;
    private Iterable<Payment> payments;

    public double capital() { // 대출의 종류를 결정짓고 계산이 진행되는데, 어떤 종류의 대출인지알아보기가 어렵다.
        if (expiry == null && maturity != null) {
            return commitment * duration() * riskFactor();
        }
        if (expiry != null && maturity == null) {
            if (getUnusedPercentage() != 1.0) {
                return commitment * getUnusedPercentage() * duration() * riskFactor();
            } else {
                return (outstandingRiskAmount() * duration() * riskFactor())
                        + (unusedRiskAmount() * duration() * unusedRiskFactor());
            }
        }
        return 0.0;
    }

    private double getUnusedPercentage() {
        return 0;
    }

    private double outstandingRiskAmount() {
        return outstanding;
    }

    private double unusedRiskAmount() {
        return (commitment - outstanding);
    }

    public double duration() {
        if (expiry == null && maturity != null) {
            return weightedAverageDuration();
        } else if (expiry != null && maturity == null) {
            return yearsTo(expiry);
        }
        return 0.0;
    }

    private double weightedAverageDuration() {
        double duration = 0.0;
        double weightedAverage = 0.0;
        double sumOfPayments = 0.0;
        Iterator loanPayments = payments.iterator();
        while (loanPayments.hasNext()) {
            Payment payment = (Payment)loanPayments.next();
            sumOfPayments += payment.amount();
            this.weightedAverage += yearsTo(payment.date()) * payment.amount();

            if (commitment != 0.0) {
                duration = weightedAverage / sumOfPayments;
            }
            return duration;
        }
        return 0;
    }

    private double yearsTo(Date endDate) {
        Date beginDate = (today == null ? start : today);
        return ((endDate.getTime() - beginDate.getTime()) / MILLIS_PER_DAY) / DAYS_PER_YEAR;
    }

    private double riskFactor() {
        return RiskFactor.getFactors().forRating(riskRating);
    }

    private double unusedRiskFactor() {
        return UnusedRiskFactors.getFactors().forRating(riskRating);
    }

    private static class Factors {
        public double forRating(Object riskRating) {
            return 0;
        }
    }

    private class Payment implements Iterable<Payment> {

        @Override
        public Iterator<Payment> iterator() {
            return null;
        }

        public double amount() {
            return 0;
        }

        public Date date() {
            return null;
        }
    }

    private static class RiskFactor {
        public static Factors getFactors() {
            return null;
        }
    }

    private static class UnusedRiskFactors {
        public static Factors getFactors() {
            return null;
        }
    }
}
