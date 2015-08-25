package wxz.test.zfunction;

public class _Char2HarfChar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = toDBC("ＡＳＤ：");
		System.out.println(s);
	}

	//转半角的函数(DBC case)
	//全角空格为12288?半角空格为32
	//其他字符半角(33-126)与全?(65281-65374)的对应关系是均相差65248
	//
	public static String toDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375) {
				c[i] = (char) (c[i] - 65248);
			}
		}
		return new String(c);
	}
}
