package this_is_the_java.Interface;

public class MultiInheritanceExample {
    public static void start() {
        ImplementationC impl = new ImplementationC();

        InterfaceA ia = impl; // methodA능()만 호출 가능
        ia.methodA();
        System.out.println();

        InterfaceB ib = impl; // methodB()만 호출 가능
        ib.methodB();
        System.out.println();

        InterfaceC ic = impl; // methodA(), methodB(), methodC() 모두 호출 가능
        ic.methodA();
        ic.methodB();
        ic.methodC();
        System.out.println();
    }
}
