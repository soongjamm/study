# 일급 컬렉션 
[참고: jojoldu님 블로그](https://jojoldu.tistory.com/412)  
### 일급 컬렉션이란?
```java
public class Station {
    List<String> stations = new ArrayList<>();
    // ...
}
```
- 컬렉션을 wrapping 하는 것을 말한다.
- 콜렉션을 포함한 클래스는 반드시 다른 멤버 변수가 없어야 한다.
- 각 콜렉션은 그 자체로 포장되어 있으므로 콜렉션과 관련된 동작 근거지가 마련된 것이다.
- 필터가 이 새 클래스의 일부가 될음 알 수 있다.
- 필터는 스스로 객체가 될 수 있다.
- 두 그룹을 묶는 등 그룹의 각 원소에 규칙을 적용하는 등의 동작을 처리할 수 있다.


### 장점
- 비즈니스에 종속적인 자료구조
- Collection의 불변성 보장
- 상태와 행위를 한 곳에 저장
- 이름이 있는 컬렉션

### 1. 비즈니스에 종속적인 자료구조
`LottoService.java` :  
`createLottoNumber`에서 `List<Long>`으로 로또 번호를 생성한다.   
이 경우 문제점은 로또번호가 필요한 모든 곳에서 검증이 필요하다. (`validate**()`) 이 코드를 처음보는 사람은 검증을 해야하는지 모를 수 있다.  
이 문제점을 해결하기 위해 6개의 숫자로 이루어지고, 서로 중복되지 않는 자료구조를 만든다. 
> 그것이 `일급 컬렉션`이다.
<br>

`LottoTicket.java` :  
`validate**()`를 이 클래스의 생성자에서 수행한다. 로또 번호를 생성한 이 자료구조를 사용할 땐 다음과 같이 사용한다.  
```java
public class LottoService2 {
    public void createLottoNumber() {
        LottoTicket lottoTicket = new LottoTicket(createNonDuplicateNumbers());
    }
}
```
이로써 비즈니스에 종속적인 자료구조가 만들어졌다.

<br>

### 2. 불변
컬렉션에는 `final` 예약어를 붙여도 재할당만 금지되지, 컬렉션 메소드를 통해 값을 추가할 수 있으니 불변이 아니다.  
```java
final List<String> names = new ArrayList<>();
names.add("홍길동"); // 가능하다.
```
그러나 일급 컬렉션을 사용한 경우, `set 메소드를 추가하지 않으면` 콜렉션은 불변이 된다.

<br>

### 3. 상태와 행위를 한 곳에서 관리
`PayTest.java`:  
Enum과 마찬가지로 값과 로직이 한곳에 위치하는 것이 장점이다.
예를 들어 여러가지 Pay가 있고, NaverPay의 합이 필요할 때,
```java
public void test() {
    //given
    List<Pay> pays = new Arrays.List(
            new Pay(NAVER_PAY, 1000), 
            new Pay(NAVER_PAY, 1500),
            new Pay(KAKAO_PAY, 1000),
            new Pay(TOSS, 1500)); 
    //when
    Long naverPaySum = pays.stream()
            .filter(pay -> getPayType().equals(NAVER_PAY))
            .mapToLong(Pay::getAmount)
            .sum();
}
```
위 처럼 list에 담고 Service에서 필요한 로직을 수행한다.  
이 상황에서 문제점은 pays라는 리스트와 그 아래 로직이 관련이 있음이 표현이 안된다.
이 코드를 모르는 사람은 둘이 관련이 있는지 모르고, 똑같은 기능을 하는 코드를 중복으로 작성할 수도 있다.    

> 원문 인용  

> Pay타입의 상태에 따라 지정된 메소드에서만 계산되길 원하는데, 현재 상태로는 강제할 수 있는 수단이 없습니다.  
지금은 Pay타입의 List라면 사용될 수 있기 때문에 히스토리를 모르는 분들이라면 실수할 여지가 많습니다.

> - 똑같은 기능을 하는 메소드를 중복 생성할 수 있습니다.  
>   - 히스토리가 관리 안된 상태에서 신규화면이 추가되어야 할 경우 계산 메소드가 있다는 것을 몰라 다시 만드는 경우가 빈번합니다.  
>   - 만약 기존 화면의 계산 로직이 변경 될 경우, 신규 인력은 2개의 메소드의 로직을 다 변경해야하는지, 해당 화면만 변경해야하는지 알 수 없습니다.  
>   - 관리 포인트가 증가할 확률이 매우 높습니다.  
> - 계산 메소드를 누락할 수 있습니다.  
>   - 리턴 받고자 하는 것이 Long 타입의 값이기 때문에 꼭 이 계산식을 써야한다고 강제할 수 없습니다  .
>   - 결국에 네이버페이 총 금액을 뽑을려면 이렇게 해야한다는 계산식을 컬렉션과 함께 두어야 합니다.  
>   - 만약 네이버페이 외에 카카오 페이의 총금액도 필요하다면 더더욱 코드가 흩어질 확률이 높습니다.  

>그래서 이 문제 역시 일급 컬렉션으로 해결합니다.

```java
List<Pay> pays = Arrays.asList(
            new Pay(NAVER_PAY, 1000),
            new Pay(NAVER_PAY, 1000),
            new Pay(KAKAO_PAY, 1000),
            new Pay(TOSS, 1500));

    // 상태와 로직이 한 곳에 위치
    PayGroups payGroups = new PayGroups(pays);
    Long naverPaySum = payGroups.getNaverPaySum(); 
```

### 4. 이름이 있는 컬렉션
같은 Pay들의 모임이라도 `kakaoPay`와 `naverPay`는 다르다.  이 둘을 구분하는 가장 쉬운 방법은 변수명으로 구분하는 것이다.
```java
List<Long> kakaoPay = createNaverPays();
List<Long> naverPay = createNaverPays();
```
이 방식의 단점은 검색이 어렵고, 명확한 표현이 불가능하다는 것이다.

> 원문 인용  


> 위 코드의 단점은 뭘까요?
> - 검색이 어려움
>   - 네이버페이 그룹이 어떻게 사용되는지 검색 시 변수명으로만 검색할 수 있습니다
>   - 이 상황에서 검색은 거의 불가능합니다.
>   - 네이버페이의 그룹이라는 뜻은 개발자마다 다르게 지을 수 있기 때문입니다.
>   - 네이버페이 그룹은 어떤 검색어로 검색이 가능할까요?
> - 명확한 표현이 불가능
>   - 변수명에 불과하기 때문에 의미를 부여하기가 어렵습니다
>   - 이는 개발팀/운영팀간에 의사소통시 보편적인 언어로 사용하기가 어려움을 얘기합니다.
>   - 중요한 값임에도 이를 표현할 명확한 단어가 없는것이죠.

이 문제의 해결 역시 일급 컬렉션으로 가능하다.  
네이버페이, 카카오페이 각각의 일급 컬렉션을 만들어 이 컬렉션을 기반으로 용어 사용과 검색을 한다.
```java
KakaoPays kakaoPays = new KakaoPays(createKakaoPays());
...
```
<br>

❓ 공부할 것
- 함수형 인터페이스 (Predicate)
```java
public Long getNaverPaySum() {
        return getFilteredPays(pay -> PayType.isNaverPay(pay.getPayType()));
    }

private Long getFilteredPays(Predicate<Pay> predicate) {
        return pays.stream()
                .filter(predicate)
                .mapToLong(Pay::getAmount)
                .sum();
    }
```
 