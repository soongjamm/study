# IoC/DI (Inversion of Control / Dependency Injection)

## 의존성이란?
`의사코드`
- 운전자가 자동차를 생산한다.
- 자동차는 내부적으로 타이어를 생산한다.

`자바로 표현`
- new Car();
- Car 객체의 생성자 안에 new Tire();

의존성을 정리하면
- 의존성은 new
- new를 실행하는 Car와 Tire 사이에서 Car가 Tire에 의존
- `전체와 부분` - 전체가 부분에 의존한다.
    - 전체 : Car
    - 부분 : Tire
- `프로그래밍에서 의존 관계는 new로 표현된다.`

> 기존 자바 코드로만 표현하면
> main()에서 Car car = new Car(); 로 Car 객체를 생성하고
> Car의 생성자 내부에서 Tire tire = new Tire(); 로 타이어를 생성함으로써 의존성을 표현한다.
> == 의존성을 자체적으로 해결한다.


## 스프링 없이 의존성 주입1 - 생성자를 통한 의존성 주입
`의사코드`
- 운전자가 타이어를 생산한다.
- 운전자가 자동차를 생산하면서 타이어를 장착한다.

> 주입이란 `외부에서` 라는 뜻을 내포한다.  
> 외부에서 타이어를 생산하고 이미 생산된 자동차에 장착하는 작업이 `주입`이다.  
> 자동차 내부에서 타이어를 생산하지 않는다.
- Car 생성자의 인자로 Tire를 주입하는 형태다.
- 주입하지 않았을 때 기존 코드의 문제점
    - 주입하지 않는 기존 코드에서는 Car가 구체적으로 어떤 Tire를 생산할지 결정했다.
    - 그러므로 유연성이 떨어진다.
    - Car가 KoreaTire, AmericaTire에 대해 정확하게 알아야만 객체를 생성할 수 있었다.
- 장점
    - 자동차가 스스로 고민하지 않고, 운전자가 차량을 자동차를 생산할 때 고민한다.
    - 구체적인 Tire의 정보가 없어도 Tire 인터페이스를 구현한 객체라면 정상적으로 작동한다.
    - 즉 새로운 브랜드의 타이어가 생겨도 Tire 인터페이스를 구현하기만 한다면 코드를 변경할 필요가 없다.
        - 다시 컴파일하지 않아도 된다.
        - Car.java, Tire.java를 하나의 모듈로, Driver.java, KoreaTire.java .. 를 하나의 모듈로 한다면
        - 새로운 타이어가 생겨도 Driver.java, ChinaTire.java만 컴파일해서 배포하면 된다.
    - 이것을 현실세계에서의 말로 `표준화`했다고 한다.

> 전략 패턴을 사용하고 있다.
> 전략 - 각종 타이어들
> 컨텍스트 - Car의 getTireBrand()
> 클라이언트 - Driver의 main() 

## 스프링 없이 의존성 주입2 - 속성을 통한 의존성 주입
`의사코드`
- 운전자가 타이어를 생산한다.
- 운전자가 자동차를 생산한다.
- 운전자가 자동차에 타이어를 장착한다.

- 속성(setter)로 Tire를 주입하는 형태다.
- 생성자로 의존성을 주입했을 때 문제점
    - 타이어를 장착하고 나면 바꿀 수 없다.
    
## 스프링을 통한 의존성 주입 - XML 파일 사용
`의사코드`
- 운전자가 종합 쇼핑몰에서 타이어를 구매한다.
- 운전자가 종합 쇼핑몰에서 자동차를 구매한다.
- 운전자가 자동차에 타이어를 장착한다.

`자바로 표현` - 속성 메서드 사용
- ApplicationContext context = new ClassPathXmlApplicationContext("c4_di_spring.xml", Driver.class)
- Tire tire = (Tire)context.getBean("tire");
- Car car = (Car)context.getBean("car");
- car.setTire(Tire);

