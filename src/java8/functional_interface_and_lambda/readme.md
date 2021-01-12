

# 함수형 인터페이스와 람다 표현식

## 함수형 인터페이스와 람다 표현식이란?

-   인터페이스에 `추상메소드`가 _하나만_ 있는 것을 말함 (2개 있으면 X)
    -   디폴트나, 스태틱 메소드가 있는건 괜찮다.
-   함수형 인터페이스를 정의할일이 있으면 견고한 관리 위해 `@FunctionalInterface` 어노테이션을 붙여주자.
    -   (이런 어노테이션을 조작방법은 코드를 조작하는 다양한 방법 참고)
-   함수형 인터페이스를 정의했다면 구현하는게 `람다 표현식`이다.
    -   마치 인터페이스 타입의 참조변수에 함수를 정의한 것 처럼 보인다.

### 특징

-   함수를 First class object로 사용

    **First Class Object** = `First Class Citizen (일급시민)` [참고](https://jcsoohwancho.github.io/2019-10-18-1%EA%B8%89-%EA%B0%9D%EC%B2%B4(first-class-object)%EC%9D%B4%EB%9E%80/)  
    고차함수가 되어 함수가 함수를 매개로 받거나 리턴 값으로 보내는게 가능해진다.

-   주의할점 - `순수함수`

    -   매개변수와 리턴값은 항상 동일해야한다. 이것을 보장해주지 않으면 함수형프로그래밍이 아니다.
        -   매개변수와 리턴값 둘이 같아야 한다는게 아니라, 항상 매개변수는 똑같은 매개변수, 리턴값은 항상 똑같은 리턴값이어야 한다.
    -   예를들어
        -   람다식 밖에 있는 변수를 참조하는 경우 상태값을 갖는다고 한다.
            -   이 경우 문법적 오류는 아니지만 함수형 프로그래밍이 아니다.
            -   밖에 있는 변수를 참조하면 그 외부 변수를 `final`로 간주하기 때문에 허용되는데, 그렇기 때문에 이후에 그 외부 변수의 값을 변경할 수 없게 된다.
            -   함수형 프로그래밍은 전달받은 파라미터만 써야한다.
        -   **외부에 있는 값을 변경하려는 경우 문법적 오류다.**
    -   함수형 프로그래밍을 지켜서 써도 되고, 그냥 편의를 위해 써도 된다.

## 일반적으로 사용되는 함수인터페이스

-   대부분 [java.util.function](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html) 에 위치한다. (다른 곳에도 있긴하다.)
-   기존의 기능을 잘 쓰면 내가 새로운 함수형 인터페이스를 정의하지 않아도 되니까 잘 봐두자.

****Function****

-   어떤 값을 하나 받아서 리턴한다.

    -   리턴타입과 매개타입이 각각 제네릭 타입으로 정의되어 있다.
-   `apply()`라는 메소드만 정의해서 사용하면 된다. (위에서 정의한 RunSomething 인터페이스가 필요없게됌.)

    ```
    Function<Integer, Integer> plus10 = (integer) -> integer + 10; 
    ```

-   함수를 조합할 수 있다. ,

-   `compse()` : 곱하기2를 하는 multiply2 라는 함수가 있을 때, `plus10.compose(multiply2)`를 하면, multiply2를 먼저 계산하고 계산된 값을 다시 plus10의 인자로 쓰겠다는 말이다.

-   `andThen()` : compose() 와는 반대로 plus10을 먼저하고 multiply2의 인자로 쓰임.


****BiFunction****

-   **Function**과 똑같은데, 입력값을 2개를 받는다.

```
BiFunction<Integer, Integer, Integer> plusAB = (a, b) -> a + b;
```

**Consumer**

-   입력값을 1개를 받고 리턴값이 없다.
-   apply()가 아닌 `accept()` 메소드를 사용한다.

**Supllier**

-   \- 입력값을 받지 않고, 리턴값을 정의한다.

**Predicate**

-   어떠한 인자값을 하나 받아서 true, false를 리턴.

-   이것도 조합이 가능하다. (and, or, negate)

    -   `and()` : 뒤에 올 **Predicate**의 결과와 `and` 연산을 한다.

    -   `or()` : 뒤에 올 **Predicate**의 결과와 `or` 연산을 한다.

    -   `negate()` : 뒤에 올 **Predicate**의 결과와 `not` 연산을 한다.

    -   `test()` 메소드로 실행한다.

        ```
        Predicate<String> myNickname = nickname -> nickname.equals("soongjamm");
        Predicate<String> myPassword = password -> password.equals("p@ssw0rd");
        
        ```


```
myNickname.and(myPassword).test("p@ssw0rd"), // false  
myNickname.or(myPassword).test("soongjamm"), // true  
myNickname.test("soongjamm"), // true  
myNickname.negate().test("fake soongjamm"))); // true

```

**UnaryOperator**

-   Function 함수인터페이스의 특수한 케이스다.
-   입력한 값과 리턴값 타입이 같을 때 쓸 수 있다.
-   코드가 조금 더 깔끔해진다.
-   Function을 상속하고 있기때문에 andThen, compose 사용 가능하다.

**BinaryOperator**

-    BiFunction의 특수한 형태. 3개의 타입이 전부 같을 경우 줄여서 쓸 수 있다.

그 외에도 많은데, 비슷하니까 응용가능하니 문서를 확인하자.

## 람다식

-   인자가 없다면 빈 괄호`()`, 하나면 이름만, 두 개 이상이면 괄호안에 `(a, b)`

-   구현 코드가 한줄이면 중괄호`{}`없이, 두줄부터는 중괄호 필요함

    ```
    Supplier<Integer> give5 = () -> 5; // 매개값 없음
    IntConsumer printInt2 = (integer) -> System.out.println(integer); // 매개값 1개
    BiConsumer<Integer, Integer> print2Int = ((integer1, integer2) -> System.out.println(integer1+integer2)); // 매개값 2개 이상
    ```

-   익명 클래스나 로컬 클래스(메소드 내부에 정의한 클래스) 내부에서 외부의 변수를 캡쳐(사용)할수있음,

    -   `final` 또는 `effective final` 인 경우에만 참조할 수 있다.
    -   그렇지 않을 경우 concurrency 문제가 생길 수 있어서 컴파일러가 방지한다.
-   자바8이하에서는 변수 캡쳐를 위해 `final` 이라는 키워드가 붙어있어야 한다. (자바8 이상에서는 사실상 `final` 취급)

-   `effective final`(변수가 사실상 final인 경우) **로컬 클래스, 익명 클래스, 람다식 모두에서 참조가 가능하다.**

    -   테스트해본 결과, 로컬이나 익명클래스에서는 effective가 아니라, 쉐도잉되기 때문에 아예 관계가 없어지고, 람다식에서 `참조`할 때 effective final이 되는 것.
    -   effective final 변수인지 아닌지 어떻게 알까? -> 값을 바꿔보면 안다. effective final 변수는 바뀌지 않는다.
-   람다식 vs 로컬클래스, 익명클래스의 다른 점은 쉐도잉이다.

    -   람다는 쉐도잉이 되지 않는다.

        **쉐도잉이란?**  
        람다식은 scope가 람다식을 감싸고 있는 메소드랑 사실상 같다.  
        즉, 메소드에 있는 동일한 이름의 변수를 람다식에서 정의할 수 없다. (파라미터 변수로도 물론 안된다.)  
        반면 나머지 둘은 바깥에 있는 동명의 변수를 가려서, 내부에 있는 동명의 변수에 우선적으로 접근한다.


```
String var = "I'm outsider";
Consumer<String> lambda = (param) -> System.out.println(var + " " + param); 
// var = "inside" // 불가능. var는 effective final이다.

// Consumer<String> lambda = (var) -> System.out.println(var); // 컴파일 에러
// 람다식과 기존 var의 스코프가 같아서 람다식 내부에 var라는 이름으로 선언 불가능하다.
```

## 메소드 레퍼런스

람다식으로 기존 메소드나 생성자를 호출한다면, 레퍼런스는 그것을 더 간결하게 만들어준다.

`스태틱 메소드 레퍼런스`

-   스태틱 메소드를 레퍼런스로 정의한 것이다.

    ```
    // 스태틱 메소드 참조
    Consumer<Integer> staticMethod = (integer) -> RunSomething.staticPrint(integer);
    Consumer<Integer> staticMethod2 = RunSomething::staticPrint;
    BiConsumer<Integer, Integer> staticMethod3 = RunSomething::staticPrint2;
    ```

-   알아서 입력값을를 메소드의 매개값으로 매칭해준다.

-   staticPrint2는 2개의 인자가 필요하기 때문에, 애초에 IDE 자동완성에 staticPrint는 뜨지도 않는다.


**특정 객체 인스턴스 메소드 레퍼런스**

-   인스턴스 메소드는 객체를 생성해서 사용한다.

-   인스턴스 메소드 레퍼런스도 생성된 객체가 필요한데, 람다식의 파라미터로 온 값이 그 인스턴스다.

    ```
    // 특정 객체의 인스턴스 메소드 참조
    Function<String, Integer> instanceMethod = (str) -> str.length();
    Function<String, Integer> instanceMethod2 = String::length;
    Arrays.asList(companies).stream()
          .forEach(String::toUpperCase); 
    ```

-   첫 번째 줄을 보면 `String` 타입의 str이라는 파라미터가 있고, 그 파라미터의 인스턴스 메소드인 `length()`를 호출한다.

-   둘째 줄에서 String은 첫째줄 파라미터인 `str`의 타입 `String`이고, 그 인스턴스 메소드 `length`를 호출한다.


**생성자 메소드 레퍼런스**

-   생성자도 가능. 생성자의 리턴값은 그 객체의 타입이다.

    -   입력값은 없는데 결과값은 있는 Supplier를 써서 연습해보자

    -   입력값이 있는 생성자는 Function을 써서 연습해보자

        ```
        Supplier<Greeting> greet2 = Greeting::new;
        Function<String, Greeting> greet4 = Greeting::new;
        ```


**임의 객체 인스턴스 메소드 레퍼런스**

-   특정 타입이기는 한데, 대상이 불특정 다수이다.

    Comparator가 자바8부터 @FunctionalInterface로 바뀌어서, 추상메소드는 compare()하나지만 많은 디폴트/스태틱 메소드가 있다.  
    그렇기때문에 람다식을 넣을 수 있다. -> 메소드 레퍼런스를 쓸 수 있다.


```
String[] names = {"soong", "jamm", "woo"};
Arrays.sort(names, new Comparator<String>() {
    @Override
    public int compare(String o1, String o2) {
        return o1.compareToIgnoreCase(o2);
    }
});
Arrays.sort(names, (o1, o2) -> o1.compareToIgnoreCase(o2));
Arrays.sort(names, String::compareToIgnoreCase);
```

-   Arrays.sort()의 두번째 인자는 `Comparator`가 와야한다.
-   Comparator의 추상메소드는 `compare()` 이므로 이 메소드가 최종적으로 구현되어 있어야 한다.
    -   문자열이 다른 문자열하고 **비교**를 해서 인트값을 넘겨주는 메소드를 참조할 수가 있다. (`compareToIgnoreCase`)
        -   `compareToIgnoreCase`는 `CASE_INSENSITIVE_ORDER`라는 Comparator 타입이 `compare()`의 결과를 리턴한다.
        -   내부적으로 자기 자신이랑 파라미터로 받은 것과 비교해서 int값을 넘겨주게 되어있다.
-   정리하자면
    -   compareToIgnoreCase()를 실행하면 `soong`과 `jamm`을 비교해서 int를 리턴하고, 두번째로는 `jamm`과 `woo`를 비교해서 int를 리턴한다.
    -   그래서 **임의의 인스턴스들이 compareToIgnoreCase()라는 인스턴스 메소드를 거쳐간다.**
    -   얼핏 `String::compareToIgnoreCase`를 보면 String에 compareToIgnoreCase 라는 스태틱 메소드가 있는것 처럼 보이지만, 아니다.
    -   임의의 스트링 객체들의 인스턴스 메소드들을 참조한 것이다.
-   `(o1, o2) -> o1.compareToIgnoreCase(o2)`는 메소드 레퍼런스 `String::compareToIgnoreCase` 로 표현된다.
    -   `(x, y) -> x.method(y)`가 된다.