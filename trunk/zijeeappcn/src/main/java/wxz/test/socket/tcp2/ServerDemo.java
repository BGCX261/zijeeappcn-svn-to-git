package wxz.test.socket.tcp2;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ServerDemo {
  
  public static void main(String[] args)
    throws IOException{
    ServerDemo server = new ServerDemo();
    server.start();
  }
  public void start() throws IOException{
    //�󶨷���˶˿ں�, �����ظ���
    //�˿ں�: 0~65535, 1K��������ϵͳʹ��, WEB(80) FTP(21)
    ServerSocket ss = new ServerSocket(8000);
    while(true){
      System.out.println("�ȴ�����....");
      Socket s = ss.accept();//IO Block �ȴ��ͻ�����
      //������ӳɾͽ���IO Block, ����s
      System.out.println("���ӳɹ�");
      new Service(s).start();//�����ͻ������߳�(����Ա),������
    }
  }
  class Service extends Thread{//����Ա����
    Socket s;
    public Service(Socket s) {
      this.s = s;
    }
    public void run() {
      try {
        Scanner in = new Scanner(s.getInputStream());
        PrintWriter out = 
          new PrintWriter(s.getOutputStream(), true);
        out.println("���, Ҫ��ɶ?");
        while(true){
          String str = in.nextLine();//IO Block�ȴ��û�����
          //str="HI"
          if(str.equals("����")){
            out.println("û������!");
          }else if(str.equals("����")){
            out.println("�и���! bye!");
            s.close();
            break;
          }else{
            out.println("ɶ, ����!");
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}


