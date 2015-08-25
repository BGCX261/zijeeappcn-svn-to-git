package wxz.test.file;

import java.io.File;

public class TestFile {
	
	public static void main(String[] args) {
		File file = new File("C:\\axis");
		String[] strs = file.list();
		for (int i = 0; i < strs.length; i++) {
			String string = strs[i];
			if(string.endsWith(".jar")){
				System.out.print("%AXIS_LIB%\\"+string+";");	
			}
		}
	}
}
