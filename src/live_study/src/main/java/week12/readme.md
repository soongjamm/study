
# Annotation

### 목표
- 자바의 애노테이션에 대해 학습하세요.

### 학습할 것 (필수)
- 애노테이션 정의하는 방법
- @retention
- @target
- @documented
- 애노테이션 프로세서

--- 

## 어노테이션 이란?
어노테이션은 _컴파일 과정이나 실행 중 코드를 어떻게 처리할지_ 알려주는 메타데이터다.  

컴파일 과정이나 실행 중 코드를 처리하는 것은 보통 다음 3가지 이다.
- 컴파일러가 문법 오류를 체크할 수 있도록 정보 제공 (`@Override`)
- 소프트웨어 개발 툴이 빌드나 배치시 코드를 자동 생성할 수 있도록 정보 제공 (`Lombok`)
- 런타임에 특정 기능을 수행하도록 정보 제공 (`스프링 MVC에서 사용하는 기능들`)

어노테이션은 다음과 같이 명시한다.  
```
@AnnotationName
```  

<br>

## 어노테이션 정의하는 방법
어노테이션을 정의하기 위해선 다음 3가지가 필요하다.
- 기본적인 어노테이션 정의
- 어노테이션의 적용 대상 (`@Target`)
- 어노테이션의 유지 범위 (`@Retention`)

### 기본적인 어노테이션 정의
어노테이션도 클래스나 인터페이스와 같이 생성한다. 단 키워드가 `@interface` 가 된다.
```java
public @interface PrinterAnnotation() {
    
}
```
  
어노테이션도 엘리먼트를 가질 수 있다. 엘리먼트는 나중에 파라미터를 통해 설정할 수 있고 디폴트로 설정할 수도 있다.  
엘리먼트를 선언할 때는 메소드를 선언할 때 처럼 `()`를 붙여줘야 한다.
```java
public @interface PrinterAnnotation() {
    int value() default 5;
    String str(); // default 가 없으므로 파라미터를 통해 설정해야 함
}
```

### 어노테이션의 적용 대상 설정 (@Target)
어노테이션 정의 위에 `@Target` 으로 지금 정의하는 어노테이션을 어디에 사용할 것인지 _적용 대상을 정의_ 한다.  
`@Target`의 파라미터로는 `java.lang.annotation.ElementType`(Enum 상수)가 오고, 배열로 여러개를 넘길 수 있다.  
상수는 다음과 같다.
- **TYPE (클래스, 인터페이스, 열거 타입)**  
- **ANNOTATION_TYPE**  
- **FIELD**  
- **CONSTRUCTOR**  
- **METHOD**  
- **LOCAL_VARIABLE**  
- **PACKAGE**  

```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
public @interface PrinterAnnotation() {
    int value() default 5;

    String str(); // default 가 없으므로 파라미터를 통해 설정해야 함
}
```

### 어노테이션 유지 범위 설정 (@Retention)
어노테이션은 용도에 따라 유지 범위이 달라질 수 있다. 예를 들어 `@Override` 같이 컴파일러가 문법 오류를 지적하는 용도면 바이트코드에 정보를 유지할 필요 없이, 소스 코드상에서만 유지하면 된다.
이러한 유지 범위는 `@Retention` 을 통해 지정할 수 있고, 파라미터로는 `java.lang.annotation.RetentionPolicy` (Enum 상수) 가 온다.  
Enum 상수는 다음과 같다.
- **SOURCE**    
소스상에서만 어노테이션을 유지해서 바이트코드에 남지 않는다. 소스 코드 분석시에만 의미가 있다.
- **CLASS**  
바이트 코드에는 남지만, 런타임에 어노테이션의 정보를 가져올 수 없다.
- **RUNTIME**  
바이트 코드에 어노테이션 정보가 남고, 리플렉션을 이용해서 어노테이션의 정보를 가져올 수 있다.
  
