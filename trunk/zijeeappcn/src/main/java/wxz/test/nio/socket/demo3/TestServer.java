package wxz.test.nio.socket.demo3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TestServer {

	public static void main(String[] args) throws Exception {

		ServerSocketChannel server = ServerSocketChannel.open();
		ServerSocket socket = server.socket();
		socket.bind(new InetSocketAddress(1234));
		server.configureBlocking(false);
		System.out.println("--before accept");
		while (true) {
			SocketChannel sc = server.accept();
			if (null == sc) {
				System.out.print(".");
				Thread.sleep(2000);
			} else {

////				sc.read(buffer);
////				System.out.println("server read end");
////				System.out.println(new String(buffer.array()));
//				buffer.rewind();
//				sc.write(buffer);
//				System.out.println(new String(buffer.array()));
//				System.out.println("server write end");
//				
				
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				// buffer.put("0123456789".getBytes());
				// System.out.println(new String(buffer.array()));
				// socket.write(buffer);
				// System.out.println("client write end");
				//
				// buffer.clear();
				// buffer.flip();

				sc.read(buffer);
				buffer.flip();
				System.out.println(new String(buffer.array()));
				buffer.clear();
				
				sc.close();
			}
		}

	}

}

class SocketServer {
	public SocketServer() throws IOException {
		ServerSocketChannel server = ServerSocketChannel.open();
		server.accept();
	}
}
