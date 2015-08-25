package wxz.test.url.connection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestHttpUPOC {
    public static void main(String[] args) throws Exception {

        URL url = new URL("http://www.baidu.com/");
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        huc.setDoOutput(true);
        huc.setConnectTimeout(6000);
        huc.setRequestProperty("Content-Type", "text/xml; charset=GBK");
        huc.setRequestMethod("GET");
        huc.connect();
        StringBuffer response = new StringBuffer();
        int code = huc.getResponseCode();
        System.out.println("Response code is:" + code);
        if (code >= 200 && code < 300) {
            InputStream is = huc.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            File file = new File("D:\\aa.txt");
            FileOutputStream out = new FileOutputStream(file);
            String line = null;
            for (; (line = br.readLine()) != null;) {
                response.append(line);
                out.write(line.getBytes());
            }
            huc.disconnect();
            System.out.println(new String(response));

            out.flush();
            out.close();
            br.close();
            is.close();
        }
    }

}
