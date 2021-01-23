package chap07.spring;

/**
 * 엄밀히 말하면 데코레이터에 가깝지만, AOP의 이해를 위해 프록시라고 생각하자.
 * (데코레이터 - 기능 추가/확장에 초점,
 *  프록시 - 접근 제어 관점에 초점)
 *
 * 프록시 객체 (프록시 대상 객체는 Impe~ , RecCalculator)
 * 핵심기능은 프록시 대상 객체에 위임하고, 부가적인 기능을 수행하는 객체가 프록시 객체다.
 *
 * 실행 시간을 구하고 싶을 때, 실행 코드에서 객체별로 실행시간을 구하게 하면, 코드 수정이 필요할 때 모두 수정해줘야 한다는 번거로움이 있다.
 * (ex. ms 를 구하다가 nano 를 구하고 싶을 때.)
 * 프록시 객체를 사용하면 이 객체 내부에서만 코드를 수정하면 된다. + 시간을 구하는 코드의 중복까지 제거됬다.
 *
 * 가능한 이유
 * 1. 핵심기능 factorial() 기능 자체를 이 클래스에서 직접 구현하지 않고, 프록시 대상 객체에 factorial()의 실행을 위임했다. (long result = delegate.factorial(num);)
 * 2. 계산 기능 외에 다른 기능(실행 시간 구하기)을 함께 실행했다.
 *
 */

public class ExeTimeCalculator implements Calculator {

    private Calculator delegate;

    public ExeTimeCalculator(Calculator delegate) {
        this.delegate = delegate;
    }

    @Override
    public long factorial(long num) {
        long start = System.nanoTime();
        long result = delegate.factorial(num);
        long end = System.nanoTime();
        System.out.printf("%s.factorial(%d) 실행 시간 = %d",
                delegate.getClass().getSimpleName(),
                num, (end - start));

        return result;
    }
}
