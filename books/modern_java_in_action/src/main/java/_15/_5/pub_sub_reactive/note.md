```java
ArithmeticCell c5=new ArithmeticCell("C5");
ArithmeticCell c3=new ArithmeticCell("C3");

SimpleCell c4=new SimpleCell("C4");
SimpleCell c2=new SimpleCell("C2");
SimpleCell c1=new SimpleCell("C1");

c1.subscribe(c3::setLeft);
c2.subscribe(c3::setRight);

c3.subscribe(c5::setRight);
c4.subscribe(c5::setRight);

c1.onNext(10);
c2.onNext(20);
c1.onNext(15);
c4.onNext(1);
c4.onNext(3);
```

| c1  | c2  | c3    | c4  | c5    |
|-----|-----|-------|-----|-------|
|     |     | c1+c2 |     | c3+c4 |

와 마찬가지다.\


---

upstream/downstream  
발행자(생산자) -> 구독자(소비자)로 가는 흐름을 말함

---

지금까지는 일반적인 pub/sub 패턴 또는 옵저버 패턴과 다를게 없어보인다.
그런데 그런 패턴들은 어떠한 모양 또는 구현 방법에 관한 것이고,  
리액티브 프로그래밍이란 것은 이벤트를 전파하는 식으로 상호작용하는 일종의 패러다임에 관한 것 이라고 이해가 된다.  

Flow API 가 특별한 이유는 onError와 onComplete 같은 인터페이스로 데이터 흐름에 예외가 발생/종료 되었을 때를 대비할 수 있어서다.
  

압력/역압력