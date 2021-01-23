package chap07.aspect;

import org.aspectj.lang.annotation.Pointcut;

/**
 * 공통으로 사용되는 Pointcut은 따로 정의를 해놓고 사용하는 것이 좋다.
 */

public class CommonPointcut {

    @Pointcut("execution(public * chap07.aspect ..*(..))")
    public void commonTarget() {

    }
}
