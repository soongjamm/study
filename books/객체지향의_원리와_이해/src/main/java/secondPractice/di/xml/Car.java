package secondPractice.di.xml;

public class Car {
    private Tire tire;

    public void setTire(Tire tire) {
        this.tire = tire;
    }

    @Override
    public String toString() {
        return "Car{" +
                "tire=" + tire +
                '}';
    }
}
