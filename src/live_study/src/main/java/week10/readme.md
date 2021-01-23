# 멀티쓰레드 프로그래밍

## 프로세스(Process)와 스레드(Thread)란 무엇인가?
스레드를 알기 전에, 프로세스를 먼저 알아야 한다.
`프로세스`는 운영체제에서 실행중인 (보통)하나의 응용 프로그램 단위를 말한다. 하나의 응용 프로그램을 실행하면 운영체제로부터 그에 필요한 메모리를 할당받아 독립적인 공간을 가지고 있다.  
`스레드`는 프로세스가 가지는 **작은 프로세스**이자 하나의 실행 환경이라고 볼 수 있다. 프로세스는 최소 하나의 스레드를 가지며, 자원을 공유한다. (메신저에서 파일을 보내는 것, 메세지를 보내는 것 각각이
스레드라고 볼 수 있다.)
프로세스 내부에서 이러한 스레드가 여러개 존재하면 `멀티 스레드`이다.

<br>

## Main 쓰레드
우리가 자바 애플리케이션을 실행시키면 가장 먼저 `main()` 메소드를 실행시킨다.   
프로세스에는 반드시 하나 이상의 스레드가 포함된다고 했는데, 바로 main() 메소드가 `Main 쓰레드`가 된다.

#### 멀티 스레드의 특징

- 멀티 스레드는 동시에 무언가를 처리하는 프로그램을 만들기 위해서 사용한다.
    - 메신저에서 메세지를 보내면서 파일 전송을 같이 하는 행위 등
- 하나의 프로세스에서 자원을 공유하기 때문에 하나의 스레드에 이상이 생기면 다른 스레드에도 영향을 미칠 수 있다.
- 그렇기 때문에 예외 처리가 중요하다.

<br>

## Thread 클래스와 Runnable 인터페이스

- `Thread` 클래스는 스레드 관리와 관련된 다양한 메소드가 정의가 되어있다.
- 스레드 객체를 생성하려면 반드시 스레드가 처리할 코드를 작성해야하는데 두 가지 방법이 있다.
    1. Thread 클래스로부터 직접 생성 (Runnable 인터페이스를 구현)
    2. Thread의 서브클래스로부터 생성

<br>


#### Runnable 구현 vs 서브 클래스?

`Runnable` 객체를 구현하는 것이 일반적이다.

- 자바는 다중상속이 불가능하기 때문에 Thread를 상속받으면 다른 클래스를 상속받을 수가 없다.
- 반면 Runnable은 인터페이스이기 때문에 다른 클래스를 상속받을 수 있다.
- 그렇기 때문에 Runnable 구현 설명에 조금 더 집중한다.

<br>


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

<br>


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

<br>


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

<br>


## 스레드 우선순위
`동시성(Concurrency)` : 멀티 작업을 위해 하나의 코어에서 멀티 스레드가 번갈아가며 실행하는 성질    
`병렬성(Parallelism)` : 멀티 작업을 위해 여러개의 코어가 멀티 스레드를 동시에 실행하는 성질
> 동시성은 워낙 빨라서 병렬적으로 실행하는 것 처럼 보이지만, 하나의 실행 흐름에서 스레드를 번갈아가며 실행하는 것이다.

스레드의 개수가 코어의 개수보다 많은 경우 **어떤 순서에 의해 동시성으로 실행할 것인지 결정**해야 하는데 이것을 `스레드 스케줄링`이라고 한다.  
만약 스레드의 개수가 코어의 개수보다 적은 경우에는 스케줄링이 큰 영향을 못미친다.

자바가 스레드 스케줄링을 하는 방식은 `우선순위(Priority)`와 `순환 할당(Round Robin)`이 있다.
<br>
### 우선순위
- 우선순위가 높은 스레드가 더 많은 실행 상태를 가지도록 스케줄링한다.
- 스레드 객체에 우선순위 번호를 부여할 수 있어서 개발자가 순서를 제어할 수 있다.
- 우선순위는 1부터 10까지 있는데 숫자가 클수록 우선순위가 크다.
  - 우선순위는 상수로 등록되어 있다.

다음은 200억번 for문을 실행시키는 스레드를 10개 만들고, **마지막에 만들어지는 스레드만** `MAX_PRIORITY`를 가지게 했다.
```java
public class CalcThread extends Thread{
    public CalcThread(String name) {
        setName(name);
    }

    public void run() {
        for(long i=0; i<20000000000l; i++) {}
        System.out.println(getName());
    }
}
```
```java
public class PriorityExample {
    public static void main(String args[]) {
        for(int i=1; i<=10; i++) {
            Thread thread = new CalcThread(i + "번째 스레드");
            if (i != 10) {
                thread.setPriority(Thread.MIN_PRIORITY);
            } else {
                thread.setPriority(Thread.MAX_PRIORITY);
            }
            thread.start();
        }
    }
}
```

