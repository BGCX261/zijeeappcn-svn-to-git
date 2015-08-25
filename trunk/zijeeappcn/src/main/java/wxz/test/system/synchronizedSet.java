package wxz.test.system;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class synchronizedSet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Set ConnectionPool =  Collections.synchronizedSet(new LinkedHashSet());
//		Set	FreeConnectionPool =Collections.synchronizedSet(new LinkedHashSet());
//		System.out.println("==end==");
		
		Set set = new LinkedHashSet();
		String a = "aaa";
		String b = "bbb";
		String c = "ccc";
		set.add(a);
		set.add(b);
		set.add(c);
		
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			System.out.println(object);
			if(object.equals(a)){
				System.out.println("====++==");
				object = null;
			}
		
		}
		System.out.println("=======");
		System.out.println(set.size());
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			System.out.println(object);
		}
	}

}
