# 람다식 (Lambda Expression)
보통 메소드를 재사용 가능하고 유연하게 어떤 기능을 넘겨주고 싶을 때, 파라미터로 익명 함수를 사용한다.   
그런데 이 익명함수가 가지는 추상 메소드가 1개 뿐이라면 익명 함수의 문법은 번잡하게 느껴지게 된다.  

이런 경우를 위해 익명 함수가 하나의 추상 메소드만 가지는 경우(Functional Interface), 메소드 시그니처를 감추고 간단한 표현식만 드러내기 위해 람다식을 사용한다.

### 익명함수의 사용부터 람다식이 적절한 경우 step by step

**step1**  
다음과 같이 사람 목록과 나이를 인자로 받고, 특정 조건을 만족하면 출력을 하는 메소드가 있다. 

```java
public static void printPerson(List<Person> roster, int age) {
    for (Person p : roster) {
        if (p.getAge() >= age) { // 만약 이 조건이 변한다면?
            p.printPerson();
        }
    }
}
```

현재 출력을 위한 조건은 `p.getAge() >= age` 이다. 그런데 만약 조건이 반대로 바뀐다면? 
기존의 코드를 수정해서 `p.getAge() < age` 로 만들어야 할 것이다. 

그런데 어떤 개발자는 앞의 조건(`p.getAge() >= age`)이 여전히 필요하다면? 똑같은 메소드를 조건만 바꿔서 두개를 만들어야할까? 

만약 age 뿐만 아니라, 성별까지 검사해야한다면? 또 새로운 메소드를 추가해야할까?

**기능을 사용하는 쪽에서 약간의 변경이 필요할 때 마다 기존의 기능을 제공하는 코드를 수정하거나 중복되는 코드를 양산하는 이런 경우를 변경이나 확장에 유연하지 못하다고 한다.** 

이를 조금 더 유연하게 만들어 줄 수 있는게 `익명 함수`다.

**step2**

```java
// 익명함수를 이용한 조금 더 유연한 변경
public class Step2{

    interface Criteria {
        boolean test(Person person);
    }
    
    public static void printPerson(List<Person> roster, Criteria criteria) {
        for (Person p : roster) {
            if (criteria.test()) { // 조건은 이 기능을 사용하는 클라이언트가 생성하면 된다.
                p.printPerson();
            }
        }
    }

	// client 
	public static void main(String[] args) {
		List<Person> people = List.of(
				new Person("kim", LocalDate.now(), Person.Sex.Male, "eamil"),
				new Person("lee", LocalDate.now(), Person.Sex.Male, "eamil"),
				new Person("park", LocalDate.now(), Person.Sex.Female, "eamil"));
		// 실행 1
		printPerson(people, new Criteria() {
			@Override
			public boolean test(Person person) {
				return person.getAge() > 10; // 여기서 마음대로 조건을 정의하면 된다.
			}
		});

		// 실행 2
		printPerson(people, new Criteria() {
			@Override
			public boolean test(Person person) {
				return person.getGender() == Gender.MALE; // 여기서 마음대로 조건을 정의하면 된다.
			}
		});
	}
}
```

만약, 기능을 제공하는 코드 내부에 조건을 정의했다면 클라이언트가 새로운 기준이 필요할 때 마다 메소드를 추가해야했지만, 인터페이스와 익명함수를 사용함으로써 필요에 따라 클라이언트가 구체적인 기준을 정의할 수 있게 되었다. 

그런데 조금 코드가 번잡하다.  이제 람다식을 적용하면 된다.

**step3**  
위 코드의 일부분이다.
```java
// 실행 1
printPerson(people, new Criteria() {
    @Override
    public boolean test(Person person) {
        return person.getAge() > 10; // 여기서 마음대로 조건을 정의하면 된다.
    }
});
```
겨우 `person.getAge() > 10;` 이 조건 하나를 위해서 대략 6줄을 만들어내고 있다. 람다식을 이용해 간단하게 표현해보자.

```java
// 실행 1
printPerson(people, new Criteria() {
    @Override
    public boolean test(Person person) {
        return person.getAge() > 10; 
    }
});
```

```java
printPerson(people, person -> {
    return person.getAge() > 10;
});

// 만약, 파라미터를 하나만 받는 람다식이라면 괄호{}와 return까지 생략할 수 있다.
printPerson(person -> person.getAge() > 10);
```

### 자바에서 Functional Interface 를 제공한다.
위 코드에서는 하나의 파라미터를 받아서 boolean을 리턴하는 추상메소드를 가진 Functional Interface Criteria 인터페이스를 직접 정의했다.
그러나  이러한 종류의 인터페이스는 JDK에서 정의한 java.util.function.* 패키지에서 찾을수 있으니 생성할 필요 없다. (ex. 직접 생성한 Criteria 인터페이스는 JDK에서 제공하는 `Predicate`로 대체할 수 있다.)


## 람다식과 익명클래스(or 로컬클래스) 의 공통점 및 차이점
**공통점 (capture variable)**  
공통점은 모두 `capture variable` 을 한다는 점이다.   
무슨 말이냐면 람다식과 익명(이나 로컬)클래스를 감싸고 있는 영역에 선언된 변수를 사용할 때, 그 순간의 상태를 기억한다고 생각하면 된다.   
그렇기 때문에 해당 변수는 final 혹은 efficient final 이어야 한다.
> efficient final 은 final은 아니지만, capture 시점 이후로 변경되지 않아서 사실상 final인 경우.

```java
public class LambdaScopeTest {
	interface Greeting {
		void greet();
	}

	void handle() {
		String hello = "hello";
		// 익명 함수
		Greeting greeting = () -> {
			System.out.println(greeting); // capture
			// hello = hello + "world"; 불가능하다. 
		};
	}
}
```

**차이점 (lexically scoped / shadowing X)**
차이점은 람다식은 `lexical scope` 를 갖는다는 점이다.  
오라클 공식문서에서는  
> This means that they do not inherit any names from a supertype or introduce a new level of scoping. Declarations in a lambda expression are interpreted just as they are in the enclosing environment.

라고 설명한다. `do not inherit any names from a supertyp` 에 대해서는 정확히 이해하지 못했지만,
대략 의미를 해석하자면 `{}` 안에 있다고 해서 새로운 범위를 만들어내지 않고, 람다식을 포함하고 있는 그 메소드나 클래스와 같은 범위를 가진다는 의미로 보인다.

그러므로 당연하게도 **shadowing 이슈는** 발생하지 않게 된다.
> 익명함수 등에서 그것을 감싸는 클래스나 메소드가 가진 변수와 동명의 변수를 선언하면, 이미 존재하던 동명의 변수들이 가려지는 현상.   
> this.var 나 XXX.this.var 등으로 접근할 수 있다. 

```java
public class LambdaScopeTest {
	interface Greeting {
		void greet(String str);
	}

	void handle() {
		String hello = "hello";
		// 익명 함수
		Greeting greeting = (hello) -> { // 컴파일 에러가 발생한다.
            // 왜냐하면 이 람다식은 handle()안에 있고, handle()안에 이미 hello 라는 지역변수가 존재한다. 
        };
	}
}
```