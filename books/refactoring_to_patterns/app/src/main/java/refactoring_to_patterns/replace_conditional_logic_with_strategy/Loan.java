package refactoring_to_patterns.replace_conditional_logic_with_strategy;

import java.util.Date;
import java.util.Iterator;

public class Loan {
    private static final long MILLIS_PER_DAY = 1;
    private static final long DAYS_PER_YEAR = 1;
    private double outstanding;
    private double commitment;
    private Date expiry;
    private Date maturity;
    private long weightedAverage;
    private Date today;
    private Date start;
    private int riskRating;
    private Iterable<Payment> payments;
    private CapitalStrategy capitalStrategy;

    public static Loan newTermLoan(double commitment, Date start, Date maturity, int riskRating) {
        return new Loan(commitment, commitment, start, null, maturity, riskRating, new CapitalStrategyTermLoan());
    }

    public static Loan newRevolver(double commitment, Date start, Date expiry, int riskRating) {
        return new Loan(commitment, 0, start, expiry, null, riskRating, new CapitalStrategyRevolver());
    }

    public static Loan newAdvisedLine(double commitment, Date start, Date expiry, int riskRating) {
        if (riskRating > 3) return null;
        Loan advisedLine = new Loan(commitment, 0, start, expiry, null, riskRating, new CapitalStrategyAdvisedLine());
        advisedLine.setUnusedPercentage(0.1);
        return advisedLine;
    }

    private void setUnusedPercentage(double v) {

    }

    public Loan(double outstanding, double commitment, Date expiry, Date maturity, Date start, int riskRating, CapitalStrategy capitalStrategy) {
        this.capitalStrategy = capitalStrategy;
        this.outstanding = outstanding;
        this.commitment = commitment;
        this.expiry = expiry;
        this.maturity = maturity;
        this.start = start;
        this.riskRating = riskRating;
    }

    public double capital() { // 대출의 종류를 결정짓고 계산이 진행되는데, 어떤 종류의 대출인지알아보기가 어렵다.
        return capitalStrategy.capital(this);
    }

    public double getUnusedPercentage() {
        return 0;
    }

    public double unusedRiskAmount() {
        return (commitment - outstanding);
    }

    public double duration() {
        return capitalStrategy.duration(this);
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

    public static class Factors {
        public double forRating(Object riskRating) {
            return 0;
        }
    }

    public class Payment implements Iterable<Payment> {

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

    public static class RiskFactor {
        public static Factors getFactors() {
            return null;
        }
    }

    public static class UnusedRiskFactors {
        public static Factors getFactors() {
            return null;
        }
    }

    double getOutstanding() {
        return outstanding;
    }

    double getCommitment() {
        return commitment;
    }

    Date getExpiry() {
        return expiry;
    }

    Date getMaturity() {
        return maturity;
    }

    long getWeightedAverage() {
        return weightedAverage;
    }

    Date getToday() {
        return today;
    }

    Date getStart() {
        return start;
    }

    Object getRiskRating() {
        return riskRating;
    }

    Iterable<Payment> getPayments() {
        return payments;
    }
}
