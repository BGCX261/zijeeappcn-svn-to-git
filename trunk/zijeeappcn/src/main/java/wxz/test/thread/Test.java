package wxz.test.thread;

import java.util.concurrent.CountDownLatch;

public class Test {
	public static void main(String[] args)  throws InterruptedException {
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(2);

		for (int i = 0; i < 2; ++i){
			// create and start threads
			new Thread(new Worker(startSignal, doneSignal)).start();
		}
		doSomethingElse(); // don't let run yet
		startSignal.countDown(); // let all threads proceed
		doSomethingElse();
		doneSignal.await(); // wait for all to finish
	}

	private static void doSomethingElse() {

	}

}

class Worker implements Runnable {
	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;

	Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
	}

	public void run() {
		try {
			startSignal.await();
			doWork();
			doneSignal.countDown();
		} catch (InterruptedException ex) {
		} // return;
	}

	void doWork() {
		System.out.println("==dowork==");
	}
}
