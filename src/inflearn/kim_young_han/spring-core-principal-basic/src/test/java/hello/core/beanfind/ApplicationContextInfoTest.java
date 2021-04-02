package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findBean() {
        String[] names = ac.getBeanDefinitionNames();
        for (String name : names) {
            Object bean = ac.getBean(name);
            System.out.println("name = " + name + " object = " + bean);
        }
    }

    @Test
    @DisplayName("모든 빈 출력하기")
    void findApplicationBean() {
        String[] names = ac.getBeanDefinitionNames();
        for (String name : names) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(name);

            // ROLE_APPLICATION - 직접 등록한 애플리케이션 빈
            // ROLE_INFRASTRUCTURE - 스프링이 내부적으로 설정한 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("name = " + name + " object = " + beanDefinition);
            }
        }
    }
}
