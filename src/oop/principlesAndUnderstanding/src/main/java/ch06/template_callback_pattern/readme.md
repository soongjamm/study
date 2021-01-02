# 템플릿 콜백 패턴
> `전략을 믹명 내부 클래스로 구현한 전략 패턴`

- 템플릿 콜백 패턴은 전략 패턴의 변형으로 모든 것이 전략 패턴과 동일한데, 전략을 익명 내부 클래스로 정의해서 사용한다는 특징이 있다.  
    - 템플릿 콜백 패턴을 리팩토링하면 중복되는 부분을 컨텍스트로 이관하여 코드가 간결해진다.
- 익명 내부 클래스를 사용하기 때문에 전략 패턴에서 사용한 각각의 전략 클래스(StrategyGun.. 등등)이 필요없다.  
- 스프링의 3대 프로그래밍 모델 중 하나인 DI(의존성 주입)에서 사용하는 특별한 형태의 전략 패턴이다.
    - 스프링을 이해하기 위해 `전략 패턴`, `템플릿 콜백 패턴`, `리팩터링된 템플릿 콜백 패턴`을 알아야 한다.
- 전략 패턴의 일종이므로 `개방 폐쇄 원칙(OCP)`와 `의존성 역전 원칙(DIP)`이 적용된다.  

### 예제
아래 코드와 같이 구현한다.

`Soldier.java` 
```java
public class Soldier {
    void runContext(Strategy strategy) {
        System.out.println("전투 시작");
        strategy.runStrategy();
        System.out.println("전투 종료");
    }
```

`Client.java`
```java
public class Client {
    public static void main(String[] args) {
        Soldier rambo = new Soldier();
        
        rambo.runContext(new Strategy() {
            @Override
            public void runStrategy() {
                System.out.println("총을 빵야 빵야");                
            }
        });

        System.out.println();

        rambo.runContext(new Strategy() {
            @Override
            public void runStrategy() {
                System.out.println("칼을 스각슥삭");
            }
        });

        System.out.println();
        
        rambo.runContext(new Strategy() {
            @Override
            public void runStrategy() {
                System.out.println("지건!");
            }
        });
    }
}
```

- 중복이 너무 많아서 리팩토링이 필요해보인다.
- 그래서 전략을 생성하는 코드가 컨텍스트(Soldier)에 들어가도록 수정한다.

`Soldier.java` 
```java
public class Soldier {
    void runContext(String weaponSound) {
        System.out.println("전투 시작");
        executeWeapon(weaponSound).runStrategy();
        System.out.println("전투 종료");
    }
    
    private Strategy executeWeapon(final String weaponSound) {
        return new Strategy() {
            @Override
            public void runStrategy() {
                System.out.println(weaponSound);
            }
        };
    }
}
``` 
- `runConext()`의 매개값이 Strategy에서 String으로 바뀌었다.
- 그리고 `executeWeapon()`이 Strategy를 반환하게 한다.
- 반환받은 Strategy로 전략을 실행한다.
    - 전략마다 차이를 만드는 것이 String이니까 String을 매개값으로 하고
    - String 매개값으로 전략을 생성해내는 것이다. 


`Client.java`  
```java
public class Client {
    public static void main(String[] args) {
        Soldier rambo = new Soldier();
        
        rambo.runContext("빵야 빵야 총!!");
        System.out.println();
        
        rambo.runContext("칼을 슥슥!!");
        System.out.println();

        rambo.runContext("지건!!");
        
    }
}
```
- 중복되는 부분을 컨텍스트로 이관하여 코드가 간결해졌다.


