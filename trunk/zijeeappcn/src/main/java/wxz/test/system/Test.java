package wxz.test.system;

import java.util.UUID;

public class Test {
	public static void main(String[] args) {
//		String a = "abc";
//		String b = "abc";
//		if(a==b){
//			System.out.println("a");
//		} else {
//			System.out.println("b");
//		}
	    
	    System.out.println(UUID.randomUUID().toString().length());
	    
        Runtime run = Runtime.getRuntime(); 

        long max = run.maxMemory(); 

        long total = run.totalMemory(); 

        long free = run.freeMemory(); 

        long usable = max - total + free; 

        System.out.println("����ڴ� = " + max); 
        System.out.println("�ѷ����ڴ� = " + total); 
        System.out.println("�ѷ����ڴ��е�ʣ��ռ� = " + free); 
        System.out.println("�������ڴ� = " + usable);
	}
	
}
