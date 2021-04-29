package secondPractice.aop.pojowithXML;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

public class MyAspect {
    public void before(JoinPoint joinPoint) {
        System.out.println("얼굴인식 : 문을 개방해라~~");
    }
    public void lock(JoinPoint joinPoint) {
        System.out.println("lock~~");
    }
}

