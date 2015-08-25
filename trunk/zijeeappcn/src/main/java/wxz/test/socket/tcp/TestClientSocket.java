package wxz.test.socket.tcp;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class TestClientSocket {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 4700);
			socket.setSoTimeout(5539900);
			java.io.OutputStream out = socket.getOutputStream();

			byte[] data = "hello world".getBytes();
			out.write(data);
			out.flush();
			socket.shutdownOutput();
			System.out.println("====after output====");
//////////////////////////////////
			byte[] buffer = new byte[1024];
			int len = -1;
			FileOutputStream fout = new FileOutputStream(
					"d:/response.xml");
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			InputStream in = socket.getInputStream();
			while ((len = in.read(buffer, 0, buffer.length)) > 0) {
				bout.write(buffer, 0, len);
			}
			in.close();
			bout.flush();
			bout.close();
			
			byte[] rdata = bout.toByteArray();
			System.out.println("The data from server:"+new String(rdata));
			fout.write(rdata);
			
			fout.flush();
			fout.close();
			socket.close();
			System.out.println("===client end===");
		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}

}
