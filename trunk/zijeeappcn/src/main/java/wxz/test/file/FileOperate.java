package wxz.test.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * FileOperate.java �ļ��ĸ��ֲ���
 */
public class FileOperate {
    public FileOperate() {
    }

    /**
     * �½�Ŀ¼
     */
    public void newFolder(String folderPath) {
        try {
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            if (!myFilePath.exists()) {
                myFilePath.mkdir();
            }
            System.out.println("�½�Ŀ¼���� �ɹ�ִ��");
        } catch (Exception e) {
            System.out.println("�½�Ŀ¼��������");
            e.printStackTrace();
        }
    }

    /**
     * �½��ļ�
     */
    public void newFile(String filePathAndName, String fileContent) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
            FileWriter resultFile = new FileWriter(myFilePath);
            PrintWriter myFile = new PrintWriter(resultFile);
            String strContent = fileContent;
            myFile.println(strContent);
            resultFile.close();
            System.out.println("�½��ļ����� �ɹ�ִ��");
        } catch (Exception e) {
            System.out.println("�½�Ŀ¼��������");
            e.printStackTrace();
        }
    }

    /**
     * ɾ���ļ�
     */
    public void delFile(String filePathAndName) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            File myDelFile = new File(filePath);
            myDelFile.delete();
            System.out.println("ɾ���ļ����� �ɹ�ִ��");
        } catch (Exception e) {
            System.out.println("ɾ���ļ���������");
            e.printStackTrace();
        }
    }

    /**
     * ɾ���ļ���
     */
    public void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // ɾ����������������
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            if (myFilePath.delete()) { // ɾ�����ļ���
                System.out.println("ɾ���ļ���" + folderPath + "���� �ɹ�ִ��");
            } else {
                System.out.println("ɾ���ļ���" + folderPath + "���� ִ��ʧ��");
            }
        } catch (Exception e) {
            System.out.println("ɾ���ļ��в�������");
            e.printStackTrace();
        }
    }

    /**
     * ɾ���ļ�������������ļ�
     * @param path String �ļ���·�� �� c:/fqf
     */
    public void delAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                // delAllFile(path+"/"+ tempList[i]);//��ɾ���ļ���������ļ�
                delFolder(path + File.separatorChar + tempList[i]);// ��ɾ�����ļ���
            }
        }
        System.out.println("ɾ���ļ����� �ɹ�ִ��");
    }

    /**
     * ���Ƶ����ļ�
     * @param oldPath String ԭ�ļ�·�� �磺c:/fqf.txt
     * @param newPath String ���ƺ�·�� �磺f:/fqf.txt
     */
    public void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) {
                // �ļ�����ʱ
                InputStream inStream = new FileInputStream(oldPath); // ����ԭ�ļ�
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; // �ֽ��� �ļ���С
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
            System.out.println("ɾ���ļ��в��� �ɹ�ִ��");
        } catch (Exception e) {
            System.out.println("���Ƶ����ļ���������");
            e.printStackTrace();
        }
    }

    /**
     * ���������ļ�������
     * @param oldPath String ԭ�ļ�·�� �磺c:/fqf
     * @param newPath String ���ƺ�·�� �磺f:/fqf/ff
     */
    public void copyFolder(String oldPath, String newPath) {
        try {
            (new File(newPath)).mkdirs(); // ����ļ��в����� �������ļ���
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }
                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {
                    // ��������ļ���
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
            System.out.println("�����ļ��в��� �ɹ�ִ��");
        } catch (Exception e) {
            System.out.println("���������ļ������ݲ�������");
            e.printStackTrace();
        }
    }

    /**
     * �ƶ��ļ���ָ��Ŀ¼
     * @param oldPath String �磺c:/fqf.txt
     * @param newPath String �磺d:/fqf.txt
     */
    public void moveFile(String oldPath, String newPath) {
        copyFile(oldPath, newPath);
        delFile(oldPath);
    }

    /**
     * �ƶ��ļ���ָ��Ŀ¼
     * @param oldPath String �磺c:/fqf.txt
     * @param newPath String �磺d:/fqf.txt
     */
    public void moveFolder(String oldPath, String newPath) {
        copyFolder(oldPath, newPath);
        delFolder(oldPath);
    }

    public static void main(String args[]) {
        String aa, bb;
        boolean exitnow = false;
        System.out.println("ʹ�ô˹����밴[1] ����һ���½�Ŀ¼");
        System.out.println("ʹ�ô˹����밴[2] ���ܶ����½��ļ�");
        System.out.println("ʹ�ô˹����밴[3] ��������ɾ���ļ�");
        System.out.println("ʹ�ô˹����밴[4] �����ģ�ɾ���ļ���");
        System.out.println("ʹ�ô˹����밴[5] �����壺ɾ���ļ�������������ļ�");
        System.out.println("ʹ�ô˹����밴[6] �������������ļ�");
        System.out.println("ʹ�ô˹����밴[7] �����ߣ������ļ��е���������");
        System.out.println("ʹ�ô˹����밴[8] ���ܰˣ��ƶ��ļ���ָ��Ŀ¼");
        System.out.println("ʹ�ô˹����밴[9] ���ܾţ��ƶ��ļ��е�ָ��Ŀ¼");
        System.out.println("ʹ�ô˹����밴[10] �˳�����");
        while (!exitnow) {
            FileOperate fo = new FileOperate();
            try {
                BufferedReader Bin = new BufferedReader(new InputStreamReader(System.in));
                String a = Bin.readLine();
                int b = Integer.parseInt(a);
                switch (b) {
                case 1:
                    System.out.println("��ѡ���˹���һ ������Ŀ¼��");
                    aa = Bin.readLine();
                    fo.newFolder(aa);
                    break;
                case 2:
                    System.out.println("��ѡ���˹��ܶ� �������ļ���");
                    aa = Bin.readLine();
                    System.out.println("��������" + aa + "�е�����");
                    bb = Bin.readLine();
                    fo.newFile(aa, bb);
                    break;
                case 3:
                    System.out.println("��ѡ���˹����� �������ļ���");
                    aa = Bin.readLine();
                    fo.delFile(aa);
                    break;
                case 4:
                    System.out.println("��ѡ���˹����� �������ļ���");
                    aa = Bin.readLine();
                    fo.delFolder(aa);
                    break;
                case 5:
                    System.out.println("��ѡ���˹����� �������ļ���");
                    aa = Bin.readLine();
                    fo.delAllFile(aa);
                    break;
                case 6:
                    System.out.println("��ѡ���˹����� �������ļ���");
                    aa = Bin.readLine();
                    System.out.println("������Ŀ���ļ���");
                    bb = Bin.readLine();
                    fo.copyFile(aa, bb);
                    break;
                case 7:
                    System.out.println("��ѡ���˹����� ������Դ�ļ���");
                    aa = Bin.readLine();
                    System.out.println("������Ŀ���ļ���");
                    bb = Bin.readLine();
                    fo.copyFolder(aa, bb);
                    break;
                case 8:
                    System.out.println("��ѡ���˹��ܰ� ������Դ�ļ���");
                    aa = Bin.readLine();
                    System.out.println("������Ŀ���ļ���");
                    bb = Bin.readLine();
                    fo.moveFile(aa, bb);
                    break;
                case 9:
                    System.out.println("��ѡ���˹��ܾ� ������Դ�ļ���");
                    aa = Bin.readLine();
                    System.out.println("������Ŀ���ļ���");
                    bb = Bin.readLine();
                    fo.moveFolder(aa, bb);
                    break;
                case 10:
                    exitnow = true;
                    System.out.println("������������˳�");
                    break;
                default:
                    System.out.println("�������.������1-10֮�����");
                }
                System.out.println("������ѡ����");
            } catch (Exception e) {
                System.out.println("��������ַ���������");
            }
        }
    }
}