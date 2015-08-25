package wxz.test.zfunction;

import java.util.Date;

public class _StingFormat {
	public static void main(String[] args) {
//		test2();
	    
	    System.out.println(    String.format("abcd %1s %2s %3s $$", "Hello","World","World"));
	}
	
	private static void test3(){
		String a = "abc";
		String b = "abc";
		if(a==b){
			System.out.println("a");
		} else {
			System.out.println("b");
		}
	}
	
	private static void test2(){
//		参考 java doc:java.util.Formatter

		System.out.println(	String.format("abcd %1s %2s $$", "Hello","World"));
		//-> Hello<- 
		System.out.println(	String.format("->%s<-", "Hello")); 
		 //->1 Hello<-
		System.out.println(	String.format("->%d %s<-", 1 ,"Hello"));
		//【-0003,123】
		System.out.println(String.format("%1$,09d", -3123));
		//【      -31】
		System.out.println(String.format("%1$9d", -31));
		//【-31      】
		System.out.println(String.format("%1$-9d", -31));
		//【     (31)】
		System.out.println(String.format("%1$(9d", -31));
		//【   0x1639】
		System.out.println(String.format("%1$#9x", 5689));
		// 【12%】对百分比符号进行格式化
		System.out.println(String.format("%1$d%%", 12));
		// 平台独立的行分隔符System.getProperty("line.separator")
		System.out.println(String.format("%n"));

		System.out.println(String.format("%1$tm-%1$te-%1$tY",new Date()));
		System.out.println(String.format("%1$Tr%1$tH %1$TB%1$Tb %1$Ta",new Date()));

	}

	private static void test1() {
		String[] s = new String[] { "1", "2", };
		String[] s1 = new String[] { "1", "2" };
		System.out.println(s.length);
		System.out.println(s1.length);
		
		String ss ="aaaa";
		String[] sass = ss.split("[.]");
		System.out.println(sass[sass.length -1]);
		
		String s2 = new String("aaaa");
		System.out.println(s2 == ss);
	}
}
