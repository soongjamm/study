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

  