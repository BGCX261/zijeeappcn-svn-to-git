package wxz.test.thread.concurrent;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(2);
		System.out.println("main:"+System.currentTimeMillis());
		new Thread(new DoTime(latch)).start();
		
		
		new Thread(new DoCount(latch)).start();
		new Thread(new DoCount(latch)).start();
//		latch.await();
	}

}

class DoCount implements Runnable {

	public CountDownLatch latch;

	public DoCount(CountDownLatch latch) {
		this.latch = latch;
	}

	public void run() {
		for (int i = 0; i < 100000000; i++) {
			if(i % 100000 == 0){
				System.out.println(i);
			}
		}
		System.out.println("count end");
		this.latch.countDown();
	}
}

class DoTime implements Runnable {

	private CountDownLatch latch;

	public DoTime(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		try {
			this.latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("start:" + start);
		System.out.println("end:" + end);
		System.out.println(end -start);
	}

}