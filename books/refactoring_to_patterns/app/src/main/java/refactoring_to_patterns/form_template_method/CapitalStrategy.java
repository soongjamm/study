package refactoring_to_patterns.form_template_method;

import java.util.Date;

import static refactoring_to_patterns.replace_conditional_logic_with_strategy.Loan.*;

public abstract class CapitalStrategy {
    private static final int MILLIS_PER_DAY = 86400000;
    private static final int DAYS_PER_YEAR = 365;

    public abstract double capital(Loan loan);

    public abstract double duration(Loan loan);

    protected double riskFactorFor(Loan loan) {
        return RiskFactor.getFactors().forRating(loan.getRiskRating());
    }

    protected double yearsTo(Date endDate, Loan loan) {
        Date beginDate = (loan.getToday() == null ? loan.getStart() : loan.getToday());
        return ((endDate.getTime() - beginDate.getTime()) / MILLIS_PER_DAY) / DAYS_PER_YEAR;
    }
}