```java
10번째 스레드
3번째 스레드
6번째 스레드
....
```
마지막으로 생성한 10번째 스레드가 가장 빨리 처리되었다.

### 순환 할당
- 시간 할당량(Time Slice)를 정해서 하나의 스레드를 정해진 시간만큼 실행하고 다시 다른 스레드를 실행하는 방식으로 스케줄링한다.
- JVM에 의해 정해져서 개발자가 코드로 제어할 수 없다.

<br>

## 동기화 메소드와 동기화 블록
### 공유 객체를 사용할 때의 주의할 점
싱글 스레드일때는 한개의 스레드가 객체를 독차지할 수 있지만, 멀티 스레드 프로그램에서 객체를 공유하는 경우 조심해야할 것이 있다.  
A스레드는 공유객체의 값을 가져오려하고 B스레드는 공유객체의 값을 변경하려고 할 때, A스레드가 먼저 실행되는 경우 원하는 값을 얻을 수도 있고 B스레드가 먼저 값을 바꾸는 경우 A스레드는 예상과 다른 값을 얻어야 한다.
<br>
```java
public class Calculator {
  private int memory;

  public int getMemory() {
    return memory;
  }

  public void setMemory(int memory) {
    this.memory = memory;
    try {
      Thread.sleep(2000);
    } catch (Exception e) {
    }
    System.out.println(Thread.currentThread().getName() + ": " + this.memory);
  }
}
```

```java
// User2는 생략
public class User1 extends Thread {
  private Calculator calculator;

  public void setCalculator(Calculator calculator) {
    this.calculator = calculator;
  }

  @Override
  public void run() {
    calculator.setMemory(100);
  }
}
```

```java
public class MainThreadExample {
    public static void main (String[] args) {
        Calculator calculator = new Calculator();

        User1 user1 = new User1();
        user1.setCalculator(calculator);
        user1.start();

        User2 user2 = new User2();
        user2.setCalculator(calculator);
        user2.start();
    }
}
```
- user1과 user2는 현재 같은 calculator를 참조하고 있다.
- user1.start(); 내부에서 Calculator의 `setMemory(100)`를 호출해 메모리 값을 100으로 바꾸고 user1 스레드가 2초간 멈춰있다. 
- **user2.start(); user1 스레드가 멈춰있는 동안 마찬가지로 내부에서 `setMemory(50)`을 호출해 메모리 값을 50으로 바꾸고** user2 스레드가 2초간 멈춰있다.

- **현재 user2 스레드에 의해 참조객체 calculator의 memory=50이 되었다.** 
- 2초뒤 user1 스레드와 user2 스레드가 차례대로 memory를 출력한다.
- **user1 스레드는 100이 출력되길 기대했지만, 멈춰있는 동안 위에서 user2가 동일한 참조객체 calculator의 memory 값을 50으로 바꿔놓는 바람에 50이 출력된다.**

이것이 멀티 스레드에서 공유객체를 사용할 때의 문제점이다.

<br>

### 동기화 메소드 및 동기화 블록
공유객체로 인한 문제를 발생시키지 않으려면, 사용중인 객체를 작업이 끝날 때까지 다른 스레드에서 접근하지 못하도록 잠금을 걸어야 한다.  
멀티 스레드 프로그램에서 단 하나의 스레드만 실행할 수 있는 코드 영역을 `임계 영역(critical section)`라 하고 `동기화(syncorinized)`메소드와 `동기화 블록`을 사용한다.  
<br>
동기화 메소드는 메소드 전체에도 락을 걸 수 있고, 일부분에도 걸 수 있다.
```java
/**
 * 전체에 동기화
 */
public synchronized void method() {
    //임계 영역
}

/**
 * 일부에 동기화
 */
public void method() {
    // 여러 스레드 실행 가능 영역
    synchronized(공유객체) {
        //임계 영역
    }
    // 여러 스레드 실행 가능 영역
}
```   

> `MainThreadExample.java` 예제에서 `Calculator.java`의 `setMemory(int memory)`에 `synchronized`만 붙여주면 문제를 해결할 수 있다.   
> 물론 동기화 블록으로 만들어줘도 된다.
```java
public synchronized void setMemory(int memory) {
       ....
    }
    
```
or
```java
public void setMemory(int memory) {
    synchronized ((Object) memory) {
        this.memory = memory;
        System.out.println(Thread.currentThread().getName() + ": " + this.memory);
    }
}
```

