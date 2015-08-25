package wxz.test.file;

import java.io.File;

public class MAIN {

    /**
     * @param args
     */
    public static void main(String[] args) {
        File file = new File("D:\\aaawork\\workspace");
        a(file);
    }

    public static void a(File file){
        if(file.isDirectory()){
            File[] filelist = file.listFiles();
            for (int index = 0; index < filelist.length; index++) {
                a(filelist[index]);
            }
        }else {
            String name = file.getName();
//            if(name.startsWith(".")){
//            if(name.endsWith(".class")){
                if(name.endsWith("harvest.sig")){
                System.out.println(file.getAbsolutePath());
                file.delete();
            }
        }
    }
    
    
}
