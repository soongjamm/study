package _15._5.pub_sub_reactive;

public interface Publisher<T> {
    void subscribe(Subscriber<? super T> subscriber);
}
