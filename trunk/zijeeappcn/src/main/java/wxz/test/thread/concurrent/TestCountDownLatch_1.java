package wxz.test.thread.concurrent;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch_1 {
	public static final int N = 10;

	public static void main(String[] args) throws Exception {
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(N);
		System.out.println("start:" + System.currentTimeMillis());
		for (int i = 0; i < N; i++) {
			new Thread(new Worker(startSignal, doneSignal), "T" + i).start();
		}
		startSignal.countDown();
		doneSignal.await();
		System.out.println("end:" + System.currentTimeMillis());
	}
}

class Worker implements Runnable {

	CountDownLatch startSignal = null;
	CountDownLatch doneSignal = null;

	public Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
		super();
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
	}

	public void run() {
		try {
			startSignal.await();
			System.out.println(Thread.currentThread().getName());
			doneSignal.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
