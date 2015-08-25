package wxz.test.time;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Times2 {

    
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println(ca.getTime());
        UUID.randomUUID().toString();
    }
}
