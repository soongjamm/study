package chap07.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

/**
 *  Aspect 구현 클래스
 * @PointCut은 공통 기능 적용대상을 설정
 * @Around는 Around Advice를 설정
 * 공통 기능을 적용하는데 필요한 코드를 구현 한것. -> 이제 설정 클래스로 넘어가서 작성
 *
 * @Order로 순서를 지정해줄 수도 있다. (각 Aspect에)
 */

@Aspect
@Order(1)
public class ExeTimeAspect {

    @Pointcut("execution(public * chap07.spring ..*(..))") // public, 모든 리턴타입, chap07/spring 하위의 모든 클래스, 모든 메소드, 파라미터 0개 이상
    public void publicTarget() {}

    @Around("publicTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        try {
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long finish = System.nanoTime();
            Signature sig = joinPoint.getSignature();
            System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n", // sig.getName() - 메소드 이름, getClass().getSimpleName() - 프록시 대상 클래스명
                    joinPoint.getTarget().getClass().getSimpleName(),
                    sig.getName(), Arrays.toString(joinPoint.getArgs()),
                    (finish-start));
        }
    }
}
