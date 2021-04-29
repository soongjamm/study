package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredOption {
    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class); // == @ComponentScan
    }

    static class TestBean {

        @Autowired(required = false) // 호출 안됌
        public void setNoBean1(Member member) {
            System.out.println("required=false. member = " + member);
        }

        @Autowired
        public void setNoBean2(@Nullable Member member) {
            System.out.println("@Nullable. member = " + member); // null
        }

        @Autowired
        public void setNoBean3(Optional<Member> member) {
            System.out.println("Optional<>. member = " + member); // Optional.empty
        }
    }
}
