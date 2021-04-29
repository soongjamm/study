package etc.first_class_collection;

public class Pay {
    PayType payType;
    int amount;

    public Pay(PayType payType, int amount) {
        this.payType = payType;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public PayType getPayType() {
        return payType;
    }
}
