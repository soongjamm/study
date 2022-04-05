package refactoring_to_patterns.replace_conditional_logic_with_strategy;

public class CapitalStrategyRevolver extends CapitalStrategy {
    public double capital(Loan loan) {
        return loan.getCommitment() * loan.getUnusedPercentage() * duration(loan) * riskFactorFor(loan);
    }

    public double duration(Loan loan) {
        return yearsTo(loan.getExpiry(), loan);
    }
}
