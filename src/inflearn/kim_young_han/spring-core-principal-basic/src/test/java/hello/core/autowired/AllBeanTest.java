package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void allBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService service = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "soongjamm", Grade.VIP);
        int discount = service.discount(member, 10000, "fixDiscountPolicy");
        Assertions.assertThat(discount).isEqualTo(1000);
    }


    static class DiscountService {
        List<DiscountPolicy> discountPolicyList;
        Map<String, DiscountPolicy> discountPolicyMap;

        @Autowired
        public DiscountService(List<DiscountPolicy> discountPolicyList, Map<String, DiscountPolicy> discountPolicyMap) {
            this.discountPolicyList = discountPolicyList;
            this.discountPolicyMap = discountPolicyMap;

            System.out.println("discountPolicyList = " + discountPolicyList);
            System.out.println("discountPolicyMap = " + discountPolicyMap);
        }

        public int discount(Member member, int price, String policyCode) {
            DiscountPolicy discountPolicy = discountPolicyMap.get(policyCode);
            return discountPolicy.discount((member), price);
        }
    }
}
