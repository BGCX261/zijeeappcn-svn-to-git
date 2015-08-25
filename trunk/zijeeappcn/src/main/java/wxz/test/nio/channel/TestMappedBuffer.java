package wxz.test.nio.channel;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class TestMappedBuffer {

    public static void main(String[] args) throws IOException {

        File in = new File("read");
        System.out.println(in.getAbsolutePath());

        RandomAccessFile fileIn = new RandomAccessFile(in, "r");
        FileChannel fin = fileIn.getChannel();

        // �ָ�ɶ���ļ���ÿ���ļ�2�ֽ�
        // �ļ�������
        long index = 0;
        // ����
        final long step = 20;
        
        long start = 0;
        long size = 0;
        // �ļ���
        long fileSize = fin.size();
        System.out.println(fileSize);
        do {
            // ��2���ֽ�(����)
            start = start + size;
            size = step;
            index++;
            System.out.println(index + "," + start + "," + size);
            if (start + size > fileSize) {
                size = fileSize - start;
            }
            fin.position(start);
            MappedByteBuffer mapBuffer = fin.map(MapMode.READ_ONLY, start, size);
            File out = new File("out_" + index);
            RandomAccessFile fileOut = new RandomAccessFile(out, "rw");
            FileChannel fout = fileOut.getChannel();
            fout.write(mapBuffer);
            mapBuffer.force();
            fout.close();

            if(index > 100){
                System.out.println("error");
                System.exit(0);
            }
        } while (start + size < fileSize);

        System.out.println("->end<-");
    }
}
