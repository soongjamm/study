package chap07.config;

import chap07.aspect.CacheAspect;
import chap07.aspect.ExeTimeAspect;
import chap07.spring.Calculator;
import chap07.spring.RecCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Advice 순서가 CacheAspect -> ExeTimeAspect -> 실제 대상 객체가 되는데, 그 이유는 단순하게 코드가 작성된 순서때문이다.
 * 만약 아래에 프록시 @Bean 설정 코드들의 위치를 바꿔주면 바뀐 순서대로 적용 된다.
 * CacheAspect에서 proceed()를 호출하면 ExeTimeAspect가 불리고, 여기서 proceed()를 호출하면 실제 대상 객체가 호출되는 것.
 * 두번째 factorial(7)은 조건문에 의해 ExeTimeAspect까지 가지 않는다.
 *
 * @Order로 순서를 지정해줄 수도 있다. (각 Aspect에)
 */

@Configuration
@EnableAspectJAutoProxy
public class AppCtxWithCache {

    @Bean
    public CacheAspect cacheAspect() {return new CacheAspect();}

    @Bean
    public ExeTimeAspect exeTimeCalculator() {
        return new ExeTimeAspect();
    }

    @Bean
    public Calculator calculator() {
        return new RecCalculator();
    }

}
