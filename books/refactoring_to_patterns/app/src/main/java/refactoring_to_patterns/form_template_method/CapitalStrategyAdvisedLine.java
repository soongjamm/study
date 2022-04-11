package refactoring_to_patterns.form_template_method;

public class CapitalStrategyAdvisedLine extends CapitalStrategy {
    public double capital(Loan loan) {
        return loan.getCommitment() * loan.getUnusedPercentage() * duration(loan) * riskFactorFor(loan);
    }

    public double duration(Loan loan) {
        return yearsTo(loan.getExpiry(), loan);
    }
}
