package week6;

public class Dollar implements Cloneable {
    String owner;
    int amount;

    public Dollar(String owner, int amount) {
        this.owner = owner;
        this.amount = amount;
    }

    public Object clone() throws CloneNotSupportedException {
        Dollar cloned = (Dollar) super.clone();
        cloned.owner = this.owner.toString();
        return (Object) cloned;
    }

    public Dollar copyDollar() {
        try {
            return (Dollar) clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Dollar) {
            if (amount == ((Dollar) object).amount) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("%d달러입니다.", amount);
    }
}
