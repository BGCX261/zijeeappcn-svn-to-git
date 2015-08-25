package wxz.test.reflect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TestMethod {

    /**
     * ���Ԥ���õ�����
     */
    public Lz007001 getLz007001(PreviewEntity en) {
        Lz007001 bo = new Lz007001();
        Map map = getEntityValue(en);
        Method[] methods = Lz007001.class.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method m = methods[i];
            String methodName = m.getName();
            if (!methodName.startsWith("set")) {
                // ����set�����ͷŹ�����
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
                // ����get����������һ��
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
