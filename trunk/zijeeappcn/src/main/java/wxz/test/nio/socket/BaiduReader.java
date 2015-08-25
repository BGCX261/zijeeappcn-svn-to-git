package wxz.test.nio.socket;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.net.InetSocketAddress;
import java.io.IOException;

public class BaiduReader {

	private Charset charset = Charset.forName("GBK");// ����GBK�ַ���
	private SocketChannel socketChannel;

	public void readHTMLContent() {

		System.out.println(System.currentTimeMillis());
		try {
			InetSocketAddress socketAddress = new InetSocketAddress(
					"www.baidu.com", 80);
			// step1:������
			socketChannel = SocketChannel.open(socketAddress);
			// step2:��������ʹ��GBK����
			socketChannel.write(charset.encode("GET / HTTP/1.1\r\n\r\n"));
			// step3:��ȡ����
			ByteBuffer buffer = ByteBuffer.allocate(1024);// ����1024�ֽڵĻ���
			while (socketChannel.read(buffer) != -1) {
				buffer.flip();// flip�����ڶ��������ֽڲ���֮ǰ���á�
				System.out.println(charset.decode(buffer));
				// ʹ��Charset.decode�������ֽ�ת��Ϊ�ַ���
				buffer.clear();// ��ջ���
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