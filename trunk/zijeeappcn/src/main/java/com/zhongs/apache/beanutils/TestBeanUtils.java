package com.zhongs.apache.beanutils;

import org.apache.commons.beanutils.BeanUtils;

public class TestBeanUtils {
	
	public static void main(String[] args) throws Exception {
		Bean1 b1 = new Bean1();
		Bean2 b2 = new Bean2();
		b1.name = "b1-name";
		
		BeanUtils.copyProperties(b2, b1);
		
		System.out.println(b2.name);
	}

}
