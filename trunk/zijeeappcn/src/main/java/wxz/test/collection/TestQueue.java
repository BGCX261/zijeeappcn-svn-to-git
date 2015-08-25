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
        // LinkedList 同时实现 List Queue
        LinkedList<String> link = new LinkedList<String>();
        Queue<String> q = link;
        List<String> l = link;
        q.add("ONE");
        l.add("TWO");
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
        
        // 排序        无界优先级队列
        Queue<String> q2 = new PriorityQueue<String>();
        q2.add("ONE");
        q2.add("TWO");
        q2.add("THREE");
        q2.add("FOUR");
        System.out.println("peek():"+q2.peek());
        
        // 排序   内部使用 TreeMap实现,key就是添加的字符串
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
        
        
//        TreeMap : 基于红黑树(对称二叉B树)数据结构的实现。查看“键”或“键值对”时，它们会被排序(次序由Comparabel或Comparator决定)。
//        TreeMap的特点在于，你得到的结果是经过排序的。TreeMap是唯一的带有subMap()方法的Map，它可以返回一个子树。
//        http://hi.baidu.com/20065562/item/8ba7d823d1310d172b0f1caf
        
        Map tmap = new TreeMap();
        tmap.put("AA", "AA");
        tmap.put("BB", "BB");
        tmap.put("BB", "BB");

        Set mset = tmap.entrySet();

        
        
        
        
        
    }
}
