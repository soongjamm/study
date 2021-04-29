# 디폴트 메소드
- 기존의 인터페이스에 기능을 추가하려고 추상메소드를 추가하면, 모든 구현체에서 오버라이딩을 해야하는 문제가 생긴다.
  - 디폴트 메소드가 생기기 전에는 추상 클래스로 implements해서 비어있는 메소드로 오버라이드 하고, 그 추상 클래스를 상속받는 식으로 사용했다.
  - 이 방법의 문제점은 자바에서 상속은 1번밖에 못하므로 상속에 제한이 걸린다는 것이다.
- 그래서 인터페이스에서 이미 정의가 가능한 `기본 메소드(default method)`를 제공한다. (자바8 부터)
- 구현체에서 재정의 또한 가능하다.
- 항상 잘 동작하라는 보장이 없다. 그래서 문서화가 중요하다.
```java
public interface Foo {
    void printName();
    
    String getName();
    
    /**
     * @implSpec 이 구현체는 getName()으로 가져온 문장을 대문자로 바꿔 출력한다.
     */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    }
}
```
- `getName()`에서 null이 올 지도 모르고, 이름이 안올지도 모른다.
- 그래서 `@implSpec` 으로 문서화를 해준다.
- `Object`에서 제공하는 메소드들 (equals(), hashcode()등)은 인터페이스에서 디폴트로 재정의 할 수 없다. (컴파일 에러)
    - 추상으로 선언하는건 가능하다. (특별한 의미가 있는 경우에만)
- 상속받은 인터페이스가 기존의 default method를 다시 추상 메소드로 만들 수 있다.
    - 대신 그 하위 인터페이스의 구현체는 반드시 그 추상 메소드를 구현해야한다.

```java
public interface Bar extends Foo{
    void printNameUpperCase();
}
```

- 구현체가 구현하는 두 인터페이스에서 동일한 이름의 default method를 가지고 있을 경우, 반드시 재정의 해야한다.

> 해당 타입 관련 유틸리티나 헬퍼메소드는 `static method`로 구현하면 된다.   
> static은 default와 구현은 똑같이 하지만, 오버라이드는 안된다.
> 그리고 접근 방법이 `인터페이스.메소드()` 이다.


## 자주 사용되는 디폴트 메소드
> Collection 인터페이스는 Iterable 인터페이스를 상속받는다.
### Iterable의 기본 메소드
**forEach()**
`names.forEach(System.out::print);`

**spliterator()**
- `spliterator()`로 객체를 만들고
- `trySplit()`으로 객체를 반으로 나눈다. (기존의 객체는 절반이 사라지고, 나머지 절반을 리턴한다.)
- `tryAdvance`로 한 element씩 가져온다. (hasNext()를 하는 것처럼 while 문 안에서 사용가능)
```java
Spliterator<String> spliterator = names.spliterator();
Spliterator<String> spliterator2 = spliterator.trySplit();
while(spliterator.tryAdvance(System.out::println));
```


### Collection의 기본 메소드**
**stream() / parallelStream()**
> stream을 다룰때 자세히 설명
- 기본 메소드로 제공. 내부를 보면 `spliterator()`를 사용하고 있다.

**removeIf(Predicate)**
**spliterator()**

### Comparator의 기본 메소드 및 스태틱 메소드
**reversed()**
**thenComparing()**
**static reverseOrder() / naturalOrder()**
**static nullsFirst() / nullsLast()**
**static comparing()**

> 코드에 의미는 없지만, 이런 방법으로 쓸 수 있다.
```java
// Comparator
Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
Comparator<String> compareTo = String::compareTo;
names.stream()
       .sorted(compareToIgnoreCase.reversed().thenComparing(compareTo));
```