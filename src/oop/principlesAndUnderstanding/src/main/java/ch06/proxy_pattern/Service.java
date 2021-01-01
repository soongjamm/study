package ch06.proxy_pattern;

public class Service implements IService {
    int randomNumber;

    public Service(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    public String runSomething() {
        return "서비스를 실행합니다.";
    }

    public boolean possibleRunning() {
        if (randomNumber%2 == 0) {
            return true;
        }
        return false;
    }
}
