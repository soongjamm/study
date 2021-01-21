# 멀티쓰레드 프로그래밍

## 프로세스(Process)와 스레드(Thread)란 무엇인가?

스레드를 알기 전에, 프로세스를 먼저 알아야 한다.
`프로세스`는 운영체제에서 실행중인 (보통)하나의 응용 프로그램 단위를 말한다. 하나의 응용 프로그램을 실행하면 운영체제로부터 그에 필요한 메모리를 할당받아 독립적인 공간을 가지고 있다.  
`스레드`는 프로세스가 가지는 **작은 프로세스**이자 하나의 실행 환경이라고 볼 수 있다. 프로세스는 최소 하나의 스레드를 가지며, 자원을 공유한다. (메신저에서 파일을 보내는 것, 메세지를 보내는 것 각각이
스레드라고 볼 수 있다.)
프로세스 내부에서 이러한 스레드가 여러개 존재하면 `멀티 스레드`이다.

#### 멀티 스레드의 특징

- 멀티 스레드는 동시에 무언가를 처리하는 프로그램을 만들기 위해서 사용한다.
    - 메신저에서 메세지를 보내면서 파일 전송을 같이 하는 행위 등
- 하나의 프로세스에서 자원을 공유하기 때문에 하나의 스레드에 이상이 생기면 다른 스레드에도 영향을 미칠 수 있다.
- 그렇기 때문에 예외 처리가 중요하다.

## Thread 클래스와 Runnable 인터페이스

- `Thread` 클래스는 스레드 관리와 관련된 다양한 메소드가 정의가 되어있다.
- 스레드 객체를 생성하려면 반드시 스레드가 처리할 코드를 작성해야하는데 두 가지 방법이 있다.
    1. Thread 클래스로부터 직접 생성 (Runnable 인터페이스를 구현)
    2. Thread의 서브클래스로부터 생성

#### Runnable 구현 vs 서브 클래스?

`Runnable` 객체를 구현하는 것이 일반적이다.

- 자바는 다중상속이 불가능하기 때문에 Thread를 상속받으면 다른 클래스를 상속받을 수가 없다.
- 반면 Runnable은 인터페이스이기 때문에 다른 클래스를 상속받을 수 있다.
- 그렇기 때문에 Runnable 구현 설명에 조금 더 집중한다.

### 스레드를 생성하는 방법 - 1. Thread 클래스로부터 직접 생성 (Runnable 객체 구현)

클라이언트 쪽에서 `Thread thread = new Thread(Runnable runnable)` 로 스레드를 생성할 수 있다.\

그러기 위해선 저 `Runnable` 이라는 인터페이스를 구현해야 한다.

- `Runnable`은 **`run()` 메소드만을 갖고 있는** 함수적 인터페이스이다.
- 즉 인터페이스이므로 아무런 기능도 정의되어 있지 않다. 직접 필요한 기능을 작성하면 된다.

```java
class BabyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("응애 애기 스레드 탄생");
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        Thread thread = new Thread(new BabyRunnable());
        thread.start();
    }
}
```

쓰레드 생성자의 파라미터로 Runnable 구현 클래스를 넣어서 쓰레드 객체를 생성한다.

- **중요한 것은 스레드 객체의 `.start()` 메소드를 호출해야 스레드가 실행된다.**

<br>

<br>

위에서  `Runnable`이 함수적 인터페이스라고 언급했다. 함수적 인터페이스는 코드를 줄이기 위해 람다식을 이용할 수도 있다!

```java
Thread lambdaThread=new Thread(()->System.out.println("나는 람다 출신 스레드"));
        lmabdaThread.run();
```

### 스레드를 생성하는 방법 - 2. Thread의 서브 클래스로부터 생성

Thread 클래스를 상속받는 서브 클래스에 코드를 작성하는 방법이다.  
마찬가지로 서브 클래스 객체의 `start()`를 호출해야 스레드가 실행된다.

```java
class SubClassThread extends Thread {
    public void run() {
        System.out.println("서브 클래스를 이용!");
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        Thread subClassThread = new SubClassThread();
        subClassThread.run();
    }
}
```

### 멀티 스레드의 효과를 느껴보기

이 세상은 매 순간 아기들이 태어나기고 하고 군인들이 훈련을 하기도 한다.   
아기가 태어나는 일과 군인이 훈련하는 일은 동시에 일어날 수도 있다. 순서가 없다.  
이러한 세상을 코드로 표현하고 싶다.

#### 멀티 스레드가 없는 세상

