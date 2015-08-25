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

        System.out.println("最大内存 = " + max); 
        System.out.println("已分配内存 = " + total); 
        System.out.println("已分配内存中的剩余空间 = " + free); 
        System.out.println("最大可用内存 = " + usable);
	}
	
}
