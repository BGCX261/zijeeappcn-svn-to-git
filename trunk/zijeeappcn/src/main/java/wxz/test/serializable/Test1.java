package wxz.test.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test1 {

	public static void main(String[] args) throws Exception {
		// 写入对象，修改对象对应bean,重新读入bean,值为null(即使有初始化值)
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
