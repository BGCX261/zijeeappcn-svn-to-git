package wxz.test.generic;

import java.util.ArrayList;
import java.util.List;

public class TestGeneric {

    public static void method1(List<String> list){
        list.add("abc");
    }
    public static void method2(List list){
        list.add(123);
        
    }
    public static void main(String[] args) {
        
        List<String> list = new ArrayList<String>();
        method1(list);
        method2(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
//    abc
//    Exception in thread "main" java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
//        at com.test.generic.TestGeneric.main(TestGeneric.java:23)
}
