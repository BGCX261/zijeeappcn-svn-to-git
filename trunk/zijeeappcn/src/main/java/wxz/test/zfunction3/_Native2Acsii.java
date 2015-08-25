package wxz.test.zfunction3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;

import sun.tools.native2ascii.Main;

public class _Native2Acsii {
    // http://bbs.pfan.cn/showpost.asp?id=14989
    public static void main(String[] args) throws IOException {
        // java sun.tools.native2ascii.Main -encoding gbk a.txt 1.txt
        sun.tools.native2ascii.Main main = new sun.tools.native2ascii.Main();
        
        run2();
    }

    private static void run1() throws IOException {
        java.lang.Process process = Runtime.getRuntime().exec("native2ascii");
        InputStream in = process.getInputStream();
        OutputStream out = process.getOutputStream();
        // out.write("EP�m�ä����ˤ��ʤ��ǥХ����Ηʳ�\n".getBytes());
        out.write("EP�m�ä����ˤ��ʤ��ǥХ����Ηʳ�\n".getBytes());
        out.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        System.out.println(br.readLine());
        br.close();
        in.close();
        out.close();
    }

    private static void run2() {
        Properties p = null;
        // �ο����з���
        // saveConvert(String theString,
        // boolean escapeSpace,
        // boolean escapeUnicode)
        
        String result = saveConvert("中国\n", false, true);
        System.out.println("result:"+result);
        result = saveConvert(result, true, false);
        System.out.println("result:"+result);
    }

    private static String saveConvert(String theString, boolean escapeSpace, boolean escapeUnicode) {
        int len = theString.length();
        int bufLen = len * 2;
        if (bufLen < 0) {
            bufLen = Integer.MAX_VALUE;
        }
        StringBuffer outBuffer = new StringBuffer(bufLen);

        for (int x = 0; x < len; x++) {
            char aChar = theString.charAt(x);
            // Handle common case first, selecting largest block that
            // avoids the specials below
            if ((aChar > 61) && (aChar < 127)) {
                if (aChar == '\\') {
                    outBuffer.append('\\');
                    outBuffer.append('\\');
                    continue;
                }
                outBuffer.append(aChar);
                continue;
            }
            switch (aChar) {
            case ' ':
                if (x == 0 || escapeSpace)
                    outBuffer.append('\\');
                outBuffer.append(' ');
                break;
            case '\t':
                outBuffer.append('\\');
                outBuffer.append('t');
                break;
            case '\n':
                outBuffer.append('\\');
                outBuffer.append('n');
                break;
            case '\r':
                outBuffer.append('\\');
                outBuffer.append('r');
                break;
            case '\f':
                outBuffer.append('\\');
                outBuffer.append('f');
                break;
            case '=': // Fall through
            case ':': // Fall through
            case '#': // Fall through
            case '!':
                outBuffer.append('\\');
                outBuffer.append(aChar);
                break;
            default:
                if (((aChar < 0x0020) || (aChar > 0x007e)) & escapeUnicode) {
                    outBuffer.append('\\');
                    outBuffer.append('u');
                    outBuffer.append(toHex((aChar >> 12) & 0xF));
                    outBuffer.append(toHex((aChar >> 8) & 0xF));
                    outBuffer.append(toHex((aChar >> 4) & 0xF));
                    outBuffer.append(toHex(aChar & 0xF));
                } else {
                    outBuffer.append(aChar);
                }
            }
        }
        return outBuffer.toString();
    }

    /**
     * Convert a nibble to a hex character
     * @param nibble the nibble to convert.
     */
    private static char toHex(int nibble) {
        return hexDigit[(nibble & 0xF)];
    }

    /** A table of hex digits */
    private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
            'F' };
}
