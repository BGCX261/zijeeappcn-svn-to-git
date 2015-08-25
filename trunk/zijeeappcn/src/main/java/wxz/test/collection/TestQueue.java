package wxz.test.collection;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class TestQueue {
    public static void main(String[] args) {
        // LinkedList ͬʱʵ�� List Queue
        LinkedList<String> link = new LinkedList<String>();
        Queue<String> q = link;
        List<String> l = link;
        q.add("ONE");
        l.add("TWO");
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
        
        // ����        �޽����ȼ�����
        Queue<String> q2 = new PriorityQueue<String>();
        q2.add("ONE");
        q2.add("TWO");
        q2.add("THREE");
        q2.add("FOUR");
        System.out.println("peek():"+q2.peek());
        
        // ����   �ڲ�ʹ�� TreeMapʵ��,key������ӵ��ַ���
        Set<String> set = new TreeSet<String>();
        set.add("BB");
        set.add("BB");
        set.add("ACC");
        set.add("BAA");
        // set.add(null); // java.lang.NullPointerException
        Object[] setArray = set.toArray();
        System.out.println(setArray[0]);
        String[] array = set.toArray(new String[] {});
        System.out.println(array[0] + "," + array.length);
        
        
//        TreeMap : ���ں����(�Գƶ���B��)���ݽṹ��ʵ�֡��鿴�������򡰼�ֵ�ԡ�ʱ�����ǻᱻ����(������Comparabel��Comparator����)��
//        TreeMap���ص����ڣ���õ��Ľ���Ǿ�������ġ�TreeMap��Ψһ�Ĵ���subMap()������Map�������Է���һ��������
//        http://hi.baidu.com/20065562/item/8ba7d823d1310d172b0f1caf
        
        Map tmap = new TreeMap();
        tmap.put("AA", "AA");
        tmap.put("BB", "BB");
        tmap.put("BB", "BB");

        Set mset = tmap.entrySet();

        
        
        
        
        
    }
}
