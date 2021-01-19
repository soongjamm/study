# Optional
- 자바8에 추가된 인터페이스로 비어있을 수도 있고, 무언가를 담고 있을 수도 있다는 것을 암시한다.

### 등장배경:
#### 만약 Duration을 가져오고 싶다.? (OnlineClass에 Progress, Progress안에 Duration)
```java
OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
spring_boot.progress.getStudyDuration(); // null 참조
```
- **spring_boot**의 progress 필드가 null일 경우 `NullPointerException이` 발생한다.
  
```java
OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
Progress progress = spring_boot.progress; // null 참조
if (progress != null) {
    progress.getDuration();
}
```
- 그래서 이렇게 중간에 null 체크를 하면서 썼었다.

#### 그러나 이러한 방식은 문제점들이 있다. 
1. 실수할 가능성이 높은 코드다. 인간이 코딩하기 때문에 null 처리 깜빡할 수 있다.
2. 애초에 null을 리턴하는 것 자체가 문제다.

자바8전에는 별다른 대안이 없었다.   
그래서 Progress 리턴하는 쪽에서 null이면 에러 던지게 코딩했다. (`IllegalStateException` 등)
그러나 에러를 던질때 문제는 자바가 `stack trace`를 찍는데, 에러전까지 어떤 콜스택을 거쳐서 에러가 발생했는지를 담게 되고 이 자체로 리소스를 쓰게 되는 것이다.  
> 에러를 던지는건 진짜 필요한 경우에만 써야지, 로직을 처리할 때 에러 던지는건 별로 좋지 않다.
> 에러를 던지지 않기 위해 null을 던지고 클라이언트에서 null 체크를 하는데,  체크하는 것을 깜빡할 수 있는게 문제다.

### 이것을 명시적으로 표현하는 방법이 생긴 것이다. (Optional)
비어있는 값이 전달될 수 있는 경우에 아래처럼 사용한다.
```java
public Optional<Progress> getProgress() {
}
```
- Optional로 감싸면, 박스를 만들어 객체를 넣는 것이다. 
    - 박스 안은 null일수도 있고, 무엇인가 들어있을 수도 있다.
- 박스안에 담을때 넣는 레퍼런스 타입(무언가가) null일 수도 있다.
- 박스에 넣는 방법
    - `Optional.ofNullable()`  
      null이 올 수 있을 때 사용한다. null이면 empty optional을 리턴한다.  
      즉 null을 파라미터로 넣으면, 아무것도 안넣은 것 취급한다.
    - `Optional.of()`  
      null이 오지 않아야할 때 사용한다.  
      만약 null이 오면 에러를 던진다. 


### 문법적 제한은 없지만, **리턴 타입에만** 쓰는게 권장사항이다.
#### 파라미터로 Optional을 쓰면 안된다.  
- 파라미터 자체가 null이 올수도 있다.   
  Optional을 쓰는 이유가 NullPointerException을 피해서 안전하게 쓰기 위함인데,   
  파라미터 자체로 null이 오게되었을 때, Optional의 메서드를 썼다가는 NullPointerException 발생.   
  Optional을 쓰는 의미가 없어진다.
- ex) `Optional x;` `x.isPresent()`를 쓰는데, x자체가 null이 될 수 있다는 의미다.

#### 맵의 key 타입에 Optional도 쓰지 않는다.  
**map의 중요한 특징이 key는 null이 아니다** 인데, 여기에 null이 올 수 있다는 여지를 줘선 안된다.

#### 인스턴스 필드타입에 Optional을 쓰지 않는다. 
- 도메인 클래스 설계의 문제다.
- Optional 대신 다른 방법을 찾는다.

#### primitive 타입을 옵셔널로 가능. But Optional.of(primitive 타입)은 X
- `OptionalDouble` 같은 primitive 타입을 위한 Optional 타입이 존재한다. 
- Optional.of(10)도 가능은 하지만 박싱, 언박싱이 일어난다. (콜렉션처럼) 그래서 성능에 안좋다.

#### 옵셔널 리턴타입으로 해놓고 null을 리턴하지 말자.... 
- 클라이언트 코드에서 Optional을 꺼내는줄 알고 null 체크를 안하고, Optional 메서드를 쓰려는데 NullPointerException이 발생하는 황당한 일이 발생한다.
- 리턴할게 없다면 `Optional.empty()`를 리턴하자.

#### 옵셔널로 다른 컨테이너 성격의 인스턴스들을 감싸지 말라. (Collection, Stream, Array, Optional에 Optional 등) 
- 왜냐하면 비어있다는 것을 이미 표현할 수 있는 타입들이다.
- 감싸면 두번 감싸게 되는 것이니 좋지 않다.


## Optional API
Optional을 사용하는 다양한 경우를 보자.
#### 스트림에도 Optional을 사용하는 오퍼레이션들이 있다. (종료형 오퍼레이션들)

#### `get()`으로 꺼낸다.

#### isPresent(), ifPresent() 
Optional에서 값을 꺼낼 때, 값이 존재하는지 확인하지 않고 그냥 꺼내는 경우 만약 값이 없으면 문제다. (`NoSuchElementException` - RuntimeException 계열)
- `if( optional.isPresent() )`로 확인할 수도 있겠지만, API를 사용하는게 권장된다.
- `optional.ifPresent( oc -> oc.doSomething() );`

#### 있으면 가져오고, 없으면 어떤 작업을 하고싶다. (`orElse()`)
`OnlineClass onlineClass = optional.orElse(createNewClasses());`
`orElse()`의 파라미터로는 Functional Interface가 아니라, 그냥 인스턴스가 들어간다.
   
#### orElseGet()
`orElse()`는 있을때도 만들고 없을때도 만든다.
- 즉, 값이 있으면 그 값을 리턴해주긴 하지만, 무조건 orElse()에 있는 메소드를 실행하고 본다. 
- 리턴을 안해줄 뿐이다.
```java
private static OnlineClass createNewClasss() {
        System.out.println("creating new online class");
        return new OnlineClass(10, "New class", false);
}

// onlineClass가 null이던, 아니던 일단 "creating new online class"는 출력이 된다.
// null이면 새로 만든 클래스를 리턴하고, 원래 값이 있다면 그 값을 가져온다.
OnlineClass onlineClass = optional.orElse(createNewClasss());
System.out.println(onlineClass.getTitle());
```

이런 일이 벌어지지 않았으면 할때 `orElseGet()` 이다.  여기에는 supplier가 들어간다.

#### orElse(), orElseGet() 언제 사용?
- `orElse()`는 이미 만들어진 인스턴스(상수로 만들어진 것등)을 참고해서 사용할 때 적합
- 동적으로 작업을 해서 만들어내는 경우 `orElseGet()` 적합

#### 뭔가 만들어줄 수 없는 경우엔 `orElseThrow()` 사용. 
기본은 NoSuchElementException 이지만, Supplier로 으로 원하는 예외를 던져줘도 된다.
`optional.orElseThrow( () -> new IllegalArgumentException("잘못된 값을 입력받았습니다."));`

(값이 있다는 가정하에) 값을 걸러내는 건 `filter(Predicate)` 
- 값이 없으면 아무 일도 일어나지 않는다.  
`Optional<OnlineClass> onlineClass = optional.filter(oc -> oc.isClose());`
  
#### map(), flatMap()
- `map()`이 복잡해지는 경우가 있는데, map()으로 꺼내는 타입 자체가 Optional인 경우다.
- `getProgress()`를 했는데, 이 메서드는 리턴 타입이 `Optional<Progress>`다. 
- 이 경우에 `flatMap()`을 쓴다.  