package wxz.test.file;

import java.io.File;

public class RenameFile {

	public static void main(String[] args) {
		String oldpath="D:\\abc";
		//File file = new File(oldpath);
		String newpath = "D:\\fff";
		File oldf=new File(oldpath); 
		File newf=new File(newpath);   
		if(oldf.exists()){    
			System.out.println("in fi");
			if(oldf.renameTo(newf)){           
				System.out.println("修改成功");     
			}else{       
				System.out.println("修改失败");    
			}
		}
	}
}
