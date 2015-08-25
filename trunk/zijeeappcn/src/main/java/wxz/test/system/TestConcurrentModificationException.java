package wxz.test.system;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TestConcurrentModificationException {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String a = "111";
		String b= "222";
		Set set = new HashSet();
		set.add(a);
		set.add(b);
		
		Iterator aa = set.iterator();
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			System.out.println(object);
			if(object.equals("111")){
				iterator.remove();
			}
		}
		System.out.println("===");
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			System.out.println(object);
		}
		System.out.println("==end==");
	}
}
