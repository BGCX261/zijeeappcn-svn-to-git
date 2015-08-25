package wxz.test.zfunction;

import java.text.SimpleDateFormat;
import java.util.Date;

public class _DateFormat {

	public static void main(String args[]) {

		Date nowTime = new Date();
		System.out.println(nowTime);
		SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		System.out.println(time.format(nowTime));

//		DecimalFormat df1 = new DecimalFormat("000000");
//		String st = df1.format(1l);
//		System.out.println(st);
	}
}
