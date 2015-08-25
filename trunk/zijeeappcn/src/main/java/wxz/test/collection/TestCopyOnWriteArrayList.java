package wxz.test.collection;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

//�� �� �� �� �� �� �� �� �� �� �� ʱ �� �� Զ �� �� �� ��
//currentModificationException
//copy-on-write ���Ϻ�һ�����ͼ��Ͻ��б��������ӣ�
//���������϶Զ� copy-on-write ģʽ���˱ȽϺõ�֧��
//��ʱ��Ҫ��ͬ����
//д���� add() �����á������µ� CopyOnWriteArrayList �� CopyOnWriteArraySet �࣬
//���пɱ�ģ�mutable������������ȡ�ú�̨����ĸ������Ը������и��ģ�Ȼ���滻����������������֤
//currentModificationException���������ϻ���ԭ���ļ�����ɣ������Ժ�Ĳ�����ʹ�ø��º�ļ��ϡ�

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
            System.err.println("Shouldn't get here and print ��NEW�� ITEM");
        }
    }

    @SuppressWarnings("unchecked")
    private static void printAll(Iterator itor) {
        while (itor.hasNext()) {
            System.out.println(itor.next());
        }
    }
}