코드 자동 생성 툴을 제작하지 않는 이상, 일반적으로 런타임에 리플렉션을 통해 어노테이션의 정보를 가져와 사용하는 목적으로 사용한다.

> 리플렉션(Reflection)은 런타임에 클래스의 메타정보를 얻는 기능을 말한다.

```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface PrinterAnnotation() {
    int value() default 5;

    String str(); // default 가 없으므로 파라미터를 통해 설정해야 함
}
```

<br>

## 리플렉션
위에서 만들어본 어노테이션 자체는 아무런 동작을 하지 않는다. 리플렉션을 이용해서 런타임에 어노테이션의 적용 여부를 읽고 엘리먼트 값을 이용해서 특정 동작을 수행할 수 있게 된다.  
클래스에 적용된 어노테이션을 가져오려면 `클래스명.class`를 통해 `java.lang.Class`를 이용하면 되고,  
필드, 생성자, 메소드에 적용된 어노테잉션은 다음 메소드들을 통해서 `java.lang.reflect` 패키지의 `Field`, `Method`, `Constructor` 타입의 배열을 얻어야 한다.  
- **getFields()** 
- **getConstructors()** 
- **getDeclaredMethods()** 

각 타입의 배열을 얻으면, 그 타입이 가진 다음의 메소드로 어노테이션 정보를 얻을 수 있다.
- **isAnnotationPresent(Class)**  
- **getAnnotation(Class)** : 지정한 어노테이션을 리턴
- **getAnnotations()** : 적용된 모든 어노테이션을 리턴 (상위클래스의 어노테이션 포함) 
- **getDeclaredAnnotations()** : 적용된 모든 어노테이션을 리턴 (상위클래스의 어노테이션은 X)


```java
public class Service {
    @PrinterAnnotation(value = "이것을 출력해주시오.")
    public static void printerMethod() {
        System.out.println("어노테이션 메세지를 출력합니다.");
    }
}
```

```java
public class Main {

    public static void main(String[] args) {
        Method[] declaredMethods = Service.class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(PrinterAnnotation.class)) {
                PrinterAnnotation printerAnnotation = method.getAnnotation(PrinterAnnotation.class);

                System.out.println(method.getName());
                System.out.println(printerAnnotation.value());
                System.out.println();
            }
        }
    }
}
```

```java
결과 :
printerMethod
이것을 출력해주시오.
```

<br>

## @Documented
어노테이션 정보가 javadoc 에 포함되도록 하는 어노테이션이다.  
javadoc 은 API를 HTML 문서화 시켜준 것인데, 흔히 구글링 하다보면 볼 수 있다. (예 -  [ArrayList](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html))

`@Documented` 적용  
![ex1](./src/live_study/screenshot/w12-1.png)

`@Documented` 미적용  
![ex2](./src/live_study/screenshot/w12-1.png)


<br>

## 어노테이션 프로세서
위에서 다룬 어노테이션은 주로 런타임에 리플렉션을 사용하여 활용하는 어노테이션을 다뤘다.     
반면, 어노테이션 프로세서는 컴파일 단계에서 어노테이션을 분석하고 처리하는 것을 말한다.  

어노테이션 프로세서를 사용한 예는 대표적으로 `롬복(Lombok)` 이라는 라이브러리가 있는데, 이것은 Getter, Setter, Constructor 등 필요하지만 반복적으로 생성해야하는 것들을 어노테이션으로 대신해주는 라이브러리다.  

```java
@Getter
public class Course {
    private String name;
    
    // 롬복 @Getter 덕분에 자동으로 get 메소드가 컴파잁 타임에 생성된다. 
}
```

어노테이션 프로세서를 사용하기 때문에 롬복을 사용시에 반드시 인텔리제이(를 사용한다고 했을 때)에서 `Preference` -> `Enable annotation processing` 에 체크해줘야 한다.

## 참고
https://mysend12.medium.com/java-annotation-processor-1-7f95693748ef
