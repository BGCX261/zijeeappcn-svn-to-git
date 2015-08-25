package org.bestupon.pd.filter;

public class HtmlFilter implements Filter {

	@Override
	public String doFilter(String msg) {
		String result = msg.replace("<", "[").replace(">", "]");
		return result;
	}

}
