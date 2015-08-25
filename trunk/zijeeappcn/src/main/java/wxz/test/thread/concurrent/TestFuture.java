package wxz.test.thread.concurrent;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestFuture {
    
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Account account = new Account();
        FutureTask<Integer> future = new FutureTask<Integer>(account);
        System.out.println("account 1:" + System.currentTimeMillis());
        new Thread(future).start();

        int r2 = new Random().nextInt(100);
        System.out.println("account 2:" + r2 + "," + System.currentTimeMillis());

        Integer r1 = (Integer) future.get();

        System.out.println("account all:" + (r1 + r2) + "," + System.currentTimeMillis());
        
        
    }
}

class Account implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {

        int result = new Random().nextInt(100);
        Thread.sleep(5000);
        System.out.println("account 1 result:" + result + "," + System.currentTimeMillis());
        return result;
    }
    
}