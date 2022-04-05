package refactoring_to_patterns.replace_conditional_logic_with_strategy;

public class CapitalStrategyAdvisedLine extends CapitalStrategy {
    public double capital(Loan loan) {
        return (loan.getOutstanding() * duration(loan) * riskFactorFor(loan))
                + (loan.unusedRiskAmount() * duration(loan) * unusedRiskFactorFor(loan));
    }

    public double duration(Loan loan) {
        return 0.0;
    }

    private double unusedRiskFactorFor(Loan loan) {
        return Loan.UnusedRiskFactors.getFactors().forRating(loan.getRiskRating());
    }
}
