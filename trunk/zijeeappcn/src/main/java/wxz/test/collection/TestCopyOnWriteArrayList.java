package wxz.test.collection;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

//在 遍 历 自 身 可 更 改 的 集 合 时 ， 永 远 不 会 抛 出
//currentModificationException
//copy-on-write 集合和一般类型集合进行遍历的例子：
//这两个集合对对 copy-on-write 模式作了比较好的支持
//性时需要的同步。
//写（即 add() ）调用。对于新的 CopyOnWriteArrayList 和 CopyOnWriteArraySet 类，
//所有可变的（mutable）操作都首先取得后台数组的副本，对副本进行更改，然后替换副本。这种做法保证
//currentModificationException。遍历集合会用原来的集合完成，而在以后的操作中使用更新后的集合。

public class TestCopyOnWriteArrayList {
    @SuppressWarnings("unchecked")
    public static void main(String args[]) {
        String[] ss = { "aa", "bb", "cc" };
        List list1 = new CopyOnWriteArrayList(Arrays.asList(ss));
        List list2 = new ArrayList(Arrays.asList(ss));
        Iterator itor1 = list1.iterator();
        Iterator itor2 = list2.iterator();
        list1.add("New"); // here is importment
        list2.add("New"); // here is importment
        try {
            printAll(itor1);
        } catch (ConcurrentModificationException e) {
            System.err.println("Shouldn't get here");
        }
        try {
            printAll(itor2);
        } catch (ConcurrentModificationException e) {
            System.err.println("Will get  here.ConcurrentModificationException error");
        }

        Iterator itor3 = list1.iterator();
        try {
            printAll(itor3);
        } catch (ConcurrentModificationException e) {
            System.err.println("Shouldn't get here and print 【NEW】 ITEM");
        }
    }

    @SuppressWarnings("unchecked")
    private static void printAll(Iterator itor) {
        while (itor.hasNext()) {
            System.out.println(itor.next());
        }
    }
}
