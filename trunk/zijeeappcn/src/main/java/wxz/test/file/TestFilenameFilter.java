package wxz.test.file;

import java.io.File;
import java.io.FilenameFilter;

public class TestFilenameFilter {

    
    public static void main(String[] args) {
        File file = new File("C:\\Documents and Settings\\user\\×ÀÃæ\\1");
        String[] images = file.list(new MyFilenameFilter());   
        System.out.println("size="+images.length);   
    }
    
}

class MyFilenameFilter implements FilenameFilter{

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(".txt");
    }
    
}
