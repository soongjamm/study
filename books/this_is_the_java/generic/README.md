## 제네릭
### 제네릭을 쓰는 이유
- 제네릭 타입을 이용함으로써 잘못된 타입이 사용될 수 있는 문제를 컴파일 과정에서 제거할 수 있다.
- 타입 변환을 제거하여 비제네릭 코드의 불필요한 타입 변환에서 오는 성능상 악영향을 제거할 수 있다.
- API 도큐먼트를 보면 제네릭 표현이 많이 때문에 제네릭을 이해하지 못하면 API 도큐먼트를 이해하기 어렵다.

### 제네릭 타입
- 타입을 파라미터로 가지는 클래스와 인터페이스를 말한다.
- 이름 뒤에 `< >`가 붙고 사이에 타입 파라미터가 위치한다. ex) `<T>`   

**사용하는 이유**  
제네릭 타입을 사용하지 않을 때 모든 종류의 객체를 저장하기 위해서 `Object` 클래스를 이용한다.  

```java
public class Box {
    private Object object;
    public void set(Object object) { this.object = object; }    
    public Object get() { return object; }
}
``` 
위과 같이 Box가 있을 때, 박스 정보를 받으려면 강제 타입변환을 해야한다.

```java
box.set("홍길동");
String name = (String) box.get();
```

제네릭을 사용하면 Box 클래스는 다음과 같이 변한다. 
```java
public class Box<T> {
    private T t;
    public T get() { return t; }
    public void set(T t) { this.t = t; }    
}
```

객체를 생성하고 나면 `private String t` 와 같이 자동으로 재구성 된다.  
> Box 클래스를 이용하는 코드에서 더이상 강제타입변환을 사용하지 않는다.
> `Box<String> box = new Box<>()`과 같이 호출하며, String이 아니라 Integer를 사용하는 Box를 사용하고 싶다면 다음과 같이 다시 생성해야 시한다.  
> `Box<Integer> box2 = new Bo<>()`

### 멀티 타입 파라미터
- 위에 언급한 일반 제네릭 타입과 같은데 타입이 여러개 들어간 것 뿐이다.
```java
public class Product<T, M> {
    ...
    public void setKind(T kind) { this.kind = kind; }
    public void setModel(M model) { this.model = model; }   
}
```
위와 같이 `,`를 구분자로 하여 타입을 추가한 뒤 클래스를 선언하고

객체 생성시에는 `Product<Tv, String> product = new Product<>();` 와 같이 생성하고
```
product.setKind(new TV())
product.setModel("스마트tv")
```
위와 같이 사용한다.

> 자바 6 이전에는 `Product<Tv, String> product = new Product<Tv, String>();` 과 같이 중복적으로 타입 파라미터를 설정해줘야 했다.  
> 하지만 자바7 부터 생략 가능하다. 


### 제네릭 메소드 - (CompareMethodExample.java)
제네릭 메소드도 같은 개념이다. **매개 타입**과 **리턴 타입**으로 타입 파라미터를 갖는다.  
리턴 타입 앞에 <> 기호를 추가하고 타입 파라미터를 기술한다.
```java
public class Util{
    public static <T> Box<T> boxing(T t) {
        Box<T> box = new Box<T>();
        box.set(t);
        return box;
    }    
}
```
호출 방법은 두가지가 있다.
```
Box<Integer> box = <Integer>boxking(00); // 명시적으로 구체적 타입을 지정
Box<Integer> box = boxking(100); // 매개값을 보고 구체적 타입을 추정
```

### 제한된 타입 파라미터 (<T extends 최상위타입>) - (BoundedTypeParameterExample.java)
- 타입 파라미터의 구체적인 타입이 제한되어야 할 때 사용한다.
    - Ex) 숫자를 연산해야 할 때는 Number 타입 또는 하위 클래스(Byte, Short, Integer, Long, Double)의 인스턴스만 가능하다
- 타입 파라미터 뒤에 extends 키워드를 붙이고 상위 타입을 명시하면 된다.
    - `public <T extends 상위타입> 리턴타입 메소드(매개변수...) {...}`