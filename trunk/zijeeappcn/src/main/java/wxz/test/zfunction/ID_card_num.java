package wxz.test.zfunction;

/**
 * 功能： 求18位身份证的最后一位
 */
public class ID_card_num {

    public static void main(String[] args) {
        System.out.println(76/14);
        System.out.println(getLastIDNum("37068619870401502"));

    }

    /**
     * 名称： 计算18位身份证的最后一位 功能 : 根据前17位身份证号，求最后一位 身份证最后一位的算法：
     * 1.将身份证号码的前17位的数字，分别乘以权数 ： 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
     * (比如：第一位乘以7，第二位乘以9，以此类推) 2.再将上面的所有乘积求和 3.将求得的和mod以11（%11），得到一个小于11的数（0到11）
     * 4.然后从1 0 X 9 8 7 6 5 4 3 2几位校验码中找出最后一位的数字
     * 如果得到的是0，则对应第一位：1,如果得到的是1，则对应第二位：0 如果得到的是2，则对应第三位：X,如果得到的是3，则对应第四位：9,以此类推
     * 5.最后得到的就是身份证的最后一位
     */
    public static Character getLastIDNum(String preIds) {
        Character lastId = null;
        // 当传入的字符串没有17位的时候，则无法计算，直接返回
        if (preIds == null && preIds.length() < 17) {
            return null;
        }
        int[] weightArray = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };// 权数数组
        String vCode = "10X98765432";// 校验码字符串
        int sumNum = 0;// 前17为乘以权然后求和得到的数

        // 循环乘以权，再求和
        for (int i = 0; i < 17; i++) {
            int index = Integer.parseInt(preIds.charAt(i) + "");
            sumNum = sumNum + index * weightArray[i];// 乘以权数，再求和
        }

        int modNum = sumNum % 11;// 求模
        lastId = vCode.charAt(modNum);// 从验证码中找出对应的数

        return lastId;
    }
}