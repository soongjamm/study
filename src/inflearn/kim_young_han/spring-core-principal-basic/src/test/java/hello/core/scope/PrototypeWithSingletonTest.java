package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

// 프로토타입은 객체 생성, 초기화, 주입까지만 컨테이너가 관리한다. (그래서 종료 콜백 메소드 실행되지 않음.)

// 싱글톤안에서 프로토타입을 주입받을 때, 매번 새로운 프로토타입을 받으려면?
// -> 스프링 컨테이너를 싱글톤안에서 주입받고 사용할 수도 있지만, 과하다.
// ObjectProvider : DL(Dependency Lookup)을 위한 용도. BeanFactory에서도 사용되는 인터페이스다. 이것을 사용한다.
// (ObjectFactory에 기능이 추가된 인터페이스다.) --> 그러나 스프링 의존적.

// 스프링에 의존적이지 않고 자바 표준인 Provider 도 있다.
// 장점 - 심플, 스프링컨테이너 말고 다른 컨테이너에서도 사용 가능. / 단점 - 라이브러리 추가해줘야함.

public class PrototypeWithSingletonTest {

    @Test
    void clientWithSingletonTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean client = ac.getBean(ClientBean.class);
        int count1 = client.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean client2 = ac.getBean(ClientBean.class);
        int count2 = client2.logic();
        Assertions.assertThat(count2).isEqualTo(2);
    }

    @Test
    void clientWithProviderTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean client = ac.getBean(ClientBean.class);
        int count1 = client.logicProvider();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean client2 = ac.getBean(ClientBean.class);
        int count2 = client2.logicProvider();
        Assertions.assertThat(count2).isEqualTo(1);
    }

    @Test
    void clientWithObjectProviderTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean client = ac.getBean(ClientBean.class);
        int count1 = client.logicObjectProvider();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean client2 = ac.getBean(ClientBean.class);
        int count2 = client2.logicObjectProvider();
        Assertions.assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {
        private PrototypeBean prototypeBean; // 싱글톤 생성시점에 결정된다. 싱글톤 빈을 주입받는다고 이 프로토타입 빈이 매번 새로 생성되지 않음.

        private Provider<PrototypeBean> prototypeBeanProvider;
        private ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;

        @Autowired
        public ClientBean(PrototypeBean prototypeBean, Provider<PrototypeBean> prototypeBeanProvider, ObjectProvider<PrototypeBean> prototypeBeanObjectProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
            this.prototypeBean = prototypeBean;
            this.prototypeBeanObjectProvider = prototypeBeanObjectProvider;
            System.out.println("ClientBean.prototypeBean " + prototypeBean);
            System.out.println("ClientBean.prototypeBeanProvider " + prototypeBeanProvider);
            System.out.println("ClientBean.prototypeBeanObjectProvider " + prototypeBeanObjectProvider);
        }

        public int logic() {
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }

        public int logicProvider() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }

        public int logicObjectProvider() {
            PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }

        @PostConstruct
        public void init() {
            System.out.println("ClientBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("ClientBean.destroy");
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public int getCount() {
            return count;
        }

        public void addCount() {
            count++;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }
    }
}



