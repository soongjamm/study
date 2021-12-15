package lambda;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class RosterTest {

	interface CheckPerson {
		// 하나의 추상메소드만 포함하고 있는 Functional Interface
		// 이러한 종류의 인터페이스는 JDK에서 정의한 java.util.function.* 패키지에서 찾을수 있으니 생성할 필요 없다. (ex. Predicate)
		boolean test(Person p);
	}

	public static void printPersonsOlderThan(List<Person> roster, int age) {
		// 깨지기 쉬운 코드다. 만약 Person의 구조가 변한다면 영향을 받기 때문이다. (ex. age 변수가 사라지고 다른 방법으로 가져온다던가..)
		// 그리고 만약 조건이 변한다면? 클라이언트가 아니라, 이 코드의 조건을 또 변경해야한다.
		for (Person p : roster) {
			if (p.getAge() >= age) {
				p.printPerson();
			}
		}
	}

	public static void printPersonsWithinAgeRange(List<Person> roster, int low, int high) {
		// 범위를 지정해서 좀더 일반적으로 만들어줬다. 그러나 만약 성별 조건이 추가된다면? -> 코드 수정.
		for (Person p : roster) {
			if (low <= p.getAge() && p.getAge() < high) {
				p.printPerson();
			}
		}
	}

	public static void printPersons(List<Person> roster, CheckPerson tester) {
		// Tester 를 클라이언트에서 구현(조건)으로 주면 된다. (아래 CheckPersonEligibleForSelectiveService) -> 변하지 않는 코드
		// 그러나 조건별로 클래스를 생성해야하는 부담. (조건 인터페이스를 구현한 클래스가 필요하므로)-> (익명, 람다로 해결해야 한다.)
		for (Person p : roster) {
			if (tester.test(p)) {
				p.printPerson();
			}
		}
	}

	public static class CheckPersonEligibleForSelectiveService implements CheckPerson {

		@Override
		public boolean test(Person p) {
			return p.gender == Person.Sex.Male && p.getAge() > 10;
		}
	}

	public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {
		for (Person p : roster) {
			if (tester.test(p)) {
				p.printPerson();
			}
		}
	}

	public static void processPerson(List<Person> roster, Predicate<Person> tester, Consumer<Person> block) {
		// Predicate는 하나의 인자를 받아서 boolean을 리턴한다.
		// print를 하기 위해서 하나의 아규먼트가 필요하고 리턴타입은 없다. -> Consumer
		for (Person p : roster) {
			if (tester.test(p)) {
				block.accept(p);
			}
		}
	}

	public static void processPersonWithFunction(List<Person> roster,
												 Predicate<Person> tester,
												 Function<Person, String> mapper,
												 Consumer<String> block) {
		// 리턴등 행위를 추가할 수도 있다.
		for (Person p : roster) {
			if (tester.test(p)) {
				String res = mapper.apply(p);
				block.accept(res);
			}
		}
	}

	public static <X,Y> void processPersonWithFunctionAndGeneric(List<X> roster,
												 Predicate<X> tester,
												 Function<X, Y> mapper,
												 Consumer<Y> block) {
		// 리턴등 행위를 추가할 수도 있다.
		for (X p : roster) {
			if (tester.test(p)) {
				Y res = mapper.apply(p);
				block.accept(res);
			}
		}
	}


	public static void main(String[] args) {
		List<Person> people = List.of(
				new Person("kim", LocalDate.now(), Person.Sex.Male, "eamil"),
				new Person("lee", LocalDate.now(), Person.Sex.Male, "eamil"),
				new Person("park", LocalDate.now(), Person.Sex.Female, "eamil"));

//		printPersonsOlderThan(people, 10);
//		printPersons(people, new CheckPersonEligibleForSelectiveService());
//		printPersonsWithPredicate(people, new Predicate<Person>() { // 람다식 X
//			@Override
//			public boolean test(Person p) {
//				return p.gender == Person.Sex.Male && p.getAge() > 10;
//			}
//		});
//		processPerson(people, (p) -> p.gender == Person.Sex.Male, (p) -> p.printPerson()); // 람다식
//		processPersonWithFunction(people,
//				(p) -> p.gender == Person.Sex.Male,
//				(p) -> p.name +  "은 " + p.gender,
//				(content) -> System.out.println(content));
//		processPersonWithFunctionAndGeneric(people,
//				(p) -> p.gender == Person.Sex.Male,
//				(p) -> p.name +  "은 " + p.gender,
//				(content) -> System.out.println(content)); // generic

		/**
		 * 위 과정들을 aggregate operation 을 사용하면 메소드나 클래스 생성없이 사용 가능
		 * Aggregate operations 처리는 collection 이 아닌, stream 에서 일어난다.
		 * stream 은 저장하는 자료구조가 아니라, 일련의 데이터들이다. stream 은 pipeline 에 의해 처리된다.
		 * piepline 은 일련의 stream 동작들을 말한다. (map(), filter(), foreach() 등)
		 */
		people
				.stream()
				.filter( person -> person.gender == Person.Sex.Male)
				.map( person -> person.name+ "의 나이 : " + person.getAge())
				.forEach(content -> System.out.println(content));

		// 람다식은 익명 메소드처럼 사용도 가능
		CheckPerson checkPerson = (person) -> person.getAge() == 11;
		boolean res = checkPerson.test(new Person("kim", LocalDate.now(), Person.Sex.Male, "eamil"));

	}
}
