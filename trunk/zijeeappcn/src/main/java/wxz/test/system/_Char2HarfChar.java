package wxz.test.system;

public class _Char2HarfChar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = toDBC("���ӣģ�");
		System.out.println(s);
	}

	//ת��ǵĺ���(DBC case)
	//ȫ�ǿո�Ϊ12288?��ǿո�Ϊ32
	//�����ַ����(33-126)��ȫ?(65281-65374)�Ķ�Ӧ��ϵ�Ǿ����65248
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
