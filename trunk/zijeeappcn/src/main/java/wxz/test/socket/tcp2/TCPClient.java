package wxz.test.socket.tcp2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
  public static void main(String[] args) 
    throws IOException {
    TCPClient client = new TCPClient();
    client.open();
  }
  public void open() throws IOException {
    Socket s = new Socket("localhost", 8000);
    InputStream in = s.getInputStream();
    OutputStream out = s.getOutputStream();
    //SysinReader ��ȡ����̨������Ϣ,ת����out
    new SysinReader(out).start();
    //SysoutWriter �����������͹�������Ϣд������̨
    new SysoutWriter(in).start();
  }
//SysinReader ��ȡ����̨������Ϣ,ת����out
  class SysinReader extends Thread{
    OutputStream out;
    public SysinReader(OutputStream out) {
      this.out = out;
      setDaemon(true);
    }
    public void run() {
      Scanner in = new Scanner(System.in);
      PrintWriter out = new PrintWriter(this.out, true);
      while(true){
        String s = in.nextLine();
        out.println(s);
      }
    }
  }
//SysoutWriter �����������͹�������Ϣд������̨
  class SysoutWriter extends Thread{
    InputStream in;
    public SysoutWriter(InputStream in) {
      this.in = in;
    }
    public void run() {
      Scanner in = new Scanner(this.in);
      String str;
      while(in.hasNextLine()){//"��� Ҫ��ɶ"
        str = in.nextLine();
        System.out.println(str);
      }
    }
  }
}






