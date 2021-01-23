package chap07.main;

import chap07.config.AppCtx;
import chap07.config.AppCtxWithCache;
import chap07.spring.Calculator;
import chap07.spring.RecCalculator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 */

public class MainAspectWithCache {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtxWithCache.class);

        Calculator cal = ctx.getBean("calculator", Calculator.class);
        cal.factorial(7);
        cal.factorial(7);
        cal.factorial(5);
        cal.factorial(5);

        ctx.close();
    }
}
