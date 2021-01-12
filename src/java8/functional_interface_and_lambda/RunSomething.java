package java8.functional_interface_and_lambda;

@FunctionalInterface
public interface RunSomething {
    int runIt(int number);

    //    int runItSecond(int number);

    static void staticPrint(int number) {
        System.out.println("this is static Print" + number);
    }

    static void staticPrint2(int number, int number2) {
        System.out.println("second static Print" + number + number2);
    }

    static int staticRunIt(int number) {
        return number + 10;
    }

    default int defaultRunIt(int number) {
        return number + 10;
    }
}
