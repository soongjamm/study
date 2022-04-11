package refactoring_to_patterns.form_template_method;

public class CapitalStrategyRevolver extends CapitalStrategy {
    public double capital(Loan loan) {
        return super.capital(loan) + unusedCapital(loan);
    }

    private double unusedCapital(Loan loan) {
        return loan.unusedRiskAmount() * duration(loan) * unusedRiskFactorFor(loan);
    }

    public double duration(Loan loan) {
        return 0.0;
    }

    @Override
    protected double riskAmountFor(Loan loan) {
        return loan.getOutstanding();
    }

    private double unusedRiskFactorFor(Loan loan) {
        return Loan.UnusedRiskFactors.getFactors().forRating(loan.getRiskRating());
    }
}
