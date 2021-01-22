package chap05.conflict;

import org.springframework.stereotype.Component;

/**
 * 컴포넌트 스캔에 따른 충돌 처리 -
 * 주석처리된 @Component 어노테이션이 설정 클래스의 @ComponentScan 으로 인해 빈 자동주입이 되었는데
 * spring 패키지에도 같은 이름의 빈이 존재한다.
 * 그래서 충돌이 발생한다.
 * 해결 - 이름을 수정해줘야한다.
 */
@Component("infoPrinter2")
public class MemberInfoPrinter {
}
