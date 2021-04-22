package secondPractice.aop.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

@Aspect
public class MyAspect {
    @Before("execution (public void *.run())")
    public void before(JoinPoint joinPoint) {
        System.out.println("얼굴인식 : 문을 개방해라~");
    }

    @After("execution (public void *.run())")
    public void lock(JoinPoint joinPoint) {
        System.out.println("lock the door!");

        System.out.println("----JoinPoint test----");
        System.out.println("tostring - "+joinPoint.toString());
        System.out.println("get args -" +Arrays.toString(joinPoint.getArgs()));
        System.out.println("getKind - "+joinPoint.getKind());
        System.out.println("getThis() - "+ joinPoint.getThis());
        System.out.println("sig() .name - "+ joinPoint.getSignature().getName());
        System.out.println("sig() .toLongString - "+ joinPoint.getSignature().toLongString());
        System.out.println("sig() .toShortString - "+ joinPoint.getSignature().toShortString());
    }
}

