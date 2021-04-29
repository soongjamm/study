package hello.core.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

// proxyMode = ScopedProxyMode.TARGET_CLASS 로 설정하면 가짜 프록시객체를 생성한다.
// 프록시 객체는 진짜 객체를 가져오는 방법을 가지고 있다가, 객체가 필요해지는 시점에 진짜를 호출한다.
// proxy mode를 쓰든, ObjectProvider를 쓰든 핵심 아이디어는 '지연 로딩'
// request 스코프 자체를 남발하지 않는게 좋다. 유지보수가 어려워진다.
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + "[" + message + "]");
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "]" + "request scope bean created "  + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "]" + "request scope bean closed "  + this);
    }

}
