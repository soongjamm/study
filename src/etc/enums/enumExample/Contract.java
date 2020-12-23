package etc.enums.enumExample;

public class Contract {

    private long id;
    private String company;
    private double commission;
    private String commissionType;
    private String commissionCutting;

    public Contract() {}

    public Contract(String company, double commission, String commissionType, String commissionCutting) {
        this.company = company;
        this.commission = commission;
        this.commissionType = commissionType;
        this.commissionCutting = commissionCutting;
    }

    public long getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public double getCommission() {
        return commission;
    }

    public String getCommissionType() {
        return commissionType;
    }

    public String getCommissionCutting() {
        return commissionCutting;
    }
}
