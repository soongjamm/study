package secondPractice.di.xml;

import org.springframework.beans.factory.annotation.Autowired;

public class CarAutowired {

    @Autowired
    private Tire tire;

    @Override
    public String toString() {
        return "Car{" +
                "tire=" + tire +
                '}';
    }
}
