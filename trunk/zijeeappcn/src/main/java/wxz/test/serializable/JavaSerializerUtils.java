package wxz.test.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JavaSerializerUtils {

	public static byte[] serialize(Object obj) {

		ByteArrayOutputStream ops = new ByteArrayOutputStream();
		try {
			ObjectOutputStream out = new ObjectOutputStream(ops);
			out.writeObject(obj);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] bytes = ops.toByteArray();
		return bytes;

	}

	public static Object deserialize(byte[] bytes) {

		ByteArrayInputStream ips = new ByteArrayInputStream(bytes);
		Object value = null;
		try {
			ObjectInputStream in = new ObjectInputStream(ips);
			value = in.readObject();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return value != null ? value : bytes;
	}

	public static void main(String[] args) {

		System.out.println(JavaSerializerUtils.deserialize(JavaSerializerUtils
				.serialize("scscsdcsd")));
	}

}
