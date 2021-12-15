# 스프링 시작하기

## Spring ?
자바로 웹 애플리케이션을 개발할때 사용하는 웹 프레임워크.  
스프링 데이터, 스프링 배치, 스프링 시큐리티 등 다양한 스프링 프로젝트가 존재한다.

#### 특징
- 의존성 주입(Dependency Injection)
- AOP (Aspect Oriented Programming)
- MVC 웹 프레임워크 제공
- DB 연동 지원 


## Bean ?
- Bean은 스프링에서 제공하는 객체로, 설정 정보를 담고 있다.  
  스프링의 중요한 일 중 하나가 객체를 생성하고 초기화 하는 기능인데, 그 객체를 `빈(bean)` 이라고 한다.
- `@Configuration` 어노테이션을 클래스에 붙여서 스프링 설정 클래스로 만들 수 있다.
- `@Bean` 어노테이션을 메서드에 붙이면 해당 객체를 스프링이 관리하는 빈 객체로 등록할 수 있다.
    - 메서드의 이름이 빈 객체를 구분하는 이름이 된다. (빈을 검색할 때 이 값을 사용한다.)

### Bean 검색해서 사용하기
- `AnnotationConfigApplicationContext` 로 스프링 설정 정보를 가져와 객체를 생성한다.
- 생성한 객체에서 bean을 검색해 가져온다.
```java
AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
Greeter g1 = ctx.getBean("greeter", Greeter.class);
```
### 스프링은 객체 컨테이너
- 자바 클래스에서 설정 정보를 읽어와 객체 생성화 초기화하는 클래스가 `AnnotationConfigApplicationContext`다.
- `BeanFactory` 인터페이스 ⇠ `ApplicationCotext` 인터페이스 ⇠ .... ⇠ (3가지 구현 클래스) 존재
    - `BeanFactory` : 객체 생성과 검색에 대한 기능 정의
    - `ApplicationContext` : 메시지, 프로필/환경 변수등을 처리할 수 있는 기능 추가로 정의
- 3가지 구현 클래스중 하나가 `AnnotationConfigApplicationContext`인데, 어노테이션을 이용해 클래스로부터 객체 설정 정보를 가져온다.
    - `GenericXmlApplicationContext` - XML로부터 설정정보를 가져온다.
    - `GenericGroovyApplicationContext` - 그루비 코드 이용
    - 위의 3가지 구현 클래스 모두 Bean 객체를 생성하고 내부에 설정 정보를 저장하고 있는다.
- `BeanFactory`와 `ApplicationContext`가 빈 객체의 생성, 초기화, 보관, 제거등을 관리.
    - 이 둘을 `스프링 컨테이너`라고 부른다.
    - 내부적으로 빈 객체와 빈 이름으 연결하는 정보를 가지고 있다.
    - 실제 객체의 생성, 초기화, 의존 주입등 객체 관리를 위한 다양한 기능을 제공함
    
## Singlton ?
- 싱글톤은 단일 객체를 의미한다.
```java
Greeter g1 = ctx.getBean("greeter", Greeter.class);
Greeter g2 = ctx.getBean("greeter", Greeter.class);
System.out.println("(g1 == g2) = " + (g1 == g2));
```
- 두 객체는 동일한 객체라고 나온다. 
- 스프링은 `@Bean`에 대해 한개의 빈 객체를 생성한다.
- 새로운 빈 객체를 생성하려면 @Bean을 붙인 메소드를 추가하면 된다.