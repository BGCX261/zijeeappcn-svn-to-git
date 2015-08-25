package wxz.test.reflect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TestMethod {

    /**
     * 获得预览用的数据
     */
    public Lz007001 getLz007001(PreviewEntity en) {
        Lz007001 bo = new Lz007001();
        Map map = getEntityValue(en);
        Method[] methods = Lz007001.class.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method m = methods[i];
            String methodName = m.getName();
            if (!methodName.startsWith("set")) {
                // 不是set方法就放过它。
                continue;
            }
            String fieldName = methodName.substring(3);
            Object param = map.get(fieldName.toUpperCase());
            try {
                m.invoke(bo, new Object[] { param });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bo;
    }

    public Map getEntityValue(PreviewEntity en) {
        Map map = new HashMap();
        Method[] methods = PreviewEntity.class.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method m = methods[i];
            String methodName = m.getName();
            if (!methodName.startsWith("get")) {
                // 不是get方法就饶他一命
                continue;
            }
            try {
                String fieldName = methodName.substring(3);
                Object obj = m.invoke(en, new Object[] {});
                map.put(fieldName.toUpperCase(), obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}

class Lz007001 {
}

class PreviewEntity {

}
