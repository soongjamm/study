package chap07.main;

import chap07.config.AppCtx;
import chap07.spring.Calculator;
import chap07.spring.RecCalculator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * RecCalculator cal = ctx.getBean("calculator", RecCalculator.class); 은 예외가 발생한다. (BeanNotOfRequiredTypeException)
 * 구조가 <Calculator>
 *        |     |
 *  <Proxy17> <RecCalculator>
 * 이렇게 Proxy 타입은 Calculator 인터페이스를 상속받는 타입이다.
 * RecCalculator를 타입으로 해놓으면 Proxy타입을 할당 할 수가 없게 되는것이다.
 * 빈 객체가 인터페이스를 상속하면, 프록시도 인터페이스를 이용해서 생성한다.
 *
 * 인터페이스가 아닌, 프록시의 실제 타입 클래스를 이용하고 싶다면 설정파일에서 설정.
 */

public class MainAspect {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        Calculator cal = ctx.getBean("calculator", Calculator.class);
        RecCalculator cal2 = ctx.getBean("calculator", RecCalculator.class);
        long fiveFact = cal.factorial(5);
        System.out.println("cal.factiorial(5) + " + fiveFact);
        System.out.println(cal.getClass().getName());
        ctx.close();
    }
}
