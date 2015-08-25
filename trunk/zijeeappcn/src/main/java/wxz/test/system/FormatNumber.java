package wxz.test.system;

import java.text.DecimalFormat;

public class FormatNumber {
    public static void main(String[] args) {
        DecimalFormat a = new DecimalFormat("000000");
        System.out.println(a.format(1));
        run1();
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
