package wxz.test.zfunction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;




public class _Properties {

	public static void main(String[] args) throws Exception {
		ResourceBundle bundle = ResourceBundle.getBundle("basename");
		for (Enumeration props = bundle.getKeys(); props.hasMoreElements();) {
			String key = (String) props.nextElement();
			System.out.println(key + ":" + bundle.getObject(key));
		}
	}

	private static void run2() throws FileNotFoundException, IOException {
		// E:\ws_demo\Demo\jdbc\jdbc.properties
		// File file = new File("jdbc.properties");
		// System.out.println(file.getAbsolutePath());

		URL fileUrl = _Properties.class.getClassLoader().getResource(
				"jdbc.properties");
		System.out.println(fileUrl);

		// InputStream inputStream =
		// RestOrderIDSeq.class.getClassLoader().getResourceAsStream(
		// "jdbc.properties");

		InputStream inputStream = new FileInputStream(fileUrl.getFile());
		Properties p = new Properties();
		p.load(inputStream);

		// Set set = p.entrySet();
		// Iterator i = set.iterator();
		// while (i.hasNext()) {
		// Entry e = (Entry) i.next();
		// System.out.println(e.getKey()+":"+e.getValue());
		// }

		String url = p.getProperty("jdbc.url");
		String username = p.getProperty("jdbc.username");
		String password = p.getProperty("jdbc.password");
		String driverName = p.getProperty("jdbc.driverClassName");

		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
		System.out.println(driverName);
	}

	private static void run1() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.4.208:1521:orcl";
		String username = "danti01";
		String password = "danti01";

		Connection conn = DriverManager.getConnection(url, username, password);
		conn.setAutoCommit(false);
		Statement stat = conn.createStatement();

		String sql1 = "drop SEQUENCE SEQ_ORDER_ID ";
		String sql2 = "CREATE SEQUENCE SEQ_ORDER_ID INCREMENT BY 1 START WITH 1 MAXVALUE  999999 NOCYCLE CACHE 10";

		stat.addBatch(sql1);
		stat.addBatch(sql2);

		int[] rs = stat.executeBatch();
		System.out.println(Arrays.toString(rs));
		conn.commit();
	}
}
