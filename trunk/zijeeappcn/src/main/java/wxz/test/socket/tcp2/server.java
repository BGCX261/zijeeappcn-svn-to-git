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
			System.out.println("等待连接");
			Socket s=ss.accept();
			System.out.println("连接ok");
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
				out.println("你要点啥？");
				while (true){
					String str=in.nextLine();
				if(str.equals("1")){
					out.println("对不起没有鱼丸了");
				}	
				else if(str.equals("包子"))
				{
					out.println("有给你！bye！");
					s.close();
					break;
				}
				else
				{
					out.println("没有听清楚");
				}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		
			
			}
		}
	}

}
