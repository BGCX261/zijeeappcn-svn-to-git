package wxz.test.socket.tcp2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {

	
	public static void main(String[] args) throws IOException {
		
		server server = new server();
		    server.start();
	}
	public void start() throws IOException{
		ServerSocket ss=new ServerSocket(8000);
		while(true){
			System.out.println("�ȴ�����");
			Socket s=ss.accept();
			System.out.println("����ok");
			new Service(s).start();
		}
	}
	class Service extends Thread{
		Socket s;
		public Service(Socket s)
		{
			this.s=s;
		}
		public void run(){
			try {
				Scanner in=new Scanner(s.getInputStream());
				PrintWriter out=new PrintWriter(s.getOutputStream(),true);
				out.println("��Ҫ��ɶ��");
				while (true){
					String str=in.nextLine();
				if(str.equals("1")){
					out.println("�Բ���û��������");
				}	
				else if(str.equals("����"))
				{
					out.println("�и��㣡bye��");
					s.close();
					break;
				}
				else
				{
					out.println("û�������");
				}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		
			
			}
		}
	}

}
