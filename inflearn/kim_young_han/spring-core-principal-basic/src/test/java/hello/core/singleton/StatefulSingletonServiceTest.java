package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class StatefulSingletonServiceTest {

    // 싱글톤 객체가 상태를 가지니 원하지 않는 결과가 나온다.
    @Test
    void statefulServiceTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulSingletonService statefulSingletonService = ac.getBean("statefulSingletonService", StatefulSingletonService.class);

        // A사용자 10000원 주문
        statefulSingletonService.order("userA", 10000);
        // B사용자 20000원 주문
        statefulSingletonService.order("userB", 20000);

        System.out.println("current price = " + statefulSingletonService.getPrice());
        Assertions.assertThat(statefulSingletonService.getPrice()).isEqualTo(20000);

    }

    @Configuration
    static class TestConfig {
        @Bean
        public StatefulSingletonService statefulSingletonService() {
            return new StatefulSingletonService();
        }

    }
}