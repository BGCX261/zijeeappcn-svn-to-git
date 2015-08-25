package wxz.test.zfunction3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SearchClass {
    public static void main(String[] args) throws Exception {

        String packName = "wxz.test.zfunction3";
        String classpath = System.getProperty("java.class.path");
        String[] all = classpath.split(";");

        for (int i = 0; i < all.length; i++) {
            System.out.println("classpath-->" + all[i]);
            if (all[i].endsWith(".jar ")) {
                findJar(all[i], packName);
            } else {
                findFile(all[i], packName);
            }

        }
    }

    /**
     * 
     * @param path
     * @param packName
     * @throws Exception
     */
    public static void findFile(String path, String packName) throws Exception {
        File file = new File(path + "\\" + packName.replace('.', '\\'));

        // System.out.println(file.getAbsolutePath());
        if (file.exists() && file.isDirectory()) {

            File[] classes = file.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    if (name.endsWith(".class")) {
                        System.out.println("-->" + name);
                        return true;
                    }
                    return false;
                }
            });
            System.out.println(classes.length);
        }
    }

    /** 
          *   
          */
    public static void findJar(String jar, String packName) throws Exception {
        ZipInputStream in = new ZipInputStream(new FileInputStream(new File(jar)));
        ZipEntry ze = null;

        while ((ze = in.getNextEntry()) != null) {
            if (ze.getName().startsWith(packName.replace(". ", "/ ")) && ze.getName().endsWith(".class ")
                    && (ze.getName().lastIndexOf("/ ") == (packName.replace(". ", "/ ") + "/ ").lastIndexOf("/ "))) {
                System.out.println(ze.getName());
            }
        }
    }
}