<br>


## 쓰레드의 상태
쓰레드에도 상태가 있다. 동영상 플레이어를 일시 정지시키고 재생하는 것 등이 스레드의 상태를 변경시키는 것이다.
- 스레드 객체 생성 (NEW) : 스레드 객체가 생성된 상태
- 실행 대기 (RUNNABLE) : 언제든지 실행상태로 돌아갈 수 있는 상태
- 일시 정지
    - (WAITING) : 디른 스레드가 통지할 때 까지 기다리는 상태
    - (TIMED_WAITING) : 주어진 시간동안 기다리는 상태
    - (BLOCKED) : 사용하고자 하는 객체의 락이 풀릴 때 까지 기다리는 상태
- 종료 (TERMINATED) : 실행을 마친 상태

### Thread의 상태를 제어하는 메소드
#### suspend(), resume(), stop()
- 셋 모두 Deprecated된 메소드다.
- suspend()는 wait()으로 대신 사용한다.
- resume()은 notify() 또는 notifyAll()로 대신 사용한다.
- stop()은 스레드를 즉시 종료시키는데, 스레드 안정성을 위해 사용하지 않는다.
  > 대신 스레드를 안전하게 종료시키기 위한 방법은 run() 메소드가 성공적으로 마무리 되도록 유도하는 것이다.
  > stop=true 면 스레드를 종료하는 방식으로 플래그를 이용한다.


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

#### join()
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

#### interrupt()
sleep()과 join()에서 interrupt된 상황에 대해 얘기했는데, 그 interrupt를 직접 일으킬 수 있는 메소드다.  
join()의 예제에서 interrupt() 메서드를 중간에 추가하면 실행시킨 thread가 `InterruptedException` 예외를 발생시킨다.
```java
class Test {
    public void run() throws InterruptedException {
        Thread thread = new Thread(new DoSomething());
        thread.start();
        thread.interrupt(); // interrupt 추가
        thread.join();
        System.out.println("TEST");
    }
}

```

#### yield()
실행 중 우선순위가 동일한 다른 스레드에게 실행을 양보하고 실행 대기 상태가 된다.

아래에 ThreadA는 work가 true 일때만 작업을 실행하고 카운트하고, false일 때는 작업을 양보하도록 하였다.
```java
public class ThreadA extends Thread {
    public boolean stop = false;
    public boolean work = true;

    public void run() {
        int count = 0;
        while(!stop) {
            if(work) {
                count++;
            } else {
                Thread.yield();
            }
        }
        System.out.println("스레드 종료" + Thread.currentThread().getName() + " : " +  count + " 회 실행 ");
    }
}
```

그리고 아래의 실행 코드는 동시에 두 스레드를 실행시키고, 한 스레드는 3초간 작업을 중단하도록 하였다.
```java
public class YieldExample {
    public static void main(String[] args) {
        ThreadA thread0 = new ThreadA();
        ThreadA thread1 = new ThreadA();
        thread0.start();
        thread1.start();

        try {Thread.sleep(3000);}catch(InterruptedException e){}
        thread0.work = false;

        try {Thread.sleep(3000);}catch(InterruptedException e){}
        thread1.work = true;

        try {Thread.sleep(3000);}catch(InterruptedException e){}
        thread0.stop = true;
        thread1.stop = true;
    }
}
```

```java
스레드 종료Thread-1 : 1627612 회 실행 
스레드 종료Thread-0 : 602533 회 실행 
```
실제로 양보를 한다는 사실을 알 수 있다.

<br>

> 다음 3개의 메소드는 Object 클래스의 인스턴스 메소드다. 
#### wait()
동기화(synchronized) 블록 내에서 스레드를 일시 정지 시킨다.  
다시 깨우기 위해선 ms단위로 시간을 파라미터로 주거나, notify() 또는 notifyAll() 메소드를 사용할 수 있다.

#### notify(), notifyAll()
동기화 블록내에서 wait()에 의해 일시 정지된 스레드를 실행 대기 상태로 만든다.

## 데드락 (Deadlock)
공유 자원을 여러 스레드에서 사용하려고 할 때, 서로 자신의 차례를 무한정 기다리고 있는 상황을 말한다.
(더 공부해서 내용을 추가할 예정입니다.)

## 참고
이것이 자바다  
https://docs.oracle.com/javase/tutorial/essential/concurrency/procthread.html
http://journals.ecs.soton.ac.uk/java/tutorial/java/threads/states.html
