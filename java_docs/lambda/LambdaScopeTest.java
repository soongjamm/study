package lambda;

import java.util.function.Consumer;

// shadowing ?
// capture variable ?

public class LambdaScopeTest {
	// 익명, 로컬 클래스처럼 capture variable 을 한다. 감싸고 있는 스코의 변수에 접근이 가능하다.
	// 다른점은 shadowing issue 를 가지지 않는다.
	// lexical scope 를 가진다.
	// lexically scoped => super 으로부터 이름을 상속하거나 새로운 스코프를 만들지 않는다? 그저 감싼 환경에 있는걸로?
	// 						람다식이 {} 안에 있지만, 새로운 스포크가 아니라, 메소드안에 있으면 그 메소드의 일부로 취급한다.
	//						검색했을땐, 호출하는곳이 아닌, 선언된 곳에 따라 스코프가 결정되는 것
	//						정적 스코프라고도 함
	public int x = 0;

	class FirstLevel {

		public int x = 1;

		void methodInFirstLevel(int x) {

			int z = 2;

			Consumer<Integer> myConsumer = (y) ->
					// 파라미터를 x로 선언하면 x는 이미 선언되었다는 에러가 나온다.
					// 람다는 새로운 레벨의 스코프를 만들지 않고,
					// lexical scope 에 의해 이미 x는 methodInFirstLevel의 파라미터로서 존재하기 때문이다.
					// 그러므로 이 안에서 자유롭게 메소드나 변수 참조가 가능하다.
			{
				// The following statement causes the compiler to generate
				// the error "Local variable z defined in an enclosing scope
				// must be final or effectively final"
				// 이건 local and anonymous class 와 같은 ㅊ
				// z = 99;

				System.out.println("x = " + x);
				System.out.println("y = " + y);
				System.out.println("z = " + z);
				System.out.println("this.x = " + this.x);
				System.out.println("LambdaScopeTest.this.x = " + LambdaScopeTest.this.x);
				this.something(); // something() 도 가능
				this.someInt = 2; // someInt = 2; 도 가능

				System.out.println(someInt);
			};

			myConsumer.accept(x);

		}

		private int someInt = 1;
		public void something() {
			int capturedVar = 100; // local class 에서 접근하려면 final or efficient final 이어야.
			class LocalClass {
				public LocalClass() {
					System.out.println(capturedVar); // 사용하는 순간 capture. 순간 스냅샷을 뜨는 것과 같은 듯하다.
//					capturedVar = 10;
				}
			}
		}
	}

	public static void main(String... args) {
		LambdaScopeTest st = new LambdaScopeTest();
		LambdaScopeTest.FirstLevel fl = st.new FirstLevel();
		fl.methodInFirstLevel(23);
	}

	interface Greeting {
		void greet(String str);
	}

	String whataboutthis;
	void handle() {
		String hello = "hello";
		Greeting greeting = (whataboutthis) -> {

		};
	}
}
