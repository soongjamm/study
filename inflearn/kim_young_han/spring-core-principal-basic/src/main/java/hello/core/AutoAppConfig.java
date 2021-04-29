package hello.core;

import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // Overriding bean definition for bean 'memoryMemberRepository'  ....
    // 수동 빈이 우선시 된다.
    // spring-boot 에서는 기본적 설정으로 overriding=false 라서 에러를 발생시킨다.
    @Bean(name = "memoryMemberRepository")
    public MemoryMemberRepository memoryMemberRepository() {
        return new MemoryMemberRepository();
    }
}
