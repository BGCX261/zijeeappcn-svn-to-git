package wxz.test.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestTime {

    /**
     * ��ȡʱ�䣬��ʽyyyy-MM-dd HH:mm:ss
     * @param ocurrTime
     * @return
     */
    private String getTimeByOccurTime(long ocurrTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ocurrTime);
        Date date = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strTime = df.format(date);
        return strTime;
    }

    static long Second = 1 * 1000;
    static long Mi = Second * 60;
    static long Hour = Mi * 60;
    static long Day = Hour * 24;
    static long Month = Day * 24;

    private static void run1() throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = df.parse("2012-03-01 12:31:31");
        Date date2 = df.parse("2012-03-03 12:31:32");
        long la = date1.getTime();
        long lb = date2.getTime();
        long time = lb - la;
        System.out.println(time);
        
        long month = time / Month;
        System.out.println("�£�" + month);
        time = time - month * Month;
        long day = time / Day;
        System.out.println("�գ�" + day);
        time = time - day * Day;
        long hour = time / Hour;
        System.out.println("ʱ��" + hour);
        time = time - hour * Hour;
        long mi = time / Mi;
        System.out.println("�֣�" + mi);
        time = time - mi * Mi;
        long second = time / Second;
        System.out.println("�룺" + second);

        String result = month + "��" + day + "��" + hour + "ʱ" + mi + "��" + second + "��";
        System.out.println(result);
    }

    
    private static void run2() throws Exception {
        // OK
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = df.parse("2012-03-01 12:31:31");
        Date date2 = df.parse("2012-03-03 12:31:32");
        long la = date1.getTime();
        long lb = date2.getTime();
        long time = lb - la;
        System.out.println(time);
        long month = time / Month;
        System.out.println("�£�" + month);
        time = time -month * Month;
        long day = time / Day;
        System.out.println("�գ�" + day);
        time = time -day * Day;
        long hour = time / Hour;
        System.out.println("ʱ��" + hour);
        time = time - hour * Hour;
        long mi = time / Mi;
        System.out.println("�֣�" + mi);
        time = time - mi * Mi;
        long second = time/Second;
        System.out.println("�룺"+second);

        String result = month + "��" + day + "��" + hour + "ʱ" + mi + "��" + second + "��";
        System.out.println(result);
    }
    public static void main(String[] args) throws Exception {
        // long l = 3967345080l;
        // System.out.println(System.currentTimeMillis());

        run1();
    }

}
