package java8.functional_interface_and_lambda;

public class Greeting {

    private String name;

    public Greeting() {
    }

    public Greeting(String name) {
        this.name = name;
    }

    public void hello() {
        System.out.println("Hello, " + name);
    }

}
