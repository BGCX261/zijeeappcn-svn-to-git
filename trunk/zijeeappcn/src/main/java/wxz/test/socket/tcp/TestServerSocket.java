package wxz.test.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServerSocket {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		ServerSocket server = new ServerSocket(4700);
		Socket client = server.accept();
		System.out.println("====after accept====");
		// get data from client
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		// send data to client
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		String line = in.readLine();
		out.println("you input is :" + line);
		out.close();
		in.close();
		server.close();
		System.out.println("===server end===");
	}
}
