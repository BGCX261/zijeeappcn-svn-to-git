package org.bestupon.pd.filter;

public class SesitiveFilter  implements Filter{

	@Override
	public String doFilter(String msg) {
		return msg.replaceAll("敏感", "普通词汇");
	}

}
