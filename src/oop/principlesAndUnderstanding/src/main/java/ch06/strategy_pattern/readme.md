# 전략 패턴 (Strategy Pattern)
> `클라이언트가 전략을 생성해 전략을 실행할 컨텍스트에 주입하는 패턴`

다양한 전략을 생성해내고 필요에 따라 컨텍스트에 주입시키면 전략을 실행할 수 있다.  
전략을 주입시킨다는 것은 모델링된 객체(어플리케이션 바운더리)에 전략을 매개값으로 주어서 사용되도록 하는 것이라고 할 수 있다.
```java
Startegy startegy = new Strategy(); // 전략 생성
Client client = new Client();
client.runContext(strategy); // 전략을 주입했다.
``` 

> **중요 포인트**
> - 전략 메서드를 가진 전략 객체
> - 전략 객체를 사용하는 컨텍스트(전략 객체의 사용자/소비자)
> - 전략 객체를 생성해 컨텍스트에 주입하는 클라이언트(제3자, 전략 객체의 공급자)

- 템플릿(견본) 템플릿과 유사하다.
    - 상속을 이용한 템플릿 메서드 패턴
    - 객체 주입을 통한 전략 패턴
> 자바는 단일 상속만 가능하기 때문에 제한이 있어서 전략 패턴이 더 많이 사용된다.

- `개방 폐쇄 원칙(OCP)`, `의존 역전 원칙(DIP)`이 적용되었다.

#### 헷갈렸던 점
데코레이터 패턴, 전략 패턴이 어떻게 다른지 혼동이 왔다.  

[데코레이터 패턴의 예제-https://johngrib.github.io/wiki/decorator-pattern/](https://johngrib.github.io/wiki/decorator-pattern/)를 보면 
```java
Beverage beverage = new Espresso();
System.out.println(beverage);

beverage = new Mocha(beverage);
System.out.println(beverage);
```
위와 같이 생성자 메소드 Mocha()에 beverage 객체를 매개값으로 주는 것을 볼 수 있다.  
그것이 전략 패턴에서 전략 객체를 컨텍스트에 주입하는 것과 비슷하다고 느꼈다.

`데코레이터 패턴`의 주목적을 다시 생각해보면 `메서드 호출의 반환값에 변화를 주는 것`이다.  
즉, 데코레이터 내부에서 어떤 작업을 수행하고 클라이언트에 반환할때 그 반환값에 변화를 주는것이 주목적이다.
위에 Beberage 예제에서도 반환값을 기다리고 있다.  

반면 `전략 패턴`은 클라이언트가 전략을 생성할 수 있고, 이를 통해 다양한 문제 상황을 해결할 수 있다는 것이 주안점이다.