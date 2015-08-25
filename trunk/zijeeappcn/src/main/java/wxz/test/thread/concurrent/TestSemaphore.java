package wxz.test.thread.concurrent;

import java.util.concurrent.Semaphore;

//用于限制可以访问某些资源的线程数目
// 系统调度的PV原语
// P = acquire()
// V = release()
public class TestSemaphore {
    private static final int MAX_AVAILABLE = 5;
    private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);
    private Object[] items = { "AAA", "BBB", "CCC", "DDD", "EEE", };
    private boolean[] used = new boolean[MAX_AVAILABLE];

    public static void main(String[] args) {
        final TestSemaphore pool = new TestSemaphore();
        Runnable runner = new Runnable() {
            @Override
            public void run() {
                try {
                    Object o = pool.getItem();
                    System.out.println(Thread.currentThread().getName() + " acquire " + o);

                    Thread.sleep(1000);

                    pool.putItem(o);
                    System.out.println(Thread.currentThread().getName() + " release " + o);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 10; i++) {// 构造10个线程
            Thread t = new Thread(runner, "t" + i);
            t.start();
        }
    }

    // 获取数据，需要得到许可
    public Object getItem() throws InterruptedException {
        available.acquire();
        return getNextAvailableItem();
    }

    // 放回数据，释放许可

    public void putItem(Object x) {
        if (markAsUnused(x)) {
            available.release();
        }
    }

    protected synchronized Object getNextAvailableItem() {
        for (int i = 0; i < MAX_AVAILABLE; i++) {
            if (!used[i]) {
                used[i] = true;
                return items[i];
            }
        }
        return null;
    }

    synchronized boolean markAsUnused(Object item) {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (item.equals(items[i])) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

}
