package wxz.test.zfunction2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
//http://www.iteye.com/problems/54114
//jvisualvm
//http://www.51testing.com/html/55/n-237855.html

// 输出GC日志
//-XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCApplicationStoppedTime -Xloggc: gc.log

// list 中 对象分组。。
public class _Class_student {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        
        OrderItemInfoBO s1 = new OrderItemInfoBO("N1", "C1", "M_1", "N_1");
        OrderItemInfoBO s2 = new OrderItemInfoBO("N2", "C2", "M_2", "N_2");
        OrderItemInfoBO s3 = new OrderItemInfoBO("N3", "C1", "M_1", "N_1");
        OrderItemInfoBO s4 = new OrderItemInfoBO("N4", "C3", "M_1", "N_2");

        List<OrderItemInfoBO> list = new ArrayList<OrderItemInfoBO>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);

        // 排序
        Collections.sort(list);

        System.out.println("-----------------------------------------------------------------------------");

     // 整理列表信息。相同key 存放在同一map的Value中
        Map<String, List<OrderItemInfoBO>> map = new LinkedHashMap<String, List<OrderItemInfoBO>>();

        for (int i = 0; i < list.size(); i++) {
            OrderItemInfoBO student = list.get(i);
            String key = student.getKey();

            List<OrderItemInfoBO> listStu = map.get(key);
            if (null == listStu) {
                listStu = new ArrayList<OrderItemInfoBO>();
            }
            listStu.add(student);

            map.put(key, listStu);
        }

        Set<Entry<String, List<OrderItemInfoBO>>> entrys = map.entrySet();
        Iterator<Entry<String, List<OrderItemInfoBO>>> iterator = entrys.iterator();

        System.out.println("*******************************");
        while (iterator.hasNext()) {
            Entry<String, List<OrderItemInfoBO>> entry = iterator.next();
            String key = entry.getKey();
            List<OrderItemInfoBO> listValue = entry.getValue();
            for (int i = 0; i < listValue.size(); i++) {
                System.out.println("key:" + key + "," + listValue.get(i).toString());
            }
            System.out.println("----");
        }
        System.out.println("*******************************");
        List<Group> groups = new ArrayList<Group>();
        iterator = entrys.iterator();
        while (iterator.hasNext()) {
            Entry<String, List<OrderItemInfoBO>> entry = iterator.next();
            List<OrderItemInfoBO> listValue = entry.getValue();
            OrderItemInfoBO ss = listValue.get(0);
            OrderInfoBo clazz = new OrderInfoBo(ss.getOrderName());
            Group g = new Group(clazz,listValue);
            groups.add(g);
        }
        System.out.println("*******************************");
        for (int i = 0; i < groups.size(); i++) {
            Group g = groups.get(i);
            System.out.println(g);
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }
}

class OrderInfoBo {

    private String name;

    public OrderInfoBo(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Clazz Name:" + name + "," + super.toString();
    }
}

class OrderItemInfoBO implements Comparable<OrderItemInfoBO> {
    private String name;
    private String orderName;
    private String publishUserID;
    private String type;

    @Override
    public int compareTo(OrderItemInfoBO o) {
        return getKey().compareTo(o.getKey());
    }

    public String getKey() {
        return publishUserID + "," + type;
    }

    public OrderItemInfoBO(String name, String clazzName, String key1, String key2) {
        super();
        this.name = name;
        this.orderName = clazzName;
        this.publishUserID = key1;
        this.type = key2;
    }

    public String getPublishUserID() {
        return publishUserID;
    }

    public void setPublishUserID(String key1) {
        this.publishUserID = key1;
    }

    public String getType() {
        return type;
    }

    public void setType(String key2) {
        this.type = key2;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String clazzName) {
        this.orderName = clazzName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "name:" + name + ",clazzName:" + orderName + ",key1:" + publishUserID + ",key2:" + type + "," + super.toString();
    }

}

class Group {
    private OrderInfoBo clazz;
    private List<OrderItemInfoBO> list = new ArrayList<OrderItemInfoBO>();

    public Group(OrderInfoBo clazz, List<OrderItemInfoBO> list) {
        super();
        this.clazz = clazz;
        this.list = list;
    }
    
    public String toString() {
        
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            buf.append(list.get(i)+"；");
        }
        return clazz + ";" + buf.toString();
    }
    

    public OrderInfoBo getClazz() {
        return clazz;
    }

    public void setClazz(OrderInfoBo clazz) {
        this.clazz = clazz;
    }

    public List<OrderItemInfoBO> getList() {
        return list;
    }

    public void setList(List<OrderItemInfoBO> list) {
        this.list = list;
    }
}


