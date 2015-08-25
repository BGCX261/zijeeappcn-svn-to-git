package wxz.test.nio.socket.demo3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TestClient {

	public static void main(String[] args) throws IOException {
		SocketChannel socket = SocketChannel.open();
		socket.connect(new InetSocketAddress("127.0.0.1", 1234));
//		socket.configureBlocking(false);
		// �ȼ۵�
		// SocketChannel socketChannel = SocketChannel.open (new
		// InetSocketAddress ("somehost", somePort));

		while (!socket.finishConnect()) {
			System.out.print(".");
		}
		System.out.println();
//		Socket client = socket.socket();
		ByteBuffer buffer = ByteBuffer.wrap("ţ�˷��������鹫Ԣ�����ؾ�����ɵ�".getBytes());
		
//		buffer.rewind();
		socket.write(buffer);
		System.out.println(new String(buffer.array()));
		System.out.println("client write end");
		
		// System.out.println(new String(buffer.array()));
	}
}
