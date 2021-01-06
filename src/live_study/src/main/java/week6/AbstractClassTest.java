package week6;

public class AbstractClassTest {
    abstract class ExampleClass {
        public abstract void ExampleMethod();
    }

    class TestSubClass extends ExampleClass {
        @Override
        public void ExampleMethod() {
            // ...
        }
    }
}
