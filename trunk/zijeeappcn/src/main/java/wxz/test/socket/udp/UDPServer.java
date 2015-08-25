package wxz.test.socket.udp;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

public class UDPServer {
	public static void main(String[] args) throws Exception {

		DatagramSocket server = new DatagramSocket(514, null);

		byte[] inBuffer = new byte[1024];
		DatagramPacket inGram = new DatagramPacket(inBuffer, inBuffer.length);
		while (true) {
			server.receive(inGram);
			String msgBuf = new String(inGram.getData(), 0, inGram.getLength());
			System.out.println("date:" + new Date() + ",buf:" + msgBuf);
		}
	}
}
