package chap07.config;

import chap07.spring.Calculator;
import chap07.aspect.ExeTimeAspect;
import chap07.spring.RecCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @EnableAspectJAutoProxy
 * - @Aspect 붙인 클래스를 공통 기능으로 적용하려면 설정 클래스에 붙여야 하는 어노테이션.
 * - @Aspect 빈 객체를 찾아서 @Pointcut, @Around 설정을 사용함.
 *
 * - 인터페이스를 상속한 프록시가 아닌, 프록시의 실제 타입 클래스로 생성하고 싶다면 proxyTargetClass 옵션을준다.
 */

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppCtx {

    @Bean
    public ExeTimeAspect exeTimeCalculator() {
        return new ExeTimeAspect();
    }

    @Bean
    public Calculator calculator() {
        return new RecCalculator(); // 원래같으면 이 객체가 리턴되겠지만, 이 객체가 @Asepect의 대상이므로 Proxy 객체가 대신 리턴된다.
    }
}
