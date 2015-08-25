package wxz.test.nio.socket.demo2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class TestClient {
	// Charset and decoder for US-ASCII
	private static Charset charset = Charset.forName("GBK");
	private static CharsetDecoder decoder = charset.newDecoder();

	public static void main(String[] args) throws IOException {
		SocketChannel socket = SocketChannel.open();
		socket.connect(new InetSocketAddress("127.0.0.1", 8013));
//		socket.configureBlocking(false);
		// µÈ¼ÛµÄ
//		SocketChannel socketChannel = SocketChannel.open (new InetSocketAddress ("somehost", somePort));
		
		while(!socket.finishConnect()){
			System.out.print(".");
		}
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.clear();
//		while (socket.read(buffer) != -1) {// ERROR
		socket.read(buffer);
		System.out.println(",");
		buffer.flip(); 
		// Print the remote address and the received time
		CharBuffer cb = decoder.decode(buffer);
		System.out.print("client" + " : " + cb);
		socket.close();
	}
}
