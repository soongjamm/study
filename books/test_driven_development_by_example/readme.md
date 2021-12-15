# 테스트 주도 개발(켄트 백) 실습 공간

TDD의 목표는 `clean code that works`   
TDD란 자동화된 테스트로 개발을 이끌어가는 것이다.  

#### 두 가지 규칙
- 자동화된 테스트가 실패할 경우에만 새로운 코드를 작성한다.
- 중복을 제거한다.

#### TDD의 순서
- 빨강 : 실패하는 작은 테스트를 작성한다.
- 초록 : 빠르게 어떻게든 테스트가 통과하게 만든다. 상수를 쓰든 중복을 발생시키든 상관없다. (죄악을 저질러도 된다)
- 리팩토링 : 테스트를 통과하게 하느라 발생한 죄악을 없애고 깔끔하게 만든다.

## 화폐 예제
### 1. 다중 통화를 지원하는 Money 객체
#### 잊지말자 5 단계
- 시나리오대로 작은 테스트를 추가한다..
- 모든 테스트를 실행해서 테스트가 실패하는 것을 확인한다. 
- 조금 수정한다.
- 모든 테스트를 실행해서 테스트가 성공하는 것을 확인한다.
- 중복을 제거하기 위해 리팩토링을 한다.

#### 필요한 기능
- 주식수 * 달러
- 주식수 * 스위스 프랑을 달러로 환율 적용 (2:1)

> - 항상 '어떤 객체가 필요할까?'가 아닌, `어떤 테스트가 필요할까?`를 생각하자.

한번에 리팩토링된 코드를 생각할 수 있더라도, 아래 코드처럼 더 작은 단위부터 생각할 줄 아는 것이 중요하다.  
그래야 나중에 더 규모가 커진 상태에서 문제가 생겼을 때, 작은 단위로 돌아볼 수 있다.  
```java
// 상수를 넣어서 테스트를 통과시킴 
public class Dollar {
    public int amount = 10;

    public Dollar(int amount) {
    }

    public void times(int multiplier) {
    }
}
```

### 2. 타락한 객체
#### 빨리 초록색 막대기를 보기위한 전략
- 스텁 구현  
메소드 시그니처만 맞춰서 컴파일되게 해놓은 껍데기 (아래 코드를 보면 무조건 null만 반환함)
```java
public Dollar times(int multiplier) {
        amount *= multiplier;
        return null
    }
```

1. 가짜로 구현하기  
상수를 반환하게 만들고, 진짜 코드를 얻을 때 까지 단계적으로 상수를 변수로 바꾸어 나감
   
2. 명백한 구현 사용하기
실제 구현을 입력한다.
   
3. 삼각측량(triangulation)
뒤에 나옴

### 3. 모두를 위한 평등
`Dollar` 객체처럼 객체를 값으로 쓰는 것을 `값 객체 패턴(value object pattern)` 이라 함.
- 별칭 문제가 없음
> 별칭 문제란, 예를 들어 수표 2개를 생성했는데 두번째 수표를 변경해서 첫번째 수표의 값까지 변하게 되는 경우

#### 삼각 측량
두 개 이상의 예제가 있을 때 코드를 일반화 할 수 있다.

아래와 같이 스텁이 있다.
```java
 @Override
    public boolean equals(Object object) {
        return true;
    }
```

다음은 테스트 코드다.
```java
public void testEquality() {
    assertTrue(new Dollar(5).equals(new Dollar(5)));
    assertFalse(new Dollar(5).equals(new Dollar(6)));
} 
```
assertTrue()만 있었을 땐 통과했겠지만, 삼각 측량법을 사용하니 통과할 수 없다.  
그러므로 동치성을 일반화시키는 코드가 필요해진다.


### 4. 프라이버시
- amount를 private로 만들기
```java
@Test
void multiplicationTest() {
    Dollar five = new Dollar(5);
    assertEquals(new Dollar(10), five.times(2));
    assertEquals(new Dollar(15), five.times(3));
}
```
- 테스트 코드에서 더 이상 Dollar의 `amount`를 사용하지 않아서, amount는 Dollar 내부에서만 쓰인다.
- 이제 private로 바꿀 수 있다.
- `Dollar의 새로운 기능들을 사용해서 테스트와 코드사이의 결합도를 낮췄다.`

### 5. 솔직히 말하면
- 스위스 프랑 + 달러 환율 적용을 위해 프랑을 테스트
    - 달러를 그대로 복붙 (테스트 작성)
    - 프랑 객체 생성 (컴파일 되게하기)
    - ...
    - 위에서 언급한 5단계 중 4단계까지 빠르게 진행하기 위해 복붙도 OK
    
### 6. 돌아온 '모두를 위한 평등'
- 공용 equals 
> 복붙했던 코드를 정리한다
- 상위 클래스 Money에 equals를 정의한다.
    - Money를 정의하고 amount 필드를 생성하는 과정을 하나하나 테스트하면서 진행한다.
    - Dollar에서 equals()에 있는 Dollar 타입을 Money 타입으로 변경하는 테스트가 끝나면
    - 최종적으로 Dollar의 equals()를 Money로 옮긴다.
    - `equalityTest()`에서 Franc 테스트는 없었음을 잊지말고, 테스트를 한다.
    - 이상이 없다면 Dollar에 했던 작업을 반복한다.
    
