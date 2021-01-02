# 싱글턴 패턴
> `클래스의 인스턴스, 즉 객체를 하나만 만들어 사용하는 패턴`

- 커넥션 풀, 스레드 풀, 디바이스 설정 객체 등과 같은 인스턴스를 여러개 만들면 불필요한 자원을 사용하게 되고, 프로그램에 예상치 못한 결과를 초래하기도 한다.  
- 그래서 이런 경우 하나의 인스턴스만을 만들고 계속 재사용하는 `싱글턴 패턴`을 사용한다.

> 중요 포인트
> new를 실행할 수 없도록 생성자에 private 접근제한자를 지정한다.
> 유일한 단일 객체를 반환할 수 있는 정적 메소드가 필요하다.
> 유일한 단일 객체를 참조할 수 있는 정적 참조 변수가 필요하다.

## 예제
```java
public class Singleton {
    static Singleton singletonObject; // 정적 참조 변수

    private Singleton() {} // private 접근제한자

    static public Singleton getInstance() { // 정적 메소드
        if (singletonObject == null) {
            singletonObject = new Singleton();
        }

        return singletonObject;
    }
}
```
- 정적 메소드 `getInstance()`를 호출하면 정적 참조변수를 반환하도록 되어있다. (null 이면 생성후 반환)


```java
public class Client {
    public static void main(String[] args) {
        // private 생성자이므로 new를 통해 인스턴스를 생성할 수 없다.
        // Singleton singleton = new Singleton();

        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        Singleton s3 = Singleton.getInstance();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        s1 = null;
        s2 = null;
        s3 = null;
    }
}
```
- 위는 싱글턴 패턴을 사용하는 메인 메소드를 보여준다.  
- 주석 처리된 ` // Singleton singleton = new Singleton();`의 주석을 풀면 에러메세지가 나타난다.  
    - `Singleton` 클래스의 생성자가 private이기 때문에 외부에서 생성할 수 없기 때문이다.
- `System.out.println()`으로 객체를 출력하면 객체의 toString() 메서드가 호출된다.
    - 별도로 오버라이딩 하지 않았다면, 객체의 고유 값인 hashcode를 반환한다.
- 동일한 객체를 호출하고 있기 때문에 동일한 hashcode를 출력한다.
