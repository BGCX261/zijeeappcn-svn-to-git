package wxz.test.nio.socket.demo2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Date;

public class TestServer {
	// Charset and encoder for US-ASCII
	private static Charset charset = Charset.forName("GBK");
	private static CharsetEncoder encoder = charset.newEncoder();
	private static CharsetDecoder decoder = charset.newDecoder();
	
	
	public static void main(String[] args) throws Exception {

		ServerSocketChannel server = ServerSocketChannel.open();
		ServerSocket socket = server.socket();
		socket.bind(new InetSocketAddress(8013));
		server.configureBlocking(false);
		System.out.println("--before accept");
		ByteBuffer buffer = encoder.encode(CharBuffer.wrap("牛人分析《爱情公寓Ⅲ》隐藏剧情和疑点"));
		String str = "牛人分析《爱情公寓Ⅲ》隐藏剧情和疑点";
//		ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
		while (true) {
			SocketChannel sc = server.accept();
			if (null == sc) {
				System.out.print(".");
				Thread.sleep(2000);
			} else {

//				sc.read(buffer);
//				System.out.println("server read end");
//				System.out.println(new String(buffer.array()));
//				buffer.flip();
//				sc.write(buffer);
				String now = new Date().toString();
//				sc.write(encoder.encode(CharBuffer.wrap(now + "\r\n")));
//				sc.write(encoder.encode(CharBuffer.wrap(str + "\r\n")));
				buffer.rewind();// 重点   重绕此缓冲区。将位置设置为 0 并丢弃标记。 
				sc.write(buffer);
//				System.out.println("END:" + decoder.decode(buffer));
				System.out.println("em=nd");
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
