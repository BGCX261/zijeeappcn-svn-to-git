package wxz.test.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestLinkedHashMap {

    public static void main(String args[]) {
        System.out.println("*************************LinkedHashMap*************");
        Map<Integer, String> map = new LinkedHashMap<Integer, String>();
        map.put(6, "apple");
        map.put(3, "banana");
        map.put(2, "pear");

        for (Iterator it = map.keySet().iterator(); it.hasNext();) {
            Object key = it.next();
            System.out.println(key + "=" + map.get(key));
        }

        System.out.println("*************************HashMap*************");
        Map<Integer, String> map1 = new HashMap<Integer, String>();
        map1.put(6, "apple");
        map1.put(3, "banana");
        map1.put(2, "pear");

        for (Iterator it = map1.keySet().iterator(); it.hasNext();) {
            Object key = it.next();
            System.out.println(key + "=" + map1.get(key));
        }
    }

}

//
//����:LinkedHashmap ���ص���put��ȥ�Ķ���λ��δ�����仯,��HashMap�ᷢ���仯.
//
//���ռ��£�
//
//javaΪ���ݽṹ�е�ӳ�䶨����һ���ӿ�java.util.Map;�����ĸ�ʵ����,�ֱ���HashMap Hashtable LinkedHashMap ��TreeMap.
//
//Map��Ҫ���ڴ洢��ֵ�ԣ����ݼ��õ�ֵ����˲�������ظ�(�ظ��˸�����),������ֵ�ظ���
//Hashmap ��һ����õ�Map,�����ݼ���HashCodeֵ�洢����,���ݼ�����ֱ�ӻ�ȡ����ֵ�����кܿ�ķ����ٶȣ�����ʱ��ȡ�����ݵ�˳������ȫ����ġ� 
//HashMap���ֻ����һ����¼�ļ�ΪNull;���������¼��ֵΪ Null;HashMap��֧���̵߳�ͬ��������һʱ�̿����ж���߳�ͬʱдHashMap;���ܻᵼ�����ݵĲ�һ�¡�
//�����Ҫͬ���������� Collections��synchronizedMap����ʹHashMap����ͬ��������������ʹ��ConcurrentHashMap��
//
//Hashtable�� HashMap����,���̳���Dictionary�࣬��ͬ����:���������¼�ļ�����ֵΪ��;��֧���̵߳�ͬ��������һʱ��ֻ��һ���߳���дHashtable,
//���Ҳ������ Hashtable��д��ʱ��Ƚ�����
//
//LinkedHashMap ��HashMap��һ�����࣬�����˼�¼�Ĳ���˳������Iterator����LinkedHashMapʱ���ȵõ��ļ�¼�϶����Ȳ����.Ҳ�����ڹ���ʱ�ô�����������Ӧ�ô�������
//�ڱ�����ʱ����HashMap������������������⣬��HashMap�����ܴ�ʵ�����ݽ���ʱ�������������ܻ�� LinkedHashMap����
//��ΪLinkedHashMap�ı����ٶ�ֻ��ʵ�������йأ��������޹أ���HashMap�ı����ٶȺ����������йء�
//
//TreeMapʵ��SortMap�ӿڣ��ܹ���������ļ�¼���ݼ�����,Ĭ���ǰ���ֵ����������Ҳ����ָ������ıȽ���������Iterator ����TreeMapʱ���õ��ļ�¼���Ź���ġ�
//
//һ������£������õ�������HashMap,��Map �в��롢ɾ���Ͷ�λԪ�أ�HashMap ����õ�ѡ�񡣵������Ҫ����Ȼ˳����Զ���˳���������
//��ôTreeMap����á������Ҫ�����˳����������ͬ,��ô��LinkedHashMap ����ʵ��,�������԰���ȡ˳��������.
//
//
//HashMap��һ����õ�Map�������ݼ���hashCodeֵ�洢���ݣ����ݼ�����ֱ�ӻ�ȡ����ֵ�����кܿ�ķ����ٶȡ�HashMap���ֻ����һ����¼�ļ�ΪNULL�����������¼��ֵΪNULL��
//
//HashMap��֧���߳�ͬ��������һʱ�̿����ж���߳�ͬʱдHashMap�����ܻᵼ�����ݵĲ�һ���ԡ������Ҫͬ����������Collections��synchronizedMap����ʹHashMap����ͬ����������
//
//
//Hashtable��HashMap���ƣ���ͬ���ǣ����������¼�ļ�����ֵΪ�գ���֧���̵߳�ͬ��������һʱ��ֻ��һ���߳���дHashtable�����Ҳ������Hashtable��д��ʱ��Ƚ�����
//
//
//LinkedHashMap�����˼�¼�Ĳ���˳������Iterator����LinkedHashMapʱ���ȵõ��ļ�¼�϶����Ȳ���ġ�
//
//
//�ڱ�����ʱ����HashMap��TreeMap�ܹ���������ļ�¼���ݼ�����Ĭ���ǰ���������Ҳ����ָ������ıȽ���������Iterator����TreeMapʱ���õ��ļ�¼���Ź���ġ�



















