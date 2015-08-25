package wxz.test.regex;

public class TestReg {
	public static void main(String[] args) {
	    run3();
	}
	
	static void run3(){
        String regex = "([}]*?)";
        System.out.println("}1".matches(regex));
    }
	
	/**
	 * ²»ÊÇÄ³×Ö·û´®¡£
	 */
	static void run1(){
		String regex = "((?!0).)*";
		System.out.println("2".matches(regex));// true
		System.out.println("0".matches(regex));// false
	}
	
	/**
	 * ²»ÊÇÄ³×Ö·û´®¡£
	 */
	static void run2(){
		String regex = "((?!World).)*";
		System.out.println("World".matches(regex));// false
		System.out.println("1".matches(regex)); // true
		System.out.println("1World".matches(regex)); // false
	}
}
