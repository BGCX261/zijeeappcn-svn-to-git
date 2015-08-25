package wxz.test.system;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;

public class GetResource {

    public static void main(String[] args) throws IOException {
        run3();
    }

    public void run1() throws IOException {
        // 从当前的工作目录中读取:
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("wkdir.txt")));
        String str;
        while ((str = in.readLine()) != null) {
            System.out.println(str);
        }
        in.close();
    }

    public void run2() throws IOException {
        // 2,从classpath中读取(读取找到的第一个符合名称的文件):
        InputStream stream = GetResource.class.getClassLoader().getSystemResourceAsStream("fileinjar.txt");
        // InputStream stream =
        // ClassLoader.getSystemResourceAsStream("fileinjar.txt");
        // ClassLoader.getSystemResourceAsStream("file.txt");的方式去找不到文件.改成
        // Xclass.class.getClassLoader().getResourceAsStream("file.txt");能从ant指定的classpath中找到文件.原因是ClassLoader和Xclass.class.getClassLoader()是不同的,查找的路径不一样.
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String str;
        while ((str = in.readLine()) != null) {
            System.out.println(str);
        }
        in.close();
    }

    public static void run3() throws IOException {
        // 3,从classpath中读取(读取找到的所有符合名称的文件,如Spring中带有classpath*:前缀的情况就会从classpath中遍历):

        Enumeration resourceUrls = Thread.currentThread().getContextClassLoader().getResources("");

        while (resourceUrls.hasMoreElements()) {
            URL url = (URL) resourceUrls.nextElement();
            System.out.println(url);

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str;
            while ((str = in.readLine()) != null) {
                System.out.println(str);
            }
            in.close();

        }
    }

    public void run4() throws IOException {
        // 4,从URL中读取:
        URL url = new URL("http://blog.csdn.net/kkdelta");
        System.out.println(url);

        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String str;
        while ((str = in.readLine()) != null) {
            System.out.println(str);
        }
        in.close();

    }

    public void run5() throws IOException {
        // 5,web项目从web-inf文件夹读取(通过得到ServletContext读取,可以在servlet或者能够得到request的类中使用):

        URL url = (URL) getServletContext().getResource("/WEB-INF/webinffile.txt");
        // URL url =
        // (URL)req.getSession().getServletContext().getResource("/WEB-INF/webinffile.txt");
        System.out.println(url);

        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String str;
        while ((str = in.readLine()) != null) {
            System.out.println(str);
        }
        in.close();
    }

    private ClassLoader getServletContext() {
        return null;
    }
}