```java
class Hospital {
    public void babyIsBorn() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("응애 애기 탄생 - 번호 : " + i);
        }
    }
}

class Soldier {
    public void fireWeapon() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("뿅!! 발사한 총알 : " + i);
        }
    }
}

public class WithoutThread {
    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        hospital.babyIsBorn();

        Soldier soldier = new Soldier();
        soldier.fireWeapon();
    }
}
```

- 멀티 스레드가 없는 세상에서는 모든 코드가 순차적으로 진행된다.
- 반드시 `hospital.babyIsBorn()`이 실행되어야 `soldier.fireWeapon();`이 실행된다.
- 당연히 결과는 아래와 같다. 코드상 아기가 다 태어나야만 군인이 훈련을 한다.

```
응애 애기 탄생 - 번호 : 1
응애 애기 탄생 - 번호 : 2
...
뿅!! 발사한 총알 : 1
뿅!! 발사한 총알 : 2
...
  ```

<br>

#### 멀티스레드로 돌아가는 세상

```java
class HaveBaby implements Runnable {

    @Override
    public void run() {
        Hospital baby = new Hospital();
        baby.babyIsBorn();
    }
}

class ShootTraining implements Runnable {

    @Override
    public void run() {
        Soldier soldier = new Soldier();
        soldier.fireWeapon();
    }
}

public class WithMultiThread {
    public static void main(String[] args) {
        Thread haveBaby = new Thread(new HaveBaby());
        Thread shootTraining = new Thread(new ShootTraining());

        haveBaby.start();
        shootTraining.start();
    }
}
```

- Runnable 객체를 생성하여 각각의 스레드를 실행시켜주었다.

<br>

```
응애 애기 탄생 - 번호 : 1
뿅!! 발사한 총알 : 1
뿅!! 발사한 총알 : 2
응애 애기 탄생 - 번호 : 2
뿅!! 발사한 총알 : 3
응애 애기 탄생 - 번호 : 3
뿅!! 발사한 총알 : 4
응애 애기 탄생 - 번호 : 4
뿅!! 발사한 총알 : 5
응애 애기 탄생 - 번호 : 5
```

- 아기가 탄생하는 일과 군인이 사격 훈련을 하는 일이 서로 독립적으로 벌어지게 되었다.
- 아기가 태어나는 순간에도, 다른 세상에서는 군인이 훈련을 할 수 있게 되었다.

아기가 태어나는 것과 군인이 총을 쏘는 것이 서로 다른 스레드에서 처리된다.

## 쓰레드의 상태
쓰레드에도 상태가 있다. 
- 스레드 객체 생성 (NEW)
  - 스레드 객체가 생성된 상태
- 실행 대기
- 일시 정지
- 종료



### Thread의 상태를 제어하는 메소드

#### Thread.sleep(milli_second)

현재 스레드를 주어진 밀리 세컨드 만큼 중단시킨다.  
만약 sleep 중에 다른 스레드가 현재 스레드에 개입하면 `InterruptedException`을 던진다.
그래서 `Thread.sleep()`을 호출하는 쪽에서 try-catch로 예외처리를 하거나, 해당 예외를 떠넘겨서 처리해야 한다. 
> 예외 처리는 프로그래머에게 달렸지만, 일반적으로 스레드를 종료한다. [문서](https://docs.oracle.com/javase/tutorial/essential/concurrency/interrupt.html)

```java
class DoSomething implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("무언가 합니다.");
    }
}

public class ThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread doSomething = new Thread(new DoSomething());

        doSomething.start();
        System.out.println("얍!");
    }
}
```
```java
얍!
무언가 합니다.
```
- _doSomething_ 스레드를 먼저 호출했지만, 1초(1000ms)간 sleep하기 때문에, _\'얍\!\'_ 이 먼저 출력된다.

#### instance.join()
instance 스레드가 끝날때 까지 현재 스레드를 정지시킨다.   
다음은 doSomething()의 run()에서 5초간 기다렸다가 메세지를 출력하는 코드다.
```java
class DoSomething implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println("무언가 합니다.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Test {
    public void run() throws InterruptedException {
        Thread thread = new Thread(new DoSomething());
        thread.start();
        thread.join();
        System.out.println("TEST");
    }
}
```

```java
무언가 합니다.
TEST
```
- DoSomething 스레드에서 5초가 지난 뒤 종료되고 나서야 Test의 run() 메소드에서 'TEST'를 출력한다.


## 참고

https://docs.oracle.com/javase/tutorial/essential/concurrency/procthread.html
http://journals.ecs.soton.ac.uk/java/tutorial/java/threads/states.html
