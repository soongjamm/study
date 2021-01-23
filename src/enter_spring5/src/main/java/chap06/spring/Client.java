package chap06.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Client implements InitializingBean, DisposableBean {

    private String host;

    public void setHost(String host) {
        this.host = host;
    }

    /**
    * 아래 메소드들이 실행되는 순서를 확인해보기 위해 출력 메세지를 찍어본다.
    **/
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Client.afterPropertiesSet() 실행");
    }

    public void send() {
        System.out.println("Client.send() to " + host);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Client.destory() 실행");
    }
}
