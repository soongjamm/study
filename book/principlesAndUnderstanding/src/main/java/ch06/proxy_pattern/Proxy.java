package ch06.proxy_pattern;

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
