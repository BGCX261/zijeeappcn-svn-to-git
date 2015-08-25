package wxz.test.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class SplitFile {

    private static long M = 1 * 1024 * 1024;

    private static String file = "E:\\TEMP\\aa.txt";

    private static String outFilePath = "E:\\TEMP\\";
    private static String outFileName = "bb";
    private static String reg = ".txt";

    public static void main(String[] args) throws IOException {

        File infile = new File(file);
        FileInputStream ins = new FileInputStream(infile);
        FileChannel in = ins.getChannel();
        long inFileSize = infile.length();
        for (long position = 0, index = 0; position < inFileSize;) {
            File outfile = new File(outFilePath + outFileName + "_" + index + reg);
            FileOutputStream outs = new FileOutputStream(outfile);
            FileChannel out = outs.getChannel();

            index++;
            in.transferTo(position, M, out);
            position += M;
        }

        System.out.println("->end<-");
    }

}