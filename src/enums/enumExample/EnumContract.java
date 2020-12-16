package enums.enumExample;

public class EnumContract {

    private Long id;
    private String company;
    private double commission;
    private CommissionType commissionType;
    private CommissionCutting cummissionCutting;

    public enum CommissionTest implements EnumModel {

        HUNDRED("백"),
        TEN("십");

        private String value;

        CommissionTest(String value) {
            this.value = value;
        }

        public String getKey() {
            return name();
        }

        public String getValue() {
            return value;
        }
    }

    public enum CommissionType implements EnumModel {

        PERCENT("퍼센트"),
        MONEY("금액");

        private String value;

        CommissionType(String value) {
            this.value = value;
        }

        public String getKey() {
            return name();
        }

        public String getValue() {
            return value;
        }

    }

    public enum CommissionCutting implements EnumModel {

        ROUND("반올림"),
        CEIL("올림"),
        FLOOR("버림");

        private String value;

        CommissionCutting(String value) {
            this.value = value;
        }

        public String getKey() {
            return name();
        }

        public String getValue() {
            return value;
        }

    }

    public EnumContract() {
    }

    public EnumContract(String company, double commission, CommissionType commissionType, CommissionCutting cummissionCutting) {
        this.company = company;
        this.commission = commission;
        this.commissionType = commissionType;
        this.cummissionCutting = cummissionCutting;
    }

    public Long getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public double getCommission() {
        return commission;
    }

    public CommissionType getCommissionType() {
        return commissionType;
    }

    public CommissionCutting getCummissionCutting() {
        return cummissionCutting;
    }
}
