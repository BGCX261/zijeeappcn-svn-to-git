package orj.jzkangta.proxydemo02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyFunction implements InvocationHandler {
	private ComputerMaker cm;
	
	public void youHui(){
		System.out.println("我给你一些优惠。。。");
	}
	
	public void giveMouse(){
		System.out.println("我还要送你一个鼠标。。。 ");
	}
	public Object invoke(Object arg0, Method arg1, Object[] arg2)
			throws Throwable {
		String type=(String)arg2[0];
		if(type.equals("联想")||type.equals("三星")){
			if(cm==null){
				cm=new ComputerMaker();
				youHui();
				giveMouse();
				arg1.invoke(cm, type);
			}
		}else{
			System.out.println("我没有你要的这个牌子的电脑。。。。");
		}
		return null;
	}

}
