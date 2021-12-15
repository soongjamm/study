package this_is_the_java.multiThread;

public class CalcThread extends Thread{
    public CalcThread(String name) {
        setName(name);
    }

    public void run() {
        for(long i=0; i<20000000000l; i++) {}
        System.out.println(getName());
    }
}
