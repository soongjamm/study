package chap06.config;

import chap06.spring.Client;
import chap06.spring.Client2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppContext {

    /**
     * 💡 중요한 점은 초기화 메서드가 두 번 불리지 않도록 한다.
     */
    @Bean
    @Scope("prototype")
    public Client client() throws Exception {
        Client client = new Client();
        client.setHost("host");
        client.afterPropertiesSet(); // 구현한 것 자체로 실행되는데, 여기서 한번 더 호출하면 중복 호출이다.
        return client;
    }

    /**
     * Client2 에는 InitilizingBean 과 DisposableBean 인터페이스를 구현하지 않았지만,
     * 다른 메소드를 initMethod 와 destroyMethod 로 지정해주어서 인터페이스를 구현한 것과 같은 효과를 낸다.
     *
     * 메소드들은 파라미터가 없어야함. 있으면 BeanDefinitionValidationException 발생
     * 그리고 메소드들은 당연히 Client2 클래스에 존재하는 메소드임
     */
    @Bean(initMethod = "connect", destroyMethod = "close")
    public Client2 client2() {
        Client2 client = new Client2();
        client.setHost("host");
        return client;
    }


}
