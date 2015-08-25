package wxz.test.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CallDemo {
  public static void main(String[] args)
    throws Exception{
		Class cls = Foo.class;
		// Foo foo = new Foo();
		Object obj = cls.newInstance();
		Class[] types = new Class[] { int.class };
		Object[] params = new Object[] { 3 };
		Object val = call(obj, "add", types, params);
		System.out.println(val);// 6
		val = call("ABC", "length", new Class[] {}, new Object[] {});
		System.out.println(val);// 3
    
  }
  public static Object call(Object obj, 
      String method, Class[] types, Object[] params){
    Class cls = obj.getClass();
    try {
      Method m = cls.getDeclaredMethod(method, types);
      //invoke 调用,这里在obj对象上调用方法m,传递参数parmas
      Object val = m.invoke(obj, params);
      return val;//val 是方法的返回值, 基本类型是包装类型
      
      
      
    } catch (InvocationTargetException e){//目标方法异常
      throw new RuntimeException(e.getTargetException());
    } catch (Exception e) {//其他的调用异常, 参数错误等
      throw new RuntimeException(e);
    }
  }
}
class Foo{
  int a = 1;
  int b = 2;
  public int add(int c){
    return a+b+c;
  }
  public int add(int c, int d){
    return a+b+c+d;
  }
}