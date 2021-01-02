package ch06.singleton_pattern;

public class Singleton {
    static Singleton singletonObject;

    private Singleton() {}

    static public Singleton getInstance() {
        if (singletonObject == null) {
            singletonObject = new Singleton();
        }

        return singletonObject;
    }
}
