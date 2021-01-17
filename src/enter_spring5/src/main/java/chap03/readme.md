# DI (Dependency Injection)
- DI는 의존 주입

### 의존이란? 
- 객체간의 의존을 말한다. 
    - A라는 클래스의 메소드 내에서 B라는 클래스의 메소드를 사용하면?
    - A 클래스가 B 클래스에 의존한다.
    - B 클래스에 변화가 생기면 A 클래스에도 변화가 생긴다.
### 의존하는 대상을 구하는 방법은?
- 가장 쉬운 방법은 직접 의존하는 대상 객체를 생성하는 일이다.  
`B b = new B();` `b.method();`
- 그러나 유지보수 관점에서 문제가 발생할 수 있다.  
- 스프링에서는 `DI`를 통한 의존 처리가 가능하다.

### DI ?  
- 직접 객체를 생성하지 않고, **전달받는 방식을 사용한다.**
- 생성자나 setter를 통해 객체를 `주입`받는다.
- 과정이 더 복잡해지는데 사용하는 이유는 `변경의 유연함` 때문이다.

- ex)
    - 유저 db에 접근하는 UserDao가 있다.
    - userDao에 의존하는 클래스 A, B, C가 있다.
    - userDao을 확장시킨 UpgradeUserDao를 만들어서, 클래스 A, B, C의 의존을 변경해줘야 한다.
    - 만약, 직접 생성하는 방식을 사용하고 있다면
    세 개의 클래스에서 `UserDao dao = new UserDao()`를 `UserDao dao = new UpgradeUserDao()`로 변경해야 한다.  
    - 세 개가 아니라... 훨씬 많다면 ..? ..... 노가다가 발생한다.  
- 만약 DI를 사용하면 코드 변경이 확연하게 줄어든다.
    - DI를 사용하면 실제로 A, B, C를 호출하는 곳에서 UserDao도 생성하게 될텐데
    - 그 UserDao 실제 객체를 생성하는 곳에서 한번만 수정하면 된다.
    
**dao객체를 생성하는 클래스 - 의존 변경 전**
```java
UserDao dao = new UserDao();
A a = new A(dao);
B b = new B(dao);
C c = new C(dao);
```
**dao객체를 생성하는 클래스 - 의존 변경 후**
```java
UserDao dao = new UpgradeUserDao();
// ...이하 변경없음
```

**A, B, C 클래스의 생성자**
- 의존이 변경되어도 수정할 코드가 없다.
```
public class A {
    UserDao dao;
    public A (UserDao dao) {
        this.dao = dao;
    }
}
```

> **요약**  
> 직접 의존 대상을 생성할 때는 관리 대상이 흩어져 있었지만 (A, B, C 클래스 각각)  
> DI를 사용하고 나서 부터는 dao 객체 생성 지점 한곳으로 관리 대상이 집중되었다.

### 객체 조립기 (assembler)
- 위에서 DI를 사용하면 의존 대상 객체를 생성하는 곳만 관리하면 된다고 했는데, 그 생성하는 곳과 관련된 내용이다.
- 메인 메서드에서 해도 되지만, 객체를 생성하고 의존객체를 주입하는 클래스를 따로 작성하는 것이 더 좋다.
    - 의존 객체를 변경하려면, Assember의 생성자에 있는 코드만 변경하면 된다.
```java
public class Assembler {
  private MemberDao memberDao;
  private MemberRegisterService regSvc;
  private ChangePasswordService pwdSvc;

  // 의존하는 객체를 변경하고 싶다면, 이 생성자 안에서 초기화하는 부분만 바꾸면 된다.
  public Assembler() {
    memberDao = new MemberDao(); // ex) .. -> new CachedMemberDao();
    regSvc = new MemberRegisterService(memberDao);
    pwdSvc = new ChangePasswordService();
    pwdSvc.setMemberDao(memberDao);
  }
}
```

## 스프링의 DI
- 위의 `Assembler` 클래스로 조립기를 구현한 것은 스프링 예제는 아니다.
    - 객체를 생성하고 의존 주입을 이용해 객체를 서로 연결해주는 `조립기`에 대해 알아본 것이다. (스프링을 이해하기 위함)
    - **_스프링이 DI를 지원하는 조립기이다._**
### 스프링을 이용한 객체 생성과 사용 방법
`ApplicationContext`인터페이스를 사용하는데, chapter02의 내용과 같다.

### DI방식 1: 생성자 방식
> `MemberListPrinter` 참고
### DI방식 2: 세터(setter) 방식
> `MemberInfoPrinter` 참고  
- 자바빈 규칙에 따라 작성한다. [자바빈](bit.ly/22Rj2Ar)
  - 메서드 이름이 `set` 으로 시작
  - set 다음 첫 글자는 _대문자로 시작_
  - 파라미터가 **1개**
  - 리턴 타입이 `void`

#### 생성자 방식과 세터 방식의 장단점 비교
상황에 따라 둘을 혼용해서 쓰는 것이 좋다.
- 생성자 방식의 장단점
  - 장점  
    - 빈 객체를 생성하는 시점에 모두 갖추고 시작한다.
    - 그러므로 `NullPointerException`이 발생하지 않는다.
  - 단점
- 파라미터가 많을 때, 각 인자가 어떤 의존 객체를 설정하는지 생성자의 코드를 확인해야 한다.
```java
@Bean
public MemberListPrinter listPrinter() {
return new MemberListPrinter(memberDao(), memberPrinter()); // 첫번째 인자, 두번째 인자에 어떤 의존 객체가 오는지 단번에 알 수 없다.
}
```
- 세터 방식의 장단점
  - 장점
    - 세터 메서드의 이름을 통해 어떤 의존 객체가 주입되는지 알 수 있다.
    - 파라미터가 많아도 어떤 의존 객체를 설정
```java
@Bean
public MemberInfoPrinter infoPrinter() {
    MemberInfoPrinter infoPrinter =  new MemberInfoPrinter();
    infoPrinter.setMemDao(memberDao()); // IDE 자동완성 기능으로 어떤 의존 객체를 설정해야 하는지도 알 수 있다.
    infoPrinter.setPrinter(memberPrinter());
    return infoPrinter;
}
```
  - 단점
    - 깜빡하고 의존 객체를 전달하지 않아도 빈 객체가 생성되기 때문에, 객체를 사용하는 시점에 `NullPointerException이` 발생할 수 있다.


    
