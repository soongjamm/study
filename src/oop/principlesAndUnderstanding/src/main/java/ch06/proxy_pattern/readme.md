# 프록시 패턴
> `제어 흐름을 조정하기 위한 목적으로 중간에 대리자를 두는 패턴`

- 실제 서비스 객체가 가진 메소드를 직접 실행하지 않고, 대리자 객체를 대신 투입해 실행하는 패턴이다.  
- 클라이언트 쪽에서는 실제 서비스를 통해 실행하는지, 대리자 객체를 통해 실행하는지 알 수 없다.
- 대리자를 둠으로써 실제 서비스의 메소드 호출 전후로 별도 로직을 수행하는 방식으로 제어 흐름이 가능하다.  
- `개방 폐쇄 원칙(OCP)`와 `의존 역전 원칙(DIP)`를 적용한 패턴이다.

## 예제
- 클라이언트에서 어떠한 서비스를 실행하고 싶다.  
- 서비스에서는 랜덤숫자를 생성해서 짝수일때만 서비스를 실행하게 하고싶다.  
그래서.. 
- 클라이언트에서는 직접 서비스를 실행하는 대신, `프록시를 호출`하고
- 프록시에서는 서비스에 있는 실행가능여부 확인 메소드`possibleRunning()`를 실행하고
- 반환값이 true일 때, 실제 서비스`runSomething()`를 실행한다.    


`ClientWithProxy.java`
```java
public class ClientWithProxy {
    public static void main(String[] args) {
        // 프록시를 이용한 호출
        IService proxy = new Proxy();
        System.out.println(proxy.runSomething());
    }
}
```

`Proxy.java`
```java
public class Proxy implements IService {
    Service service;

    @Override
    public String runSomething() {
        System.out.println("호출에 대한 흐름 제어가 주목적, 반환 결과를 그대로 전달");
        service = new Service(RandomNumber.generate());
        System.out.println(service.randomNumber);
        if (service.possibleRunning()) {
            return service.runSomething();
        }
        return "실패했습니다.";
    }
}
```

`Service.java`
```java
public class Service implements IService {
    int randomNumber;

    public Service(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    public String runSomething() {
        return "서비스를 실행합니다.";
    }

    public boolean possibleRunning() {
        if (randomNumber%2 == 0) {
            return true;
        }
        return false;
    }
}
```


`IService.java`
```java
public interface IService {
    String runSomething();
}
```