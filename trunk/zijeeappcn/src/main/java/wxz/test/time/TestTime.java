package wxz.test.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestTime {

    /**
     * 获取时间，格式yyyy-MM-dd HH:mm:ss
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
        System.out.println("月：" + month);
        time = time - month * Month;
        long day = time / Day;
        System.out.println("日：" + day);
        time = time - day * Day;
        long hour = time / Hour;
        System.out.println("时：" + hour);
        time = time - hour * Hour;
        long mi = time / Mi;
        System.out.println("分：" + mi);
        time = time - mi * Mi;
        long second = time / Second;
        System.out.println("秒：" + second);

        String result = month + "月" + day + "日" + hour + "时" + mi + "分" + second + "秒";
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
        System.out.println("月：" + month);
        time = time -month * Month;
        long day = time / Day;
        System.out.println("日：" + day);
        time = time -day * Day;
        long hour = time / Hour;
        System.out.println("时：" + hour);
        time = time - hour * Hour;
        long mi = time / Mi;
        System.out.println("分：" + mi);
        time = time - mi * Mi;
        long second = time/Second;
        System.out.println("秒："+second);

        String result = month + "月" + day + "日" + hour + "时" + mi + "分" + second + "秒";
        System.out.println(result);
    }
    public static void main(String[] args) throws Exception {
        // long l = 3967345080l;
        // System.out.println(System.currentTimeMillis());

        run1();
    }

}
