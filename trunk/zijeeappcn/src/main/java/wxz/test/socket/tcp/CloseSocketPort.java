package wxz.test.socket.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class CloseSocketPort {
	public static void main(String[] args) throws UnknownHostException, IOException {
//		Socket socket = new Socket("127.0.0.1",9001);
		ServerSocket server = new ServerSocket(9001);
		
//		socket.close();
		server.close();
		System.out.println("===end===");
	}

}
