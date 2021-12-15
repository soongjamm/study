> 정리를 하고도 왜 어댑터 패턴을 사용하는지 와닿지 않았는데, 다른 예제를 찾아보고 좀 더 와닿았다.
> https://jusungpark.tistory.com/22

# Adapter Pattern
> `어댑터 패턴은 합성, 즉 객체를 속성으로 만들어서 참조하는 디자인 패턴이다.`  
> `호출당하는 쪽의 메서드를 호출하는 쪽의 코드에 대응하도록 중간에 변환기를 통해 호출하는 패턴`

어댑터란 변환기(Converter)의 역할을 한다.  
서로 다른 두 인터페이스 사이에 통신이 가능하게 해주는 것이다.  
> - 휴대폰 충전기를 예로 들수있다.  
>   - 휴대폰을 직접 전원 콘센트에 꽂을 수 없기 때문에 우리는 충전기라는 것을 이용한다.
> - 또다른 예로 OBDC/JDBC나 플랫폼별(OS별) JRE를 들 수 있다.
>   - 환경에 따라 공통의 인터페이스인 ODBC 또는 JDBC를 선택/개발 
>   - --> ODBC/JDBC가 데이터베이스에 시스템을 단일한 인터페이스로 조작할 수 있게 해준다.
>
> JDBC와 JRE가 어댑터의 역할을 하는 것

객체지향의 5원칙 SOLID중 `개방 폐쇄 원칙(Open Closed Principle)`을 활용한 설계 패턴이라 할 수 있다.  

## 어댑터 패턴을 사용하면 나타나는 차이 (결과)
- 다음 예제는 어댑터 패턴을 적용하지 않았을 때와 적용했을 때의 차이를 보여준다.
<br>

### 1. 다음과 같은 일을 하는 서비스가 있다고 하자.
#### ServiceA, ServiceB
> - 둘은 비슷한 메세지를 출력하는 거의 비슷한 일을 한다.  
```
public class ServiceA {
        public void runServiceA() {
            System.out.println("Service A");
        }
    }
```
```java
public class ServiceB {
    public void runServiceB() {
        System.out.println("Service B");
    }
}
```

### 2. 위의 서비스들을 사용할 main() 메소드를 작성한다. (어댑터 X)
#### `ClientWithNoAdapter.java` 
> 어댑터 패턴을 적용하지 않은 코드
```java
public class ClientWithNoAdapter {
    public static void main(String[] args) {
        ServiceA sa1 = new ServiceA();
        ServiceB sb1 = new ServiceB();

        sa1.runServiceA();
        sb1.runServiceB();
    }
}
```
- `sa1.runServiceA();`와 `sb1.runServiceB();` 거의 같은 일을 하지만, 메소드의 이름이 다르다.

### 3. 어댑터를 사용했을 때 main() 메소드
####`ClientWithAdapter.java` 
> 어댑터 패턴을 적용한 코드
```java
public class ClientWithAdapter {
    public static void main(String[] args) {
        AdapterServiceA asa1 = new AdapterServiceA();
        AdapterServiceB asb1 = new AdapterServiceB();

        asa1.runService();
        asb1.runService();
    }
}
```
- 객체는 다르지만, 동일한 메소드명 `runService()`를 호출하게 되었다.


### 4. 그렇다면 어댑터는 어떻게 생겼나?
```java
public class AdapterServiceA {
    ServiceA sa1 = new ServiceA();

    void runService() {
        sa1.runServiceA();
    }
}
```
- 어댑터 합성, 즉 객체를 속성으로 만들어서 참조하고 있다.
- 속성으로 만들어서 그 속성이 가지고 있는 메소드를 수행하는 메소드를 가진다. (`runService()`)


### 결론
