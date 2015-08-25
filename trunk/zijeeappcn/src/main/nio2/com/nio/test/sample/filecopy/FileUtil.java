package com.nio.test.sample.filecopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileUtil {
    public static final int MAX_BUFFER = 4096;  
      
        /** 
         * ɾ���ļ����� 
         *  
         * @param file 
         * @return 
         */  
        public void removeFile(File file) {  
            if (file.isDirectory()) {  
                File[] files = file.listFiles();  
                for (File fil : files) {  
                    removeFile(fil);// �ݹ����   
                }  
                file.delete();// �ļ�ɾ����ɾ��Ŀ¼   
            } else {  
                file.delete();// ɾ���ļ�   
            }  
        }  
      
        /** 
         * ɾ���ļ� 
         *  
         * @param filstr 
         */  
        public void removeFile(String filstr) {  
            File file = new File(filstr);  
            removeFile(file);  
        }  
      
        /** 
         * ���ļ� 
         *  
         * @param file 
         * @param output 
         */  
        public void readFile(File file, OutputStream output) {  
            FileInputStream inputStream = null;  
            FileChannel fileChannel = null;  
            try {  
                inputStream = new FileInputStream(file);  
                fileChannel = inputStream.getChannel();// �õ���������ͨ��   
                ByteBuffer buffer = ByteBuffer.allocate(1024);// �����ֽڻ�����   
                while (true) {  
                    buffer.clear();// ���������   
                    int size = fileChannel.read(buffer);  
                    if (size == -1)  
                        break;  
                    buffer.flip();// Ϊд����׼��   
                    output.write(buffer.array());  
                }  
                output.flush();  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            } finally {  
                close(fileChannel);  
                close(output);  
            }  
        }  
      
        /** 
         * �ر��� 
         *  
         * @param outputStream 
         */  
        public void close(OutputStream outputStream) {  
            if (outputStream != null) {  
                try {  
                    outputStream.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
      
        public void close(FileChannel channel) {  
            if (channel != null) {  
                try {  
                    channel.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
      
        /** 
         * �����ļ� 
         *  
         * @param sourceFile 
         * @param targetFile 
         * @throws IOException 
         */  
        public void copyFile(File sourceFile, File targetFile) throws IOException {  
            File tar = new File(targetFile, sourceFile.getName());  
            if (sourceFile.isDirectory()) {  
                tar.mkdir();  
                File[] files = sourceFile.listFiles();  
                for (File file : files) {// �ݹ���ļ�   
                    copyFile(file, tar);  
                }  
            } else {// ͨ��NIO���Կ����ļ�   
                FileInputStream inputStream = new FileInputStream(sourceFile);  
                FileOutputStream outputStream = new FileOutputStream(tar);  
                FileChannel channel = inputStream.getChannel();  
                FileChannel outchannel = outputStream.getChannel();  
                ByteBuffer buffer = ByteBuffer.allocate(MAX_BUFFER);  
                while (true) {  
                    buffer.clear();  
                    if ((-1) == channel.read(buffer))  
                        break;  
                    buffer.flip();  
                    outchannel.write(buffer);  
                }  
                close(outchannel);  
                close(outputStream);  
            }  
        }  
      
        public void copyFile(String source, String target) {  
            File filesour = new File(source);  
            File filetar = new File(target);  
            try {  
                copyFile(filesour, filetar);  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
      
        public static void main(String[] args) {  
            FileUtil fileUtil = new FileUtil();  
            // fileUtil.removeFile("F://transportFiles");   
            fileUtil.copyFile("F://��������//API�ĵ�", "F://aaaaaaa");  
        }  
}
