package wxz.test.socket.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/** 
 * 模拟FTP服务器, 支持命令pwd,ls,get
 * 
 */

public class FtpServer {
  public static void main(String[] args)
    throws IOException{
    FtpServer server = new FtpServer();
    server.start();
  }
  
  public void start() throws IOException{
    ServerSocket ss = new ServerSocket(9000);
    while(true){
      Socket s = ss.accept();
      new Agent(s).start();
    }
  }
  class Agent extends Thread{
    Socket s;
    public Agent(Socket s) {
      this.s = s;
    }
    public void run() {
      try{
        InputStream in = s.getInputStream();
        OutputStream out = s.getOutputStream();
        IOUtils.println(out, "text,1");
        IOUtils.println(out, "欢迎使用FTP演示服务器!");
        while(true){
          String cmd = IOUtils.readLine(in).trim();
          if("pwd".equals(cmd)){
            pwd(out);
          }else if("ls".equals(cmd)){
            ls(out);
          }else if(cmd.startsWith("get ")){
            get(cmd, out);
          }else if("bye".equalsIgnoreCase(cmd)){
            IOUtils.println(out, "text,1");
            IOUtils.println(out, "Bye, Bye!");
            s.close();
          }else{
            IOUtils.println(out, "text,1");
            IOUtils.println(out, "只支持 pwd,ls get!");
          }
        }
      }catch(IOException e){
        e.printStackTrace();
      }
    }
  }
  public void pwd(OutputStream out) 
    throws IOException{
    File dir = new File(".");
    IOUtils.println(out, "text,1");
    IOUtils.println(out, dir.getAbsolutePath());
  }
  public void ls(OutputStream out)
    throws IOException{
    File dir = new File(".");
    File[] files = dir.listFiles();
    IOUtils.println(out, "text,"+files.length);
    for (File f : files) {
      IOUtils.println(out, f.getName());
    }
  }

  public void get(String cmd, OutputStream out)
    throws IOException{
    //cmd="get filename"
    String name = cmd.split("\\s+")[1];
    File file = new File(name);
    if(! file.exists()){
      IOUtils.println(out, "text,1");
      IOUtils.println(out, "没有文件呀!"+name);
      return;
    }
    IOUtils.println(out, 
        "file,"+file.length()+","+name);
    FileInputStream in = 
      new FileInputStream(file);
    IOUtils.cp(in, out);
    out.flush();
    in.close();
    IOUtils.println(out, "text,1");
    IOUtils.println(out, "发送成功:"+name);
  }
}





