package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    void lifecycle() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(NetworkConfiguration.class);
        NetworkClient networkClient = ac.getBean("networkClient", NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class NetworkConfiguration {

        @Bean
        public NetworkClient networkClient() {
            System.out.println();
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("my.network.client.com");
            return networkClient;
        }
    }
}
