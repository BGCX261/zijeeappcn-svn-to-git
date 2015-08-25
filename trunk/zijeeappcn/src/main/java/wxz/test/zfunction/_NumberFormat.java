package wxz.test.zfunction;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class _NumberFormat {
	public static void main(String[] args) throws ParseException {
		// Format
		Locale locale = Locale.CANADA;
		String string = NumberFormat.getNumberInstance(locale).format(123.45);
		System.out.println(string);
		// Parse
		Number number = NumberFormat.getNumberInstance(locale).parse("123.45");
		System.out.println(number.getClass());
		if (number instanceof Long) {
			// Long value
		} else {
			// Double value
		}

	}

	private static void run1() {
		DecimalFormat df = new DecimalFormat();
		double data = 1234.56789;
		System.out.println("��ʽ��֮ǰ������: " + data);
		String style = "0.0";// ����Ҫ��ʾ�����ֵĸ�ʽ
		df.applyPattern(style);// ����ʽӦ���ڸ�ʽ����
		System.out.println("����style: " + style + "��ʽ��֮��: " + df.format(data));
		style = "00000.000 kg";// �ڸ�ʽ��������絥λ���ַ�
		df.applyPattern(style);
		System.out.println("����style: " + style + "��ʽ��֮��: " + df.format(data));
		// ģʽ�е�"#"��ʾ�����λ�����ַ�������ʾ�ַ�����������ڣ�����ʾ��
		style = "##000.000 kg";
		df.applyPattern(style);
		System.out.println("����style: " + style + "��ʽ��֮��: " + df.format(data));
		// ģʽ�е�"-"��ʾ���Ϊ������Ҫ������ǰ��
		style = "-000.000";
		df.applyPattern(style);
		System.out.println("����style: " + style + "��ʽ��֮��: " + df.format(data));
		// ģʽ�е�","����������Ӷ��ţ����������
		style = "-0,000.0#";
		df.applyPattern(style);
		System.out.println("����style: " + style + "��ʽ��֮��: " + df.format(data));
		// ģʽ�е�"E"��ʾ���Ϊָ����"E"֮ǰ���ַ����ǵ����ĸ�ʽ��
		// "E"֮������ַ�����ָ���ĸ�ʽ
		style = "0.00E000";
		df.applyPattern(style);
		System.out.println("����style: " + style + "��ʽ��֮��: " + df.format(data));
		// ģʽ�е�"%"��ʾ����100����ʾΪ�ٷ�����Ҫ�������
		style = "0.00%";
		df.applyPattern(style);
		System.out.println("����style: " + style + "��ʽ��֮��: " + df.format(data));
		// ģʽ�е�"\u2030"��ʾ����1000����ʾΪǧ������Ҫ�������
		style = "0.00\u2030";
		// �ڹ��캯�����������ָ�ʽ
		DecimalFormat df1 = new DecimalFormat(style);
		// df.applyPattern(style);
		System.out.println("����style: " + style + "��ʽ��֮��: " + df1.format(data));
	}

}
