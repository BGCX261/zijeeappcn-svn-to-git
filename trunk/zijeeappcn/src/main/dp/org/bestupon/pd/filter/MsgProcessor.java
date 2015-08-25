package org.bestupon.pd.filter;

public class MsgProcessor {
//	private String msg;
//	/**
//	 * 多个过滤器组合到一起之后就会形成过滤器链。
//	 * 好处是过滤规则的排序，
//	 * 先后顺序的的原因，新旧规则可以灵活的替换更新
//	 * 责任链模式
//	 */
//	private Filter filters []= {new HtmlFilter(),new SesitiveFilter()};
//	
//	public String getMsg() {
//		return msg;
//	}
//
//	public void setMsg(String msg) {
//		this.msg = msg;
//	}
//	public String processHTML() {
//		Filter filter = new HtmlFilter();
//		return  filter.doFilter(msg);
//	}
//	
//	public String process() {
//		for(Filter filter :filters) {
//			msg = filter.doFilter(msg);
//		}
//		return msg;
//	}
//	
	
	
	private AbsractFilterChian chian;
	private String msg;
	
	
	public String process() {
	    return chian.doFilter(msg);
	}

	
	public AbsractFilterChian getChian() {
		return chian;
	}
	public void setChian(AbsractFilterChian chian) {
		this.chian = chian;
	}
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
