package wxz.test.system;

public class _String {

	public static void main(String[] args) {
		// Properties p = System.getProperties();
		//
		// Set set = p.keySet();
		// for (Iterator iterator = set.iterator(); iterator.hasNext();) {
		// String key = (String ) iterator.next();
		// System.out.println(key + " = "+ p.getProperty(key));
		// }
		String s = "123456702012345678";
		System.out.println(s.substring(7, 9));

		
		
		String s1 = new String("ABC");
		String s2 = "ABC";
		String s3 = new String("ABC");
		System.out.println(s1 == s3);
		System.out.println(s1.equals(s3));
		System.out.println("----------");
		System.out.println(s1 == s2);
		
		
		String s4 =new String("rmiregistry.usage=\u7528\u6CD5\uFF1A {0} <\u9009\u9879> <\u7AEF\u53E3>\n\n\u5176\u4E2D\uFF0C<\u9009\u9879> \u5305\u62EC\uFF1A\n  -J<runtime \u6807\u8BB0> \u5C06\u53C2\u6570\u4F20\u9012\u5230 java \u89E3\u91CA\u7A0B\u5E8Frmiregistry.port.badnumber=\u7AEF\u53E3\u53C2\u6570\uFF1A{0}, \u4E0D\u662F\u6570\u5B57\u3002");
		System.out.println(s4);

	}

}
