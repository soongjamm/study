## 상속  
### 자식 생성자의 부모 생성자 호출
자식 객체를 생성하면 부모 객체가 먼저 생성된 뒤 자식 객체가 생성된다.  
아래 코드에서 Child()를 호출하면 Child 생성 이전에 Parent를 먼저 생성함으로  
`Parent() call` =>  `Child() call` 순으로 출력된다.

```
public class Parent {
    public String nation;

    public Parent() {
        System.out.println("Parent() call");
    }
```

```
public class Child extends Parent {
    private String name;

    public Child() {
        System.out.println("Child() call");
    }
}
```

즉 자식 생성자 첫줄에 `super();` 가 숨어있는 것과 같다.  
만약 부모 생성자가 명시적으로 선언되지 않았다면 컴파일러는 다음과 같은 기본 생성자를 생성해낸다.
```$xslt
public Parent() {
}
```

<br>

### 부모 메소드 호출 (super)
자식 클래스에서 부모 클래스의 메소드를 오버라이드 했을 땐 생성자와 달리 `super.~` 가 숨어있지 않다.  
오버라이드한 코드만 실행이 되고 부모 메소드가 필요하다면 `super.부모메소드();` 로 호출할 수 있다.

### 기타
* final 클래스는 상속할 수 없다.
* protected 접근 제한자가 붙어있으면 다른 패키지의 클래스에서 접근할 수 없다, **단 상속을 받을 순 있다.**