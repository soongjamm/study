package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// 1. InitializingBean, DisposableBean은 코드를 가져다 써야하기 때문에, 코드가 수정되지 않는 외부라이브러리 등을 쓸땐 쓸 수 없다.
// 2. @Bean()의 initMethod, destroyMethod는 코드가 아니라 설정정보를 활용하기 때문에 코드를 수정하지 않아도 쓸 수 있다.
// 3. @PostConstruct, @PreDestroy 가 권장된다. 자바 표준이며 간단하게 쓸 수 있다. 그러나 외부 라이브러리에 적용이 불가능해서 그런 경우 2번을 쓰자.
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("client constructor. url - " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    private void call(String msg) {
        System.out.println("msg = " + msg + " url - " + url);
    }

    private void connect() {
        System.out.println("connect. url - " + url);
    }

    private void disconnect() {
        System.out.println("disconnect  " + url);
    }

    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetworkClient.init");
        connect();
        call("init msg call ");
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
        System.out.println();
    }
}
