package wxz.test.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopyFile {
    
    public static void main(String[] args) throws IOException {
        File infile = new File("fileConfig.properties");
        File outfile = new File("out_file");
        
        FileInputStream ins = new FileInputStream(infile);
        FileChannel in = ins.getChannel();
        
        FileOutputStream outs = new FileOutputStream(outfile);
        FileChannel out = outs.getChannel();
        
        in.transferTo(0, in.size(), out);
        
        System.out.println("->end<-");
    }

//    public static void main(String[] args) throws Exception {
//        String outfile = "out_file";
//
//        File infile = new File("fileConfig.properties");
//        System.out.println(infile.getAbsolutePath());
//
//        // ��ȡԴ�ļ���Ŀ���ļ������������
//        FileInputStream fin = new FileInputStream(infile);
//        FileOutputStream fout = new FileOutputStream(outfile);
//
//        // ��ȡ�������ͨ��
//        FileChannel fcin = fin.getChannel();
//        FileChannel fcout = fout.getChannel();
//
//        // ����������
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//
//        while (true) {
//            // clear�������軺������ʹ�����Խ��ܶ��������
////            buffer.clear();
//
//            // ������ͨ���н����ݶ���������
//            int r = fcin.read(buffer);
//
//            // read�������ض�ȡ���ֽ���������Ϊ�㣬�����ͨ���ѵ�������ĩβ���򷵻�-1
//            if (r == -1) {
//                break;
//            }
//
//            // flip�����û��������Խ��¶��������д����һ��ͨ��
//            buffer.flip();
//
//            // �����ͨ���н�����д�뻺����
//            fcout.write(buffer);
//            fcout.force(true);
//            
//        }
//        System.out.println("->end<-");
//    }

}