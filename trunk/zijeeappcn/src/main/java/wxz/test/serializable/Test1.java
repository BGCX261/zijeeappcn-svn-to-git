package wxz.test.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test1 {

	public static void main(String[] args) throws Exception {
		// д������޸Ķ����Ӧbean,���¶���bean,ֵΪnull(��ʹ�г�ʼ��ֵ)
		 TestBean bean = new TestBean();
		 FileOutputStream fos = new FileOutputStream("e://test1.txt");
		 ObjectOutputStream oos = new ObjectOutputStream(fos);
		 oos.writeObject(bean);

		FileInputStream fis = new FileInputStream("e://test1.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		TestBean stu1 = (TestBean) ois.readObject();
		System.out.println(stu1);
	}

}

class TestBean implements java.io.Serializable {

	private static final long serialVersionUID = 1158864087203768634L;

	private String str = "aaa";
	private String str1 = "aaa";

	public String toString() {
		String rst = (super.toString() + "," + "str:" + str);
		rst += ",str1:" + str1;
		return rst;
	}

}
