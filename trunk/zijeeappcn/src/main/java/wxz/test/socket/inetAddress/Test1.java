package wxz.test.socket.inetAddress;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Test1 {
	public static void main(String[] args) throws UnknownHostException, IOException {
		InetAddress a = InetAddress.getByName("www.baidu.com");
//		Socket s = new Socket("www.baidu.com",80);
		
		Socket s = new Socket(a,80);
		System.out.println("=end=");
	}
}
