# 예외 처리

자바의 예외 처리에 대해 학습하세요.

학습할 것 (필수)
자바에서 예외 처리 방법 (try, catch, throw, throws, finally)
자바가 제공하는 예외 계층 구조 o
Exception과 Error의 차이는? o
RuntimeException과 RE가 아닌 것의 차이는? o
커스텀한 예외 만드는 방법

## 예외(Exception) ?
- 사용자의 잘못된 조작이나, 개발자의 잘못된 코딩으로 발생하는 프로그램 오류다.
- 예외가 발생하면 프로그램이 곧장 종료된다.
- 그러나 `예외 처리`를 하면 프로그램을 종료하지 않고 실행 상태를 유지할 수 있다.
 
> ### 에러(Error)와의 차이점
> - 컴퓨터 하드웨어 오작동이나 고장으로 인한 프로그램 실행 오류는 `에러`라고 한다.
> - 에러가 발생하면 JVM 실행에 문제가 생긴 것으로, 잘 만들어진 프로그램을이라도 실행이 불가능해진다.
> - 개발자가 대처할 수 없다.

## 예외 처리(Exception Handling)
- 예외 처리에는 `일반 예외`와 `런타임 예외` 두 가지가 있다.
- 두 가지 예외 모두 예외 처리가 필요하고, 자바에서는 `클래스로 예외를 관리`한다.
- 예외가 발생하면 정해진 클래스를 생성하고 예외 처리 코드에서 **이 예외 객체를 사용할 수 있다.** 
- 예외 클래스는 모두 `java.lang.Exception` 클래스를 상속받는다.

