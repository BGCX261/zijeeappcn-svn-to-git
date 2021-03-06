package wxz.test.nio.socket;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.net.InetSocketAddress;
import java.io.IOException;

public class BaiduReader {

	private Charset charset = Charset.forName("GBK");// 创建GBK字符集
	private SocketChannel socketChannel;

	public void readHTMLContent() {

		System.out.println(System.currentTimeMillis());
		try {
			InetSocketAddress socketAddress = new InetSocketAddress(
					"www.baidu.com", 80);
			// step1:打开连接
			socketChannel = SocketChannel.open(socketAddress);
			// step2:发送请求，使用GBK编码
			socketChannel.write(charset.encode("GET / HTTP/1.1\r\n\r\n"));
			// step3:读取数据
			ByteBuffer buffer = ByteBuffer.allocate(1024);// 创建1024字节的缓冲
			while (socketChannel.read(buffer) != -1) {
				buffer.flip();// flip方法在读缓冲区字节操作之前调用。
				System.out.println(charset.decode(buffer));
				// 使用Charset.decode方法将字节转换为字符串
				buffer.clear();// 清空缓冲
			}
			System.out.println(System.currentTimeMillis());
		} catch (IOException e) {
			System.err.println(e.toString());
		} finally {
			if (socketChannel != null) {
				try {
					socketChannel.close();
				} catch (IOException e) {
				}
			}
		}

	}

	public static void main(String[] args) {
		new BaiduReader().readHTMLContent();
	}

}