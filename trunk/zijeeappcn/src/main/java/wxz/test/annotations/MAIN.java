package wxz.test.annotations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MAIN {

    /**
     * @param args
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args) throws Exception {

        Class cls = Class.forName("wxz.test.annotations.Data");
        Class cls1 = Class.forName("wxz.test.annotations.Data1");
        Class cls2 = Class.forName("wxz.test.annotations.Data2");
        Class cls3 = Class.forName("wxz.test.annotations.Data3");
        Class clsno = Class.forName("wxz.test.annotations.DataNo");
        List<Class> list = new ArrayList<Class>();
        list.add(cls2);
        list.add(cls1);
        list.add(cls);
        list.add(cls3);
        list.add(clsno);
        for (Class clazz : list) {

            if (clazz.isAnnotationPresent(ClazzAnno.class)) {
                ClazzAnno anno = (ClazzAnno) clazz.getAnnotation(ClazzAnno.class);
                Method method = clazz.getMethod("getData");
                if(method != null && method.isAnnotationPresent(MethodAnno.class)){
                    System.out.println("YES:" + clazz.getName() + ",key=" + anno.key() +",methodAnno");
                } else {
                    System.out.println("YES:" + clazz.getName() + ",key=" + anno.key());
                }
            
            } else {
                System.out.println("NO:" + clazz.getName());
            }
            
        }

    }

}
