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
        // �ӵ�ǰ�Ĺ���Ŀ¼�ж�ȡ:
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("wkdir.txt")));
        String str;
        while ((str = in.readLine()) != null) {
            System.out.println(str);
        }
        in.close();
    }

    public void run2() throws IOException {
        // 2,��classpath�ж�ȡ(��ȡ�ҵ��ĵ�һ���������Ƶ��ļ�):
        InputStream stream = GetResource.class.getClassLoader().getSystemResourceAsStream("fileinjar.txt");
        // InputStream stream =
        // ClassLoader.getSystemResourceAsStream("fileinjar.txt");
        // ClassLoader.getSystemResourceAsStream("file.txt");�ķ�ʽȥ�Ҳ����ļ�.�ĳ�
        // Xclass.class.getClassLoader().getResourceAsStream("file.txt");�ܴ�antָ����classpath���ҵ��ļ�.ԭ����ClassLoader��Xclass.class.getClassLoader()�ǲ�ͬ��,���ҵ�·����һ��.
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String str;
        while ((str = in.readLine()) != null) {
            System.out.println(str);
        }
        in.close();
    }

    public static void run3() throws IOException {
        // 3,��classpath�ж�ȡ(��ȡ�ҵ������з������Ƶ��ļ�,��Spring�д���classpath*:ǰ׺������ͻ��classpath�б���):

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
        // 4,��URL�ж�ȡ:
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
        // 5,web��Ŀ��web-inf�ļ��ж�ȡ(ͨ���õ�ServletContext��ȡ,������servlet�����ܹ��õ�request������ʹ��):

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
