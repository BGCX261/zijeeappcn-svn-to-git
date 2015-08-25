package wxz.test.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestEntity {
	private String code;
	private String name;

	public static void main(String[] args) throws SecurityException,
			ClassNotFoundException, IllegalArgumentException,
			IllegalAccessException, InstantiationException,
			InvocationTargetException {
		Class cla = Class.forName("com.test.reflect.TestEntity");
		Object obj = Class.forName("com.test.reflect.TestEntity").newInstance();
		setField(cla, obj, "code", new Object[] { "dddsAdd" });
		setField(cla, obj, "name", new Object[] { "ddddsd" });
		// System.out.println(s.getCode());
		// System.out.println(s.getName());
		System.out.println("d");
	}

	void getField() throws SecurityException, ClassNotFoundException,
			IllegalArgumentException, IllegalAccessException {
		TestEntity obj = new TestEntity();
		obj.setName("name value");
		obj.setCode("code value");

		Field[] fds = Class.forName("com.test.reflect.TestEntity")
				.getDeclaredFields();
		System.out.println(fds.length);
		for (int i = 0; i < fds.length; i++) {
			System.out.println(fds[i].getName());// 这样能获取属性对应的值
			System.out.println(fds[i].get(obj));
		}
	}

	static Object setField(Class cla, Object obj, String name, Object[] value)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IllegalArgumentException,
			InvocationTargetException {
		Method[] methods = cla.getMethods();
		for (int i = 0; i < methods.length; i++) {
			System.out.println(methods[i].getName());
			Method method = methods[i];
			String methodName = method.getName();
			if (methodName.equalsIgnoreCase("set" + name)) {
				method.invoke(obj, value);
			}
		}
		return obj;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}