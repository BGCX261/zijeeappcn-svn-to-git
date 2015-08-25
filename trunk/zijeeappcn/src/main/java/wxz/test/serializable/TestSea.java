package wxz.test.serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Date;

public class TestSea {

	// ���л������ļ�
	public static void serialize(String fileName) {
		try {
			// ����һ�������������������������ļ�
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(fileName));

			UserInfo user = new UserInfo("renyanwei", "888888", 20);
			out.writeObject(user); // ���л�һ����Ա����

			out.close();
		} catch (Exception x) {
			System.out.println(x.toString());
		}

	}

	// ���ļ������л�������
	public static void deserialize(String fileName) {
		try {
			// ����һ�����������������ļ���ȡ����
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					fileName));

			// ��ȡUserInfo���󲢵�������toString()����
			UserInfo user = (UserInfo) (in.readObject());
			System.out.println(user.toString());

			in.close();
		} catch (Exception x) {
			System.out.println(x.toString());
		}

	}

	public static void main(String[] args) {

		serialize("test.txt");
		System.out.println("���л����");

		deserialize("test.txt");
		System.out.println("�����л����");
	}

}

class UserInfo implements java.io.Serializable {
	public String userName;
	public transient String userPass;
	public int userAge;

	public UserInfo() {
	}

	public UserInfo(String username, String userpass, int userage) {
		this.userName = username;
		this.userPass = userpass;
		this.userAge = userage;
	}

	// �����л�����ʱ,�÷����Զ�����
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("����ִ�����л�����");
		// ���������л�ʱд������ı���
		Date d = new Date();
		out.writeObject(d);
		// ֻ���л�userName,userPass����
		out.writeObject(userName);
		out.writeObject(userPass);
	}

	// �������л�����ʱ,�÷����Զ�����
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		System.out.println("����ִ�з����л�����");
		Date d = (Date) in.readObject();
		System.out.println(d);
		this.userName = (String) in.readObject();
		this.userPass = (String) in.readObject();
	}

	public String toString() {
		return "�û���: " + this.userName + ";���룺" + this.userPass + ";���䣺"
				+ this.userAge;
	}
}
