package wxz.test.nio.channel;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class TestFile {

    public static void main(String[] args) throws IOException {
        
        File in = new File("read");
        System.out.println(in.getAbsolutePath());
        
        RandomAccessFile fileIn = new RandomAccessFile(in, "r");
        FileChannel fin = fileIn.getChannel();

        
        File out = new File("out");
        System.out.println("out:"+out.getAbsolutePath());
//        FileChannel fout = new FileOutputStream(out).getChannel();
        RandomAccessFile fileOut = new RandomAccessFile(out, "rw");
        FileChannel fout = fileOut.getChannel();
        fin.transferTo(0, fin.size(), fout);
        System.out.println("->end<-");
    }
}
