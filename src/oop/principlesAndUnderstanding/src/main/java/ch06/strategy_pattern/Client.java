package ch06.strategy_pattern;

public class Client {
    public static void main(String[] args) {
        Soldier rambo = new Soldier();

        // 총을 람보에게 전달해서 전투를 수행하게 한다.
        rambo.runContext(new StrategyGun());
        System.out.println();

        // 칼을 람보에게 전달해서 전투를 수행하게 한다.
        rambo.runContext(new StrategySword());
        System.out.println();

        // 활을 람보에게 전달해서 전투를 수행하게 한다.
        rambo.runContext(new StrategyBow());
        System.out.println();

    }
}
