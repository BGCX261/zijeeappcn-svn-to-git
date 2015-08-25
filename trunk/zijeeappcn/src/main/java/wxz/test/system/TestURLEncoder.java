package wxz.test.system;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class TestURLEncoder {

    /**
     * @param args
     * @throws UnsupportedEncodingException 
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        String whereCause = " »À‘ı√¥_°æ°ø[]";
        String s = URLEncoder.encode(whereCause,"utf-8");
        System.out.println(s);
        
        String s1 = URLDecoder.decode("%E3%80%90%E3%80%91%5B%5D","utf-8");
        System.out.println(s1);
        
    }

}
