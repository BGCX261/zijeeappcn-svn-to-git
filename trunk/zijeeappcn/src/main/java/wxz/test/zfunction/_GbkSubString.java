package wxz.test.zfunction;

public class _GbkSubString {
	public static void main(String[] args) throws Exception {
		String str = "围a 带￥Ｗ、割对abc 围带￥Ｗ-面 def";
		String str2 = "围ABC对字";
		int num = trimGBK(str2.getBytes("GBK"), 6);
		System.out.println(str2.substring(0, num));
	}

	public static int trimGBK(byte[] buf, int n) {
		int num = 0;
		boolean bChineseFirstHalf = false;
		for (int i = 0; i < n; i++) {
			if (buf[i] < 0 && !bChineseFirstHalf) {
				bChineseFirstHalf = true;
			} else {
				num++;
				bChineseFirstHalf = false;
			}
		}
		return num;
	}
}
