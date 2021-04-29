> [live-study 11주차](https://github.com/whiteship/live-study/issues/11) 

## 목표
- 자바의 열거형에 대해 학습하세요.
## 학습할 것 (필수)
- enum 정의하는 방법
- enum이 제공하는 메소드 (values()와 valueOf())
- java.lang.Enum
- EnumSet
--- 

# enum
## enum(열거형) 이란?
- `enum`은 _계절_ 이나 _요일_ 처럼 하나의 집합을 상수로 정의할 수 있는 특별한 데이터 타입이다.
- 상수를 정의한다고 해서 서로 아무 관련이 없는 상수들을 정의해놓는 것은 바람직 하지 않다. 
    - 예를 들어 _JAVA_VERSION_ 과 _OS_VERSION_ 를 묶는 것은 바람직 하지 않다.
- 정의한 상수는 하나의 타입이 되어 **type-safety**하게 코드를 작성할 수 있게 해준다.

## enum 정의하는 방법
- 클래스나 인터페이스와 같은데, `class`/`interface` 키워드 대신 `enum`이 들어간다.
```java
public enum Season1 {
    SPRING, SUMMER, FALL, WINTER;
}
```

<br>

```java
public class Client {
  public static void printSeason(Season season) {

    switch (season) {
      case SPRING:
        System.out.println("봄 " + Season.SPRING);
        break;
      case SUMMER:
        System.out.println("여름 " + Season.SUMMER);
        break;
      case FALL:
        System.out.println("가을 " + Season.FALL);
        break;
      case WINTER:
        System.out.println("겨울 " + Season.WINTER);
        break;
    }

  }
  public static void main(String[] args) {
    printSeason(Season.WINTER);
  }
}
```
- 각각의 상수는 _Season_ 타입의 객체이다. 
- _printSeason()_ 은 **파라미터로 _Season_ 만을 받는다.**
  - **type-safe** 하다.  

<br>

```java
class Season {
    String name;
    public Season(String name) {
        this.name = name;
    }
}
```
```
public static final Season SPRING = new Season("SPRING");
public static final Season SUMMER = new Season("SUMMER");
...
```
- enum 상수를 선언하는 것은 위의 코드 처럼 객체를 생성하는 것과 같다.

<br>

#### enum 상수는 객체와 같기 때문에 데이터를 사용하거나 메소드를 사용할 수 있다.
```java
public enum Season {
    SPRING("봄", "1"),
    SUMMER("여름", "2"),
    FALL("가을", "3"),
    WINTER("겨울", "4");

    String season;
    String code;

    Season(String season, String code) {
        this.season = season;
        this.code = code;
    }

    public void print() {
        System.out.println(season + " code :" + code);
    }
}
```
- 상수에 작성한 데이터 순서대로 생성자 파라미터의 순서를 작성하면 된다.

```java
public static void printSeason(Season season){
        season.print();
}
```
```java
printSeason(Season.WINTER);
```
> 각 객체가 가진 값을 출력하는 메소드다.

> 각 객체는 컴파일 시점에 생성된다.  
> 생성자에 출력 메세지 `System.out.println(season + " 생성자"); `를 추가하고 실행시켜보면 확인할 수 있다.
> 
> ```
> 봄 생성자
> 여름 생성자
> 가을 생성자
> 겨울 생성자
> ```

<br>

#### 동명의 상수를 구분지을 수 있다.
```java
public enum Season {
SPRING("봄"), SUMMER("여름"), FALL("가을"), WINTER("겨울");
}
...
```

```java
public enum WebFrameworks {
    SPRING("스프링"), DJANGO("장고"), LARAVEL("라라벨"), EXPRESS("익스프레스");
}
...
```
- 만약 `public static final` 키워드로 상수를 정의할 경우 _Season_ 의 _SPRING_ 과 _WebFrameworks_의 _SPRING_ 을 구분할 수 없다.
- enum을 사용하면 `Season.SPRING` 과 `WebFrameworks.SPRING` 처럼 가시적으로 확인이 가능하고, **type-safe** 하다.

## enum이 제공하는 메서드 (values()와 valueOf())
#### values()
- 스태틱 메소드이다.  
- 해당 enum이 가진 모든 상수를 배열에 담는다. 주로 forEach 문과 사용된다.
```java
public enum WebFrameworks {
    SPRING, DJANGO, LARAVEL, EXPRESS;
}
```
```java
for( WebFrameworks framework : WebFrameworks.values() ) {
            System.out.println(framework);
}
```
```java
SPRING
DJANGO
LARAVEL
EXPRESS
```

#### valueOf()
- 스태틱 메소드이다.
- 파라미터로 상수의 이름을 받고, 이름과 매칭되는 상수 객체를 리턴한다.
```java
public enum WebFrameworks {
    SPRING("스프링"), DJANGO("장고"), LARAVEL("라라벨"), EXPRESS("익스프레스");

    private String name;

    WebFrameworks(String name) {
        this.name = name;
    }

    public void print() {
        System.out.println(name + " 입니다.");
    }
}
```

```java
WebFrameworks framework = WebFrameworks.valueOf("SPRING");
framework.print();
```
- _SPRING_ 상수 객체를 리턴한다.
- 해당 객체의 데이터와 메소드를 사용할 수 있다.
```
스프링 입니다.
```

## java.lang.Enum
- 모든 enum 클래스는 암시적으로 `java.lang.Enum` 을 상속받는다. 
  - 그렇기 때문에 상속을 받을 수 없다.
  - (자바는 다중상속이 불가능 하므로)


## EnumSet
- enum 을 위한 전용 `Set` 구현체다. 
- 내부적으로 `Vector`로 구현되어 있고, 효율적으로 구현되어서 파라미터가 Enum인 경우 `EnumSet`을 사용하면 공간/시간 복잡도 면에서 효율적이다.
  - `containsAll()` 이나 `retainAll()` 같은 bulk operation도 빠르게 수행한다.
- `Null` 을 허용하지 않고 `NullPointerException` 을 발생시킨다.
- 대부분의 콜렉션처럼 Synchronized 하지 않다. (thread-safe 하지 않다.)

#### allOf()
`EnumSet<Season> seasons = EnumSet.allOf(Season.class);`
- 파라미터로 주어진 Enum 클래스의 모든 상수를 가진 `EnumSet` 을 생성한다.

#### complementOf()
```java
EnumSet<Season> seasons = EnumSet.allOf(Season.class);
EnumSet<Season> seasonsComplemented;
seasons.remove(Season.SUMMER);
seasonsComplemented = EnumSet.complementOf(seasons);
```
`seasonsComplemented - SUMMER만 가지고 있다.`
- 파라미터로 주어진 `EnumSet`과 해당 타입의 여집합을 생성한다.
