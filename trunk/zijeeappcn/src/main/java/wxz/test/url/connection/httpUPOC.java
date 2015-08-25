package wxz.test.url.connection;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class httpUPOC { 

	public static String askYahooAboutZip(String zipCode) throws Exception {
		// URL url = new
		// URL("http://api.local.yahoo.com/MapsService/V1/geocode?appid=YahooDemo&zip="+zipCode);
		URL url = new URL(
				"http://local.yahooapis.com/MapsService/V1/geocode?appid=cbesbUseCase4&zip="
						+ zipCode);
		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
		huc.setRequestMethod("GET");
		huc.getOutputStream();
		huc.connect();
		InputStream is = huc.getInputStream();
		StringBuffer response = new StringBuffer();
		int code = huc.getResponseCode();
		System.out.print("Yahoo response is:" + code);
		if (code >= 200 && code < 300) {
			int ch;
			while ((ch = is.read()) > 0) {
				// System.out.println((char)ch);
				response.append((char) ch);
			}
			huc.disconnect();
			return new String(response);
		}

		return null;
	}

}

