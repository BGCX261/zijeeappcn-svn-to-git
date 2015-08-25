package wxz.test.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class RefDemo {
    public static void main(String[] args) {
        check("ABC");
        System.out.println();
        check(5);

    }

    public static void check(Object obj) {
        // Class: 类/类型
        Class cls = obj.getClass();// 获取对象的类型
        System.out.println(cls.getName()); // 类名
        // Field: 字段, 属性
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f.getType() + " " + f.getName());
        }
        // Method 方法
        Method[] methods = cls.getDeclaredMethods();
        for (Method m : methods) {
            String name = m.getName();
            Class[] types = m.getParameterTypes();
            System.out.println(name + Arrays.toString(types));
        }
        // Constructor 构造器
        Constructor[] constructors = cls.getConstructors();
        for (Constructor c : constructors) {
            String name = c.getName();
            Class[] types = c.getParameterTypes();
            System.out.println(name + Arrays.toString(types));
        }
    }
}
