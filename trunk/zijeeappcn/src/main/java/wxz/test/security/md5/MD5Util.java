package wxz.test.security.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		String str = "";
		MessageDigest messagedigest = MessageDigest.getInstance("MD5");
		messagedigest.update(str.getBytes());
		byte[] b = messagedigest.digest();

	}
}
