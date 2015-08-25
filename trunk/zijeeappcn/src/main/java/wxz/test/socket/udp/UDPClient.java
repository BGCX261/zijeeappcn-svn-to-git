package wxz.test.socket.udp;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class UDPClient {

	public static void main(String[] args) throws Exception {
		DatagramSocket socket = new DatagramSocket();

		socket.connect(InetAddress.getByName("localhost"), 514);

		String test = "December 09 14:11:36 2011 SA-2.BOMCϵͳ~1~2011-12-04 20:38:20~133.96.32.1:50681~133.96.32.4:1521~����澯:BOMC���ܺ������ݿ�����û�����~��~1112042038200001039~SELECT * FROM FM_PREPROCESS WHERE EUID = 1002276";
		byte[] b = test.getBytes();
		int size = b.length;
		DatagramPacket out = new DatagramPacket(b, size);

		socket.send(out);
	}
}
