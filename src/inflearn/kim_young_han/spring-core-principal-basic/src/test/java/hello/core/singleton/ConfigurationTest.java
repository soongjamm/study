package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationTest {

    // 자바 코드대로라면 호출순서는 다음과 같다.
    // memberService()
    // memberRepository()
    // memberRepository()
    // orderService()
    // memberRepository()

    // 그러나 결과는
    // memberService()
    // memberRepository()
    // orderService()

    // @Configuration이 싱글톤을 보장한다.
    // 빼면 @Bean 등록은 되지만, 스프링 컨테이너가 빈을 주입해주지 않고
    // 직접 메서드(memberService() 등)를 호출해서 싱글톤이 깨진다.
    // 즉 순수 자바로 의존성주입 한거나 마찬가지다.

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemoryMemberRepository memberRepository = ac.getBean("memberRepository", MemoryMemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("MemberRepository = " + memberRepository);
        System.out.println("MemberService -> MemberRepository = " + memberRepository1);
        System.out.println("OrderService -> MemberRepository = " + memberRepository2);

        Assertions.assertThat(memberRepository1)
                .isSameAs(memberRepository2)
                .isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean = " + bean);
    }
}
