package hello.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoreApplicationTests {
    // 1. 수동-자동빈 동시 등록때문에 테스트가 깨졌다. (오버라이딩=false 라서)
    //    -> properties 에서 오버라이딩 허용
    // 2. AutoAppConfigTest 에서 @MyIncludeComponent만 읽어들이게 했는데,
    //    왜인지 다 읽어들여서 임의로 생성한 OrderService들이 다른 의존성을 가지고 있지 않다고 NoSuchBeanDefinitionException 터트림
    //    -> basePackage 아래에 있기 때문에 그렇다. exclude 시켜줘서 해결
    @Test
    void contextLoads() {
    }

}