- 스프링을 도입시 장점
    - 자동차의 타이어 브랜드를 변경할 때 재컴파일/재배포 없이 XML 파일만 수정해도 프로그램의 실행 결과를 바꿀 수 있다.
        - `Driver.java`의 `Tire tire = context.getBean("tire",Tire.class);` 이 부분에 구체적인 타이어를 명시하지 않는다.
        - xml파일에 `<bean id="tire" class="...KoreaTire">..`에서 클래스부분만 수정하면 된다.
        - 실제 배포한 환경이라면 엄청난 장점이라고 한다.
        
## 스프링을 통한 의존성 주입 - 스프링 설정 파일(XML)에서 속성 주입
`의사 코드` - 점점 더 현실 세계를 닮아간다.
- 운전자가 종합 쇼핑몰에 자동차를 구매 요청한다.
- 종합 쇼핑몰은 자동차를 생산한다.
- 종합 쇼핑몰은 타이어를 생산한다.
- 종합 쇼핑몰은 자동차에 타이어를 장착한다.
- 종합 쇼핑몰은 운전자에게 자동차를 전달한다.

`자바로 표현` 
- ApplicationContext context = new ClassPathXmlApplicationContext("c5_di_spring_property.xml", Driver.class)
- Car car = conext.getBean("car", Car.class);

`XML로 표현`
```xml
....
<bean id="koeraTire"...>
<bean id="americaTire"...>
<bean id="car" class=".....Car">
    <property name="tire" ref="koreaTire"></property>
</bean>
....
```

- 자바의 접근자, 설정자 메서드를 속성 메서드라고 한다. 
    - = Property
    - 즉 `car.setTire(tire)`를 xml의 property 태그로 대체하는 것이다.
- 각 Tire클래스와 Car클래스엔 변화가 없다.
    - Driver의 main()에서 Tire생성과 주입부분 (총 2줄)을 삭제한다.
> property의 name은 해당 bean의 클래스에 있는 set 메소드 이름을 따른다.
> 만약 `Car.java`에서 tire를 주입하는 setter의 이름이 `setTire()`가 아닌 `setPotato()`로 되어있다면, property의 name도 potato가 되어야 한다.


## 스프링을 통한 의존성 주입 - @Autowired
- 의사코드는 이전과 똑같다. XML에서 약간의 수정이 일어난다.
- @Autowired는 스프링 설정 파일을 보고 자동으로 속성의 setter 메서드 역할을 해준다.
- property를 삭제하고, 속성에 `@Autowired`를 적용한다.
    - 별도의 setter나, xml파일에 property를 작성할 필요가 없다.

- **type 기준 매칭**
    - 타이어를 변경하고 싶다면 bean의 id 속성만 변경하면 된다.
    - 그런데 bean에 id가 써있지 않아도 구동될 수 있다.
    
    - type을 구현한 bean이 있는가?
        - 없다면 `No matching bean` 에러
    - bean이 1개인가?
        - 아니라면 id가 일치하는 빈이 있는가?
            - 없다면 `No unique bean 에러`
    - 유일한 bean을 객체에 할당

#### type 기준 매칭 예제
```
// Car.java
@Autowired
Tire tire;

// beans.xml
<bean id="adlskjf" class="~.AmericaTire"></bean>
```
- 위 처럼 터무니없는 id의 bean이어도 Tire 타입을 구현한 bean이 하나 존재하는 것이므로 구동된다.

```
// Car.java
@Autowired
Tire tire;

<bean class="~.AmericaTire"></bean>
<bean id="tire" class="~.Door"></bean>
```
- 위는 Tire 타입을 구현한 bean도 있지만, id만 tire고 엉뚱한 클래스의 bean도 존재한다.
- 이 경우도 어쨌든 type을 기준으로 먼저 매칭하기 때문에, AmericaTire 타입의 bean과 매칭된다.
     

---
bean xml파일을 읽어들이지 못하는 에러가 있었는데, 클래스패스와 관련이 있다고 한다.  
bean xml파일을 resources폴더에 넣으니 해결됬는데, 클래스패스를 공부해보자.
 
@Autowired field가 자꾸 null이 되는 현상 
- `<context:annotation-config />`을 빼먹어서 였다.
- https://deinum.biz/2020-07-03-Autowired-Field-Null/#xml 
