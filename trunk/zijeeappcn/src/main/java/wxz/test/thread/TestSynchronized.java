package wxz.test.thread;

public class TestSynchronized {
    // synchronized ���ڷ����ϣ�����������༶����
    
    
    public int value = 100;

    public static void main(String[] args) {

        TestSynchronized test = new TestSynchronized();
        
        T1 t1 = new T1(test);
        T2 t2 = new T2(test);
        new Thread(t1).start();
        new Thread(t2).start();
        //////////////////////////
        new Thread(new T1(new TestSynchronized())).start();
        new Thread(new T2(new TestSynchronized())).start();
    }

    public synchronized void decrement() {
        value--;
    }

    public synchronized void increment() {
        value++;
    }
}

class T1 implements Runnable {
    TestSynchronized test;

    public T1(TestSynchronized test) {
        this.test = test;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("t1");
            test.decrement();
        }
    }

}

class T2 implements Runnable {
    TestSynchronized test;

    public T2(TestSynchronized test) {
        this.test = test;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("t2");
            test.increment();
        }
    }
}