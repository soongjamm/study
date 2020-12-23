package this_is_the_java.generic;

public class ProductExample {
    public static void run () {
        Product<Box, String> product1 = new Product<>();
        product1.setKind(new Box());
        product1.setModel("골판지박스");

        Box box = product1.getKind();
        String model = product1.getModel();
    }
}
