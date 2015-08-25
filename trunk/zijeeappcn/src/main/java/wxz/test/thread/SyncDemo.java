package wxz.test.thread;
/** 同步安全性问题 */
public class SyncDemo {
  public static void main(String[] args) {
    Table table = new Table();//一张桌子对象,中有两个人
    table.start();
  }
}
class Table{
  int beans=20;//豆子数量
  Person1 p1;//第一个人
  Person2 p2;//第二个人
  public void start(){//启动一下
    p1 = new Person1();
    p2 = new Person2();
    p1.start();
    p2.start();
  }
  //Object monitor = new Object();
  public synchronized int getBean(){//取豆子方法
    //synchronized(this){
      if(beans==0){//检查是否取完
        throw new RuntimeException("吃完了");
      }
      //Thread.yield();
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      int bean = beans;
      beans--;//减少豆子数量
      return bean;//取回一个豆子
    //}`
  }
  class Person1 extends Thread{
    public void run() {
      while(true){
        System.out.println("p1吃了:"+getBean());
        Thread.yield();
      }
    }
  }
  class Person2 extends Thread{
    public void run() {
      while(true){
        System.out.println("p2吃了:"+getBean());
        Thread.yield();
      }
    }
  }
}
