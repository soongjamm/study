# 
> 백기선님 인프런 강의  `더 자바, 애플리케이션을 테스트하는 다양한 방법` 정리
#### 진행중 발생한 문제
자바 버전 관련 에러 - Error:java: invalid source release: 11 [해결](https://soongjamm.tistory.com/104)
<br>
## JUnit5 소개
Junit4는 자체가 하나의 덩어리 jar로 디펜더쉴로 들어오고, junit4가 다른 라이브러리 참조한다.
Junit5는 JUnit5 자체가 여러 모듈화되었다. 플랫폼 위에 5의 세부모듈인 `Jupiter`, `Vintage`를 올리는 형태다. 
그 중 `JUnit Platform`은 JUnit으로 작성한 테스트코드를 실행시켜주는 런처를 제공한다.
런처를 통해 콘솔, 메인 메소드, 인텔리제이같은 툴에서도 자바로 써진 테스트코드를 실행할 수 있다.
<br>
인텔리제이에서 테스트 코드를 main 메서드 없이 작성해도 실행한다.  
개발 툴이 `JUnit Platform`을 사용해서 클래스에 들어있는 테스트 어노테이션이 붙은 메서드를 실행해주기 때문이다.   
<br> 
`Jupiter`는 Junit5 구현체, `Vintage`는 JUnit3와 4를 지원하는 테스트 엔진 구현체이다.

## JUnit5 시작하기
> 우선 스프링부트를 설치한다. https://goddaehee.tistory.com/238 참고

junit5 실행하는 간단한 방법을 보자.
스프링부트 프로젝트를 만든다. 2.2+ 버전의 스프링부트 프로젝트는 기본으로 JUnit5 의존성이 추가된다.

기본으로 생성되는 스프링부트 클래스를 삭제하고 새로운 클래스와 테스트 클래스를 생성해서 실습한다.     
> 테스트 클래스를 생성하는 명령어는 `cmd`+`shift`+`t` 
> 또는 shift를 더블 클릭하고 `Java Declaration: Create Test`  

JUnit5에서는 테스트가 `public`일 필요 없다.   
> 리플렉션을 사용해서라고 하는데, 리플렉션이 뭔지는 더자바-코드를 조작하는.. 강의 참고 (또는 찾아보자)  

<br>

#### 어노테이션

`@Test`
- 해당 메소드 안에있는 내용을 테스트한다.

`@BeforeAll`, `@AfterAll`
- 테스트클래스 안에 모든 테스트 실행하기 전에(또는 후에) 한번만 호출이 된다.
- 반드시 `static` 메서드를 사용해야한다. 
- 접근제어자로 `private`은 안되고 `default`는 된다. 
- 리턴타입 반드시 `void`여야하고, 메소드 이름은 상관없다.

`@BeforeEach`, `@AfterEach`
- 모든 테스트에 대해 하나의 테스트를 실행할 때마다, 각각 실행하기 이전(또는 이후)에 한번씩 실행. 
- static일 필요 없다.

`@Disabled`
- 해당 테스트를 수행하지 않는다. 
- 깨진 테스트가 있다면 고쳐야겠지만 필요한 경우도 있다.  
    - 예를들어 더이상 관리되지 않는 코드인데 소스가 정리가 안되서 테스트코드를 남겨둔 경우
    - > 나는 아직 경험이 없어서 잘 이해가 안된다.


## 테스트 이름 표시하기
테스트하면 `Run` 탭에 테스트 이름이 표기된다. 기본은 메소드 이름이다.

#### 어노테이션
`@DisplayNameGeneration`
- 클래스와 메소드 모두에 적용 가능하다.
- 클래스에 적용하면 모든 메소드에 적용된다.   
- 인자로는 Strategy 즉 어떤식으로 디스플레이 이름을 새성할건지, 전략에 해당하는 구현체를 입력해줄 수 있다. 
- 기본 구현체로 ReplaceUnderscores를 제공하는데, _를 공백으로 치환해준다.
    - 테스트케이스는 보통 스네이크 케이스로 작성한다.

`@DisplayName` (권장)
- 테스트 메소드 이름이 길어지고 불편해지는 경우 테스트 이름을 잘 표현할 수 있다.
    - `@DisplayNameGeneration`도 나쁘지 않은 방법이긴 하지만, `@DisplayName`을 더 권장한다.
- 테스트 이름으로 한글 적어도 되고 공백으로 적어도 되고, 이모지를 쓸 수도 있다.

> Tip.  
> 테스트 메소드 이름에 마우스를 가져다대고 `ctl`+`shift`+`r`을 누르면 해당 테스트만 진행되고,  
> 공백 부분에 마우스를 가져다대고 똑같은 키를 누르면 전체 테스트가 진행된다.
>
>
---

## Assertion
실제테스트에서 검증하고자 하는 내용을 확인하는 기능이다.  
> `assert`를 다 다루기엔 너무 많고 몇가지만 간추려서 학습한다.  

> 실제 로직 작성전에 테스트 먼저 작성하자.  

`assertEquals(expected, actual)`
- 인자로 `(기대값, 실제값[, 실패시 메세지])`로 주고 두 값이 같은지 확인한다.   
- 실패시 메세지는 필수는 아니다. 다만 나중에 봤을 때 메세지를 적어놨면 디버깅이 더 쉬울것이다. 
- 메세지는 스트링으로 줄수도 있고 `supplier (or supplier를 람다식)`으로 작성할 수도 있다.
    - `Supplier`도 테스트가 실패했을때 출력해줄 메세지를 적어주면 된다. 
    - 스트링으로 작성핳는 것 보다 복잡하지만 사용하는 이유는 에러 메세지를 만들 때, (연산이 들어가는 등 복잡한 메세지를 생성할 때), 실패한 경우에만 해당 메세지를 만들게 할 수있다. 그냥 스트링을 넣으면 성공이던 실패던 항상 만든다.
    - 즉 문자열 연산 비용이 걱정되는 경우에 성능에 유리하다.
    - `Supplier`에 대해서는 자바8을 공부한다.
<br>

`assertTrue(boolean)`
- 다음 조건이 참인지 확인한다.

`assertAll(executables...)`
- 원래 앞에 있는 테스트가 깨지면 그 다음 테스트는 실행되지가 않는데, 
- 깨지지 않고 연관된 테스트를 모두 묶어주는 방법이다.
- 람다식으로 묶는다.
```
assertAll(
        () -> assertNotNull(study),
        () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 DRAFT여야 한다."),
        () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야한다.")
);
```

`assertThrows(expectedType, executable)`
- 로직에서 예외를 던지는지 확인할 수 있다. 
- 예외를 받아서 `assertTrue()`로 내가 기대한 메세지와 같은지 확인하는 테스트코드를 짤 수도 있다.
```
IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new Study(-10));
assertEquals("limit은 0보다 커야 한다.", exception.getMessage());
```

`assertTimeout(duration, executable)`
- 실행이 얼마만에 끝나야 한다는 시간제한을 걸어서 시간을 초과하면 에러가 난다.
- 단점 : 반드시 코드블럭 실행이 끝날때 까지 기다린다. 
    - 이 테스트에서 오래걸리는 코드가 있다면 그만큼 테스트도 시간이 걸린다.  
    - (시간제한을 몇초로 걸든 10초 걸리는 코드있으면 10초 기다려야함)  

`assertTimeoutPreemptively()`
- `assertTimeout()`을 단점을 개선해, 사용하면 제한건 시간 지났을 때 그냥 종료함. 
- 주의 : 코드블록은 별도의 스레드에서 실행하기 때문에 `ThreadLocal` 이라는걸 사용해야하는 경우 예상치 못한 문제 발생한다. (나중에 스프링 시큐리티등에서 사용)
    - 스레드와 상관없는 코드일때만 사용하는게 안전하다.   
    
> AssertJ​, ​Hemcrest​, ​Truth​ 등의 라이브러리도 있다. 
> 예전에 JUnit과 AssertJ를 혼동한 적이 있는데 다른 것임을 기억하자.  
> AssertJ는 `asserThat(actual.getLimit()).isGreaterThan()...` 처럼 영어 작문하듯 작성한다.


---
조건에 만족할 때만 테스트를 실행하는 메소드
assumeTrue() : 성공시에만 이 다음 코드를 테스트
assumingTrue() : 