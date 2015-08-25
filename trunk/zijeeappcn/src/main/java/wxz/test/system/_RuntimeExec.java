package wxz.test.system;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
public class _RuntimeExec extends Thread{
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        try {

            String classPath = System.getProperty("java.class.path");
            String cmd = "java -cp "
                    + classPath + " T2";
            System.out.println(cmd);
            final Process proc = Runtime.getRuntime().exec(cmd);

            new Thread() {
                public void run() {
                    try {
                        copy(proc.getInputStream(), System.out);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                };
            }.start();

            new Thread() {
                public void run() {
                    try {
                        copy(proc.getErrorStream(), System.err);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                };
            }.start();
            
            
            while (true) {
                System.out.println("T1");
                Thread.sleep(2 * 1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
	
	public static int copy(InputStream in, OutputStream out) throws IOException {
        int sum = 0;
        byte[] bytes = new byte[8];
        int n = -1;
        while ((n = in.read(bytes)) != -1) {
            out.write(bytes, 0, n);
            sum += n;
        }
        return sum;
    }
	
	public void run(){
		while(true){
			System.out.println("1");
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class T2 extends Thread {

	public static void main(String[] args) {
		T2  t = new T2();
		t.start();
	}
	
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(i);
			try {
				Thread.currentThread().sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
