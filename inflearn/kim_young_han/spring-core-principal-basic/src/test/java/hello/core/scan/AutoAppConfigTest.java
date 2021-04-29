package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import hello.core.scan.sample.OrderServiceConstructorInjection;
import hello.core.scan.sample.OrderServiceFieldInjection;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    void fieldInjectionTest() {
        // 테스트에서는 스프링을 사용하지 않고 순수 자바로만 테스트하게 될 수도 있다.
        // 이 경우 field injection 을 해줄 수가 없다. '빈 컨테이너' 가 없기 때문이다.

        // field injection 의 단점은 테스트시에 내가 원하는 의존성으로 교체(주입)해줄 수가 없다. -> NPE 가 터진다.
        OrderServiceFieldInjection orderService1 = new OrderServiceFieldInjection();
        assertThrows(NullPointerException.class,
                () -> orderService1.createOrder(1L, "item", 10000)
        );

        // 그러나 생성자 or Setter 주입을 하면 테스트시에도 이렇게 의존성을 변경해줄 수 있다.
        MemoryMemberRepository memberRepository = new MemoryMemberRepository(); // 내부에서 member 때문에 npe 터지는것 방지
        memberRepository.save(new Member(1L, "item", Grade.BASIC));

        OrderServiceConstructorInjection orderService2 =
                new OrderServiceConstructorInjection(memberRepository, new RateDiscountPolicy());
        assertDoesNotThrow(
                () -> orderService2.createOrder(1L, "item", 10000)
        );
    }
}
