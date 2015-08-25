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
      //invoke ����,������obj�����ϵ��÷���m,���ݲ���parmas
      Object val = m.invoke(obj, params);
      return val;//val �Ƿ����ķ���ֵ, ���������ǰ�װ����
      
      
      
    } catch (InvocationTargetException e){//Ŀ�귽���쳣
      throw new RuntimeException(e.getTargetException());
    } catch (Exception e) {//�����ĵ����쳣, ���������
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