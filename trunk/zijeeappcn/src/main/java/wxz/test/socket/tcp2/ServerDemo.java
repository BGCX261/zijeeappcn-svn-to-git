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
    //绑定服务端端口号, 不能重复绑定
    //端口号: 0~65535, 1K以下留给系统使用, WEB(80) FTP(21)
    ServerSocket ss = new ServerSocket(8000);
    while(true){
      System.out.println("等待连接....");
      Socket s = ss.accept();//IO Block 等待客户连接
      //如果连接成就结束IO Block, 返回s
      System.out.println("连接成功");
      new Service(s).start();//创建客户服务线程(服务员),并启动
    }
  }
  class Service extends Thread{//服务员类型
    Socket s;
    public Service(Socket s) {
      this.s = s;
    }
    public void run() {
      try {
        Scanner in = new Scanner(s.getInputStream());
        PrintWriter out = 
          new PrintWriter(s.getOutputStream(), true);
        out.println("你好, 要点啥?");
        while(true){
          String str = in.nextLine();//IO Block等待用户输入
          //str="HI"
          if(str.equals("鱼丸")){
            out.println("没有鱼丸!");
          }else if(str.equals("包子")){
            out.println("有给你! bye!");
            s.close();
            break;
          }else{
            out.println("啥, 不懂!");
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}


