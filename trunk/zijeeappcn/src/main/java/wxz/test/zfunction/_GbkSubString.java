package wxz.test.zfunction;

public class _GbkSubString {
	public static void main(String[] args) throws Exception {
		String str = "Χa �����ס����abc Χ������-�� def";
		String str2 = "ΧABC����";
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
