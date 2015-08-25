package wxz.test.thread;

public class TestThreadState {

    public static void main(String[] args) {
        test1();

    }
    

    private static void test1() {
        TThread t = new TThread();
        System.out.println(t.getState());
        t.start();
        try {
            t.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(t.getState());
        // t.start();
    }

}

class TThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + "-->" + i + "," + Thread.currentThread().getState());
        }
    }
}
