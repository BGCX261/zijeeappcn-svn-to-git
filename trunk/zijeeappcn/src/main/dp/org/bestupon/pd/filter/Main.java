package org.bestupon.pd.filter;


public class Main {

	/*public static void main(String[] args) {
		String msg = "<Script language='javascript'>javascript 代码</script> 敏感信息";
		MsgProcessor processor = new MsgProcessor();
		processor.setMsg(msg);
		log.info("HTML结果：" +processor.process());
	}*/
    
    
	public static void main(String[] args) {
		String msg = "<Script language='javascript'>javascript 代码</script> 敏感信息";
     
		AbsractFilterChian chian = new FilterChian();
        chian.addAbsractFilterChian(new HtmlFilter());
        
        AbsractFilterChian chian2 = new FilterChian();
        chian2.addAbsractFilterChian(new SesitiveFilter());

        chian.addAbsractFilterChian(chian2);

        MsgProcessor processor = new MsgProcessor();

        processor.setChian(chian);
        processor.setMsg(msg);
		
		System.out.println("HTML结果：" +processor.process());
	}
	

}
