package wxz.test.thread.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCyclicBarrier {
    public static final int second = 100;

    // ͽ����Ҫ��ʱ��: Shen
    private static int[] timeForWalk = { 5, 8, 15 };
    private static int[] timeForSelf = { 1, 3, 4 };
    // ���δ��
    private static int[] timeForBus = { 2, 4, 6 };

    public static void main(String[] args) { // ��������
        Runnable runner = new Runnable() {
            public void run() {
                System.out.println("we all here");
            }
        };
        CyclicBarrier barrier = new CyclicBarrier(3, runner);
        // ʹ���̳߳�
        ExecutorService exec = Executors.newFixedThreadPool(3);
        exec.submit(new Tour(barrier, "WalkTour", timeForWalk));
        exec.submit(new Tour(barrier, "SelfTour", timeForSelf));
        exec.submit(new Tour(barrier, "BusTour", timeForBus));
        
        System.out.println("end");
        exec.shutdown();
    }

    static class Tour implements Runnable {
        private int[] timeForUse;
        private CyclicBarrier barrier;
        private String tourName;

        public Tour(CyclicBarrier barrier, String tourName, int[] timeForUse) {
            super();
            this.timeForUse = timeForUse;
            this.barrier = barrier;
            this.tourName = tourName;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(timeForUse[0] * second);
                System.out.println(nowTime() + tourName + "reached shenzhen");
                barrier.await();//
                Thread.sleep(timeForUse[1] * second);
                System.out.println(nowTime() + tourName + " Reached guangzhou");
                barrier.await();// ������ת
                Thread.sleep(timeForUse[2] * second);
                System.out.println(nowTime() + tourName + " Reached chongqing");
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

    }

    static String nowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date()) + ": ";
    }

}