- 만약 Dollar와 Franc를 비교하면?
    - 7장에.
    

### 7. 사과와 오렌지
> 영어 속담 `You can't compare apples and oragnes`는 서로 다른걸 비교할 수 없다는 뜻
`assertFalse(new Franc(5).equals(new Dollar(5)))` -> 실패 (타입 상충함)

```java
@Override
public boolean equals(Object object) {
    Money money = (Money) object;
    return amount == money.amount &&
            getClass().equals(money.getClass()); // 임시 처방
}
```

### 8. 객체 만들기
Money의 하위 클래스에 대한 직접적 참조가 적어졌다.  
Money에 Dollar를 반환하는 팩토리 메서드를 도입할 수 있다.  
> **팩토리 메서드를 사용하면 좋은 점**  
> 클라이언트 코드에서 Dollar 클래스의 존재를 모른다.  
> 즉 하위 클래스가 테스트에서 분리(decoupling)되어 어떤 모델코드에도 영향을 주지 않고 상속 구조를 변경할 수 있다.
하위 클래스들의 times()에서 메서드 시그니처를 Money로 통일시킨 뒤 점진적으로 Money 클래스로 이동. 중복제거

테스트 코드에서 Dollar를 완전히 없애버린다.

### 9. 우리가 사는 시간
- 통화(currency)를 어떻게 테스트할까?
- 테스트코드를 작성한다.
```java
@Test
void currencyTest() {
    assertEquals("USD", Money.dollar(1).currency());
    assertEquals("CHF", Money.franc(1).currency());
}
```
- 8장과 마찬가지로 모든 화폐에 currency()를 만들어놓고 타입을 통일시켜 중복을 제거한다.
    - 최종적으로 currency 문자열을 정적 팩토리 메서드로 이동한다.
- 서브클래스들의 생성자가 동일해진다. 
    - Money 클래스로 생성자를 올린다.
    - 그리고 서브 클래스에서는 `super(amount, currency)`로 생성한다.
  
### 10. 흥미로운 시간
- Money의 서브 클래스들을 제거한다.
- `toString()`은 디버그 출력에만 쓰일 것이기 때문에 잘못 구현될 위험이 낮다. (즉 테스트가 실패한 상황에서 구현해도 된다.)
- 테스트가 실패한 상황에서 다른 테스트를 작성하는건 좋지 않다.
- 기존에 테스트코드를 잘 작성해놓으면 코드에 변화를 주었을 때 잘 작동할 것인지 확인 가능하다.


- Money(10, "USD")와 Dollar(10, "USD")가 같길 원하지만 다르기 때문에 곱하기 테스트를 통과하지 못한다..
  - 이것을 그대로 테스트로 만든다.
```java
@Test
void differentClassEqualityTest() {
assertTrue(new Money(10, "USD").equals(new Dollar(10, "USD")));
}
```


### 11. 모든 악의 근원
- Dollar와 Franc 클래스에는 생성자 밖에 남지 않았다. 
  - 생성자 때문에 클래스가 존재할 필요는 없기 때문에 제거한다.
  - 코드의 의미를 변경하지 않고 하위 클래스에 대한 참조를 상위 클래스에 대한 참조로 변경할 수 있다.
  - Money.franc()의 `return new Franc(...)`를 `return new Money(...)`로 바꾸어도 문제없다.
  
테스트 제거
- 동치성 테스트 중, Money(10..)과 Dollar(10..)을 비교하는 것은 더이상 의미가 없으니 지워도 된다.
  - 그 외에 중복되는 동치성 테스트 제거
- 클래스가 Dollar와 Franc로 나누어져 있을땐, 각각 클래스에 대해 더하기를 테스트해야 했지만, 이제 Money에서 하나의 로직으로 동작한다.
  - Franc 더하기 테스트는 삭제해도 된다.
  

### 12. 드디어, 더하기
> 다중 통화 : 여러 나라의 화폐 단위 말함  
> 참조 통화 : 기준이 되는 화폐. 전 세계에서 무언가 거래할때 달러를 많이 쓰는 것 처럼
- 각자 다른 통화를 더하기해서 하나의 통화로 나타내야 한다.
  - 어떻게 해야할지 모르겠다. 우선, `더 작은 단위`로 나눈다.  
    동일한 화폐끼리 더하는 것을 생각한다.
- 다중 통화 연산은 어떻게?
  - 시스템 나머지 코드에 다중 통화 연산을 숨기고 싶다.
  - 여러 환율을 표현하고 연산처럼 다룰 수 있는 방법 필요
  - 객체로 해결 (imposter: 타인을 지칭하는 사기꾼)
  - Money와 비슷하게 동작하지만, 내부적으로는 두 Money의 합을 나타내는 객체.
    - (2+3)*5 <=> (2달러 + 3프랑) *5
  - 연산 결과는 Expression
    - 그 중 하나는 Sum 
  - 연산이 완료되면 환율을 적용하여 단일 통화로 축약
  - `위 과정을 거꾸로 테스트로 만들자.`
    - ### `void simpleAdditionTest()` 작성