[##_Image|kage@cm03PG/btqTyBzEYdB/s46WDR3xcmKtuX8ma37pMk/img.jpg|alignCenter|data-origin-width="0" data-origin-height="0" width="569" height="NaN" data-ke-mobilestyle="widthContent"|http://java5tutor.info/java/flowcontrol/exceptionover.html||_##]

### 일반 예외 (Exception)
- 컴파일 단계에서 예외 처리 코드가 필요한지 검사하기 때문에 `컴파일러 체크 예외`라고도 한다.

### 실행 예외 (Runtime Exception)
- 컴파일 단계에서 예외 처리 코드를 **검사하지 않는** 예외이다.
- `RuntimeException`을 상속받는 클래스들이다.
  - `RunTimeException`도 `Exception`을 상속받고 있다.
- JVM은 `RuntimeException`을 상속했는지에 따라 실행 예외를 판단한다. 

### 자주 발생하는 실행 예외 종류
- **NullPointerException**  
    어떤 객체를 참조할 때, `.`를 이용하게 되는데, 이때 `참조 대상이 null 객체`일 때 생기는 예외다.
    ```java
    ArrayList<Integer> list = null; // 참조 변수만 생성한 상태. 현재 참조 대상이 없는 null
    list.get(1); // exception 발생
    ```
- **ArrayIndexOutOfBoundsException**  
    범위를 벗어나는 배열 인덱스에 접근하려고 할 때 발생하는 예외다.
    ```java
    int[] arr = new int[3]; // 크기가 3인 배열은 인덱스가 0부터 2까지 존재한다.
    arr[3] = 3; // 범위를 넘어서 인덱스에 값을는 할당하려 해서 exception 발생 
    ```  
- **NumberFormatException**  
    숫자가 아닌 문자열을 숫자 타입으로 변경하려 할 때 발생하는 예외다.
    [Integer같은 Wrapper 클래스](https://soongjamm.tistory.com/120) 에는 '123' 처럼 숫자로된 String 타입 객체를 숫자로 바꿔주는 `parseInt()` 메소드가 있다.  
    이 때, 문자열에 숫자가 아닌 문자가 포함되어 있으면 예외가 발생한다.
    ```java
    String string123 = "123";
    String wrong123 = "123aaa";
    int integer123 = Integer.parseInt(number123);
    int integer123 = Integer.parseInt(wrong123); // wrong123에 숫자가 아닌 문자(a)가 포함되어 있어서 exception 발생
    ```
  
- **ClassCastException**  
    변환할 수 없는 타입에 억지로 캐스팅을 시도했을 때 나타나는 예외다.  
    예를 들어, Animal 클래스를 상속하는 Dog 와 Cat 클래스가 있다고 했을 때 다음 코드를 보자.
    ```java
    Animal cat = new Cat();
    Cat cuteCat = (Cat) cat;
    ```
    `Animal cat`은 Animal 타입이지만, 실제 객체는 `Cat` 타입이기 때문에 `Cat cuteCat`으로 캐스팅이 가능하다.

    하지만 다음을 코드를 보자.
    ```java
    Animal cat = new Cat();
    Dog cuteDog = (Dog) cat; // cat은 Animal 타입 변수지만, Cat 타입을 참조하고 있기 때문에 exception 발생
    ```
    위에 예제처럼 Animal 타입인 cat을 Dog로 캐스팅하려고 하지만, **cat이 가리키고 있는 실제 객체는 Cat 타입이기 때문**에 exception이 발생한다.

    캐스팅을 할 때는 타입변환이 가능한지 `instanceof`를 통해 확인이 가능하다. (그러나 빈번한 사용한 안티패턴으로 간주되니 꼭 필요할 때만 쓰자.)
    ```java
    Cat cuteCat;
    if (cat instanceof Cat) {
        cuteCat = (Cat) cat;
    } 
    ```
    

## 자바에서 예외처리 방법 (try, catch, throw, throws, finally, resource)
### try, catch, finally
기본적인 예외처리 방법은 `try`문에 실행 코드를 넣고 `catch(예외클래스 e)`에 예외 발생시 실행할 코드를 넣으면 된다.  
`finally` 문 안에는 예외 발생 여부와 상관 없이 반드시 실행할 코드를 넣는다.  

아래 코드는 입력받은 숫자에 따라서 고양이가 그르렁거리는 코드다.  
잘못된 값을 입력받으면 예외 처리로 재입력을 받고,  
예외 발생 여부와 상관없이 _나는 귀여운 고양이다._ 를 출력한다.  
```java
public void growlSound(int number) {
        Scanner scanner = new Scanner(System.in);
        String[] grwolSound = {"그릉", "그르렁", "그르러엉", "멍멍야옹왈왈!!!"};
        System.out.println(grwolSound[number]);

        try {
            System.out.println(grwolSound[number]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("growl을 넘버를 잘 못 선택하셨습니다.");
            System.out.println("다시 입력하세요.");
            growlSound(scanner.nextInt());
        } finally {
            System.out.println("나는 귀여운 고양이다.");
        }
    }
```
> 위의 코드처럼 **catch문 안에 재귀적으로 코드를 호출**해서 재입력을 받을 수도 있다.

### 다중 catch
여러 상황에 대해 예외처리를 하고싶다면 다중으로 catch문을 사용할 수 있다.
```java
try {
    ArrayList<Integer> arrayList = null;
    arrayList.get(0);

    Integer.parseInt("난 숫자가 아니라 못바꾸지"); // 
} catch (NumberFormatException e) {
    System.out.println("number foramt exception !");
} catch (NullPointerException e) {
    System.out.println("null pointer exception !");
} finally {
    System.out.println("화이팅~");
}
```
> exception은 발생하는 순간 프로그램이 정지하기 때문에, 하나의 예외처리만 실행된다.
> 위의 코드에서는 **null에 대한 접근이 먼저 발생**하므로 `NullPointerException`이 발생한다.

**catch 순서**  
catch 블록은 위에서부터 아래로 실행되면서 적절한 예외처리를 찾아가기 때문에 상위 예외 클래스를 아래에 두는게 적절하다.
```java
try {
    ...
} catch (IllegalArgumentException e) {
    ...    
} catch (Exception e) {
    ...
}
```
> catch(Exception e)가 위에 있을 경우, 모든 예외 발생시 `catch(Exception e)` 블록에서 처리하므로  다른 예외처리가 의미 없어진다.

**multi catch** (자바7 부터 가능)  
서로 다른 예외지만, 하나의 블록에서 같은 처리를 원할 경우 multi catch를 사용할 수 있다.  
catch() 괄호 안에 예외클래스를 or`(|)`로 구분해주면 된다.  
  
```java
try {
    ...
} catch (IllegalArgumentException | NullPointerException e) {
    ...    
}
```

### 자동 리소스 닫기 (try-with-resources) (자바7 부터 가능)
예외발생 여부와 상관없이, 사용한 자원 객체들에 대해 `close()`를 호출하여 안전하게 리소스를 닫아줄 수 있다.
- 리소스 객체는 반드시 `java.lang.AutoCloseable` 인터페이스를 구현하고 있어야 한다.
- `AutoCloseable`에 `close()`가 정의되어 있고, try-with-resource는 이 `close()`를 자동 호출한다.

> **리소스란?**
> 각종 입출력 스트림, 서버 소켓, 소켓, 각종 채널등을 의미하며, 간단하게 데이터를 읽고 쓰는 객체라고 생각할 수 있다.  
  
**자바7 이후 리소스 닫기**
이전에는 `try` 뒤에 바로 중괄호`{}`였지만, 리소스를 사용할 땐 괄호`()`에서 자원을 생성한다.   
여러개의 자원이 올때는 semi-colon`;`으로 구분짓는다.
- close()를 명시하지 않았지만, 안전하게 리소스를 닫을 수 있다.
```java
public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("file1.txt");
            FileOutputStream fos = new FileOutputStream("file2.txt")){
            // code
        } catch (IOException e) {
            // code
    }
}
```

**자바7 이전에 리소스 닫기**  
try문 안에서 객체를 생성하고, 안전하게 리소스 객체를 닫기위해서 finally문 안에 if문 - try문이 중첩되었다.
```java
FileInputStream fis = null;
try {
    fis = new FileinputStream("file.txt");
    ...
} catch (IOException e) {
    ...
} finally {
    if(fis != null) {
        try {
            fis.close();
        } catch (IOException e) {
            ...
        }
    }    
}
```

### throws로 예외 떠넘기기
- 메소드 내부에서 예외가 발생하면 try-catch 의 catch 블록에서 처리하는게 기본이지만, 예외를 떠넘기는 방법도 있다.
- `throws 예외 클래스명` 키워드를 메소드 옆에 작성한다. 
- 예외를 떠넘기면, 현재 메소드를 호출한 곳에서 예외처리를 하도록 만든다.
```java
public class Main extends Animal {
    public void growlSound(int number) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String[] grwolSound = {"그릉", "그르렁", "그르러엉", "멍멍야옹왈왈!!!"};
        System.out.println(grwolSound[number]);
    }
}

 public static void main(String[] args){
    Cat cat = new Cat();
    try{
        cat.growlSound(1);
    }catch(Exception e){
        // ...
    }
}
```
- growlSound() 에서 발생하는 예외는 해당 메소드를 호출한 곳에서 처리하도록 `throws Exception`을 붙여주었다.  
- 위 코드에서는 main() 메소드가 호출하였으므로, main() 에서 예외를 처리한다.
    - 즉, `throws` 키워드가 붙은 메소드를 호출할 땐 반드시 `try-catch`문을 작성해서 예외처리를 할 수 있어야 한다.
    - 만약 호출한 곳에서 예외처리를 하지 않으려면, 그 메소드에서도 `throws`를 사용해서, 호출한 메소드를-호출한 메소드를-호출한 곳으로 예외처리를 떠넘겨야 한다.

> 그러나 main()에 throws Exception을 붙이는 것은 좋지 않다. 
> 사용자는 알 수 없는 예외 내용을 출력받고 프로그램이 종료되기 때문이다. 구체적인 예외처리가 필요하다.

### throw로 예외 발생시키기
- 잘못된 코딩이나 잘못된 조작으로 예외가 발생되기도 하지만, `throw` 키워드로 개발자가 직접 예외를 발생시킬 수도 있다.
- 위에서 보았던 예외들과 마찬가지로, 예외를 발생시키면 catch 블록에서 예외를 처리한다.
- 직접 예외 메세지를 작성할 수도 있다.
```java
int year = scanner.nextInt();
if (year > 2020) {
    throw new IllegalArgumentException("벌써 1년이 지나갔을리가 없습니다.");
}
```
- 위의 코드처럼 예외를 던지면, 해당 메소드를 호출했던 try-catch문의 catch 블록으로 가서 예외를 처리한다.
- 예외메세지는 catch 블록에서 `getMessage()`를 통해 확인할 수 있다.
```java
try {
    cat.growlSound(1);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```



## 사용자 정의 예외 클래스
- 자바 API에서 제공하는 예외 클래스만으로는 부족할 때, 직접 예외 클래스를 정의할 수 있다.
    - 예를 들어 은행 시스템에서 잔액 부족 같은 예외는 자바 API로 처리할 수 없다.
    - 이 경우 새로 정의하면 된다.
> 이러한 애플리케이션 서비스 관련 예외를 애플리케이션 예외(Application Exception) 이라 한다.

- 클래스 작성 방법은 기존 클래스와 같고 일반 예외면 `Exception`을, 실행 예외면 `RuntimeException`을 상속하면 된다.
```java
public class MyException extends RuntimeException {
    public MyException() { }

    public MyException(String message) {
        super(message);
    }
}
```
- 보통은 생성자 선언만 포함한다.  
- 클래스의 이름은 Exception으로 끝나게 하는 것이 좋다.