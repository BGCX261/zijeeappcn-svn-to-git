package wxz.test.zfunction3;

public class Test {

    /**
     * 读取指定JAR包中，所有文件
     */
    public static void main(String[] args) {
        sun.tools.jar.Main jartool = new sun.tools.jar.Main(System.out, System.err, "jar ");
        jartool.run(new String[] { "-tf", "C:\\Program Files (x86)\\Java\\jdk1.6.0_16\\lib\\dt.jar" });
    }
}
