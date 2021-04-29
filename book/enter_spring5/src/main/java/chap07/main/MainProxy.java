package chap07.main;

import chap07.spring.Calculator;
import chap07.config.AppCtx;
import chap07.spring.ExeTimeCalculator;
import chap07.spring.ImpeCalculator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainProxy {

    public static void main(String[] args) {
        ExeTimeCalculator ttCal1 = new ExeTimeCalculator(new ImpeCalculator());
        System.out.println(ttCal1.factorial(20));

        ExeTimeCalculator ttCal2 = new ExeTimeCalculator(new ImpeCalculator());
        System.out.println(ttCal2.factorial(20));

    }
}
