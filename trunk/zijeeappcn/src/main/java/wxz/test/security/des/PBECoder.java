package wxz.test.security.des;

import java.security.Key;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import sun.misc.BASE64Encoder;

/**
 * PBE��ȫ�������
 * 
 * @author ����
 * @version 1.0
 * @since 1.0
 */
public abstract class PBECoder {
	/**
	 * ֧����������һ���㷨
	 * 
	 * <pre>
	 * PBEWithMD5AndDES 
	 * PBEWithMD5AndTripleDES 
	 * PBEWithSHA1AndDESede
	 * PBEWithSHA1AndRC2_40
	 * </pre>
	 */
	public static final String ALGORITHM_PBEWITHMD5andDES = "PBEWITHMD5andDES";
	public static final String ALGORITHM_PBEWithMD5AndTripleDES = "PBEWithMD5AndTripleDES";
	public static final String ALGORITHM_PBEWithSHA1AndDESede = "PBEWithSHA1AndDESede";
	public static final String ALGORITHM_PBEWithSHA1AndRC2_40 = "PBEWithSHA1AndRC2_40";
	/**
	 * �γ�ʼ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public static byte[] initSalt() throws Exception {
		byte[] salt = new byte[8];
		Random random = new Random();
		random.nextBytes(salt);
		return salt;
	}

	/**
	 * ת����Կ<br>
	 * 
	 * @param password
	 * @return
	 * @throws Exception
	 */
	private static Key toKey(String password) throws Exception {
		PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM_PBEWITHMD5andDES);
		SecretKey secretKey = keyFactory.generateSecret(keySpec);

		return secretKey;
	}

	/**
	 * ����
	 * 
	 * @param data
	 *            ����
	 * @param password
	 *            ����
	 * @param salt
	 *            ��
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, String password, byte[] salt)
			throws Exception {

		Key key = toKey(password);

		PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);
		Cipher cipher = Cipher.getInstance(ALGORITHM_PBEWITHMD5andDES);
		cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

		return cipher.doFinal(data);

	}

	/**
	 * ����
	 * 
	 * @param data
	 *            ����
	 * @param password
	 *            ����
	 * @param salt
	 *            ��
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, String password, byte[] salt)
			throws Exception {

		Key key = toKey(password);

		PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);
		Cipher cipher = Cipher.getInstance(ALGORITHM_PBEWITHMD5andDES);
		cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

		return cipher.doFinal(data);

	}

	/**
	 * BASE64����
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	public static void main1(String[] args) throws Exception {
		String inputStr = "abc";
		System.out.println("ԭ��: " + inputStr);
		byte[] input = inputStr.getBytes();
		String pwd = "efggfdsa";
		System.out.println("����: " + pwd);
		byte[] salt = PBECoder.initSalt();
		byte[] data = PBECoder.encrypt(input, pwd, salt);
		System.out.println("���ܺ�: " + PBECoder.encryptBASE64(data));
		byte[] output = PBECoder.decrypt(data, pwd, salt);
		String outputStr = new String(output);
		System.out.println("���ܺ�: " + outputStr);
	}
	
	public static void main(String[] args) throws Exception {
		String inputStr = "¼1����Լ��ʱ3����";
		byte[] input = inputStr.getBytes();
		String pwd = "efggfdsa";
		byte[] salt = PBECoder.initSalt();
		byte[] data = PBECoder.encrypt(input, pwd, salt);
		
		System.out.println(System.currentTimeMillis());
		for (int i = 0; i < 1000; i++) {
			PBECoder.encryptBASE64(data);
			PBECoder.decrypt(data, pwd, salt);
		}
		System.out.println(System.currentTimeMillis());
//		1297834545125
//		1297834545421
//		1297834562093
//		1297834562406
//		String outputStr = new String(output);
//		System.out.println("���ܺ�: " + outputStr);
	}
}