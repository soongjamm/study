package refactoring_to_patterns.form_template_method;

public class CapitalStrategyRevolver extends CapitalStrategy {
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
