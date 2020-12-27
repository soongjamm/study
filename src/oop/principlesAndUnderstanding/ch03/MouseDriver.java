package oop.principlesAndUnderstanding.ch03;

public class MouseDriver {
    public static void main(String[] args) {
        Mouse mickey = new Mouse();

        mickey.name = "mickey";
        mickey.age = 1;
        mickey.countOfTail = 1;

        mickey.sing();
        mickey = null;
    }
}