- `reduce(환율 적용해서 단일 통화로 축약)`은 `Bank`가 하도록 한다
  - 실제 환율이 적용되는 곳은 은행이니까
  - Expression은 핵심적인 역할.
    - 핵심적인 역할은 다른 객체가 모르도록 하고싶다.
    - 그래야 객체가 오랫동안 유연할 수 있다. (재활용, 이해가 쉬움)
  - Expression 관련 오퍼레이션이 많을 때, 모든 오퍼레이션을 Expression에만 추가하면 Expression이 너무 커질 수 있다.
    - Bank가 별 필요 없어지면 reduce의 책임을 Expression으로 옮길 수 있다.
  


    

### 13. 진짜로 만들기
- 중복이 있으니 '같은 통화(usd)로 더하기(5+5)'는 끝난게 아니다.
  - Bank reduce()의 리턴값 `Money.dollar(10)`과 테스트 `simpleAdditionTest()`에 `assertEquals(Money.dollar(10), reduced);`
- 이전에는 거꾸로 가짜 -> 진짜 (상수 -> 변수)로 할일이 명확했지만, 이번엔 명확하지 않아서 정방향으로 간다.  

- `Expression sum = five.plus(five);` 두 머니의 합은 `Sum`이어야 한다.
  - ### `void plusReturnsSumTest()` 작성

- Money.plus()는 Sum이 아닌 Money를 반환함. 
  - `ClassCastException` 발생
  - Money.plus()가 Sum을 반환하게 한다.
  
- Bank.reduce()는 Sum을 전달받는다.
  - 만약 Sum이 같은 통화 끼리 더하기, 원하는 통화 역시 두 피연산자와 동일하다면?
  - reduce()의 리턴은 두 Money의 amount를 합친 Money가 나와야 한다.
  - ### `void reduceSumTest()` 작성
  
  
- 이 코드는 지저분하다.
```java
  public Money reduce(Expression source, String to) {
      Sum sum = (Sum) source;
      int amount = sum.addend.amount + sum.augend.amount;
      return new Money(amount, to);
  }
```
1. 캐스팅. 모든 Expression에 동작하도록 만들어야 함.
2. 공유(public) 필드와 두 단계에 걸친 참조 (sum.addend.amount)

해결 
- sum의 publie 필드 사용을 막기 위해, `int amount = ..` 과정을 Sum에 집어넣는다.


- Bank.reduce()의 인자로 Money를 넣었을 때 어떻게 테스트할지 작성
  - 지금까지 테스트 통과되고, 앞으로 할 것이 명확하지 않으니 테스트부터 작성한다.
  - ### `void reduceMoneyTest()` 작성
  - 이전의 `void reduceSumTest()`와 뭐가 다른가 생각해보니, 이 전에는 `Sum`을 인자로 넣었었다. `Money`를 테스트하지 않았었다.
  ```java
  if (source instanceof Money) {
    return (Money) source;
  }
  ```

  > 클래스를 명시적으로 검사하는 코드가 있을 땐, 다형성을 사용하도록 바꾸는게 좋다.  
  > Sum도 reduce()를 구현하니 인터페이스에도 올리자.  
  > => 그러면 캐스팅 검사를 지워도 된다. (알아서 각자 클래스에 reduce()로 찾아가므로)

### 14. 바꾸기
- 서로 다른 화폐에 환율을 적용한다.
```java
@Test
void reduceMoneyDifferentCurrencyTest() {
    Bank bank = new Bank();
    bank.addRadte("CHF", "USD", 2);
    Money result = bank.reduce(Money.franc(2), "USD");
    assertEquals(Money.dollar(1), result);
}
```
이렇게 되면 `bank.reduce(Money.franc(2), "USD");`의 첫번째 인자 Money가 다시 Money의 reduce고()를 호출하게 되고, 그 안에서 환율(rate)를 적용한다.  
```java
public Money reduce(String to) {
        int rate = (currency.equals("CHF") && to.equals("USD")) ? 2 : 1;
        return new Money(amount / rate, to);
    }
```
Money가 스스로 rate까지 알게되는 것은 바람직 하지 않다.  
그래서 Expression의 reduce()는 인자로 `Bank`까지 받아서, bank가 rate를 알려주도록 해야한다.  
> Expression의 구현 클래스로는 Money와 Sum이 현재 있고, Bank는 아니다.  
> `int rate = (currency.equals("CHF") && to.equals("USD")) ? 2 : 1;` 에서 마지막 `1`은 같은 화폐끼리 연산을 했을 때를 위해서였다.  

통화의 쌍을 해시테이블로 쓴다. (Pair) 그리고 Bank가 통화 정보(해시테이블)을 관리한다.
리팩토링중 코드를 작성할 땐 테스트를 안쓴다. 리팩토링이 끝나고 테스트를 통과하면 그 코드가 잘 사용된 것이다.  
> hashCode()의 0 리턴은 최악이다. 해시테이블에서 검색이 선형검색처럼 될 것이다.  


