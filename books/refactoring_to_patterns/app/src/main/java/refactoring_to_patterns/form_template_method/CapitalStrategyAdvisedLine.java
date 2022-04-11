package refactoring_to_patterns.form_template_method;

public class CapitalStrategyAdvisedLine extends CapitalStrategy {
    public double capital(Loan loan) {
        return riskAmountFor(loan) * duration(loan) * riskFactorFor(loan);
    }

    protected double riskAmountFor(Loan loan) {
        return loan.getCommitment() * loan.getUnusedPercentage();
    }

    public double duration(Loan loan) {
        return yearsTo(loan.getExpiry(), loan);
    }
}
