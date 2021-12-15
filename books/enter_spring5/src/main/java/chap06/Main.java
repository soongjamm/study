package chap06;

import chap06.config.AppContext;
import chap06.spring.Client;
import chap06.spring.Client2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);

        Client client = ctx.getBean(Client.class);
        client.send();

        Client2 client2 = ctx.getBean(Client2.class);
        client2.send();

        Client2 clientSinglton = ctx.getBean(Client2.class);
        Client clientProtoType = ctx.getBean(Client.class);

        System.out.println(client == clientProtoType); // false
        System.out.println(client2 == clientSinglton); // true

        ctx.close();
    }
}
