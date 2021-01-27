package messagePractice;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        Printer printer = ctx.getBean(Printer.class);
        printer.print();
    }
}
