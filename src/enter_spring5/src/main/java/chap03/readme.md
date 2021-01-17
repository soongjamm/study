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
