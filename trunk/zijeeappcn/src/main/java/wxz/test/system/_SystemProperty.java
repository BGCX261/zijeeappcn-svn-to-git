package wxz.test.system;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class _SystemProperty {

	public static void main(String[] args) {
		System.out.println(System.getProperty("java.ext.dirs"));
		System.out.println(System.getProperty("java.class.path"));
		System.out.println(System.getProperty("java.library.path"));
		System.out.println("Java运行时环境版："
				+ System.getProperty("java.version"));

		System.out.println("Java 运行时环境供应商:\n"
				+ System.getProperty("java.vendor"));

		System.out.println("Java 供应商的URL:\n"
				+ System.getProperty("java.vendor.url"));

		System.out.println("Java安装目录:\n" + System.getProperty("java.home"));

		System.out.println("Java 虚拟机规范版："
				+ System.getProperty("java.vm.specification.version"));

		System.out.println("Java 类格式版本号:\n"
				+ System.getProperty("java.class.version"));

		System.out
				.println("Java类路径：\n" + System.getProperty("java.class.path"));

		System.out.println("加载库时搜索的路径列："
				+ System.getProperty("java.library.path"));

		System.out.println("默认的临时文件路："
				+ System.getProperty("java.io.tmpdir"));

		System.out.println("要使用的 JIT 编译器的名称:\n"
				+ System.getProperty("java.compiler"));

		System.out.println("????或多个扩展目录的路径:\n"
				+ System.getProperty("java.ext.dirs"));

		System.out.println("操作系统的名：" + System.getProperty("os.name"));

		System.out.println("操作系统的架：" + System.getProperty("os.arch"));

		System.out.println("操作系统的版：" + System.getProperty("os.version"));

		System.out.println("文件分隔符（??UNIX 系统中是??”）:"
				+ System.getProperty("file.separator"));

		System.out.println("路径分隔符（??UNIX 系统中是??”）:"
				+ System.getProperty("path.separator"));

		System.out.println("行分隔符（在 UNIX 系统中是??n”）:"
				+ System.getProperty("line.separator"));

		System.out.println("用户的账户名：" + System.getProperty("user.name"));

		System.out.println("用户的主目录:" + System.getProperty("user.home"));

		System.out.println("用户的当前工作目：" + System.getProperty("user.dir"));
		System.out.println("-------------------------------------------------------------------------");
		Properties p = System.getProperties();
		Set set= p.entrySet();
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			Entry entry = (Entry) iterator.next();
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		
		
		System.out.println("java.nio.channels.spi.SelectorProvider："+System.getProperty("java.nio.channels.spi.SelectorProvider"));
//		java.runtime.name:Java(TM) 2 Runtime Environment, Standard Edition
//		sun.boot.library.path:C:\Program Files\Java\jdk1.5.0_18\jre\bin
//		java.vm.version:1.5.0_18-b02
//		java.vm.vendor:Sun Microsystems Inc.
//		java.vendor.url:http://java.sun.com/
//		path.separator:;
//		java.vm.name:Java HotSpot(TM) Client VM
//		file.encoding.pkg:sun.io
//		sun.java.launcher:SUN_STANDARD
//		user.country:CN
//		sun.os.patch.level:Service Pack 3
//		java.vm.specification.name:Java Virtual Machine Specification
//		user.dir:D:\workspace\TestClassLoader
//		java.runtime.version:1.5.0_18-b02
//		java.awt.graphicsenv:sun.awt.Win32GraphicsEnvironment
//		java.endorsed.dirs:C:\Program Files\Java\jdk1.5.0_18\jre\lib\endorsed
//		os.arch:x86
//		java.io.tmpdir:C:\DOCUME~1\ADMINI~1\LOCALS~1\Temp\
//		line.separator:
//
//		java.vm.specification.vendor:Sun Microsystems Inc.
//		user.variant:
//		os.name:Windows XP
//		sun.jnu.encoding:GBK
//		java.library.path:C:\Program Files\Java\jdk1.5.0_18\bin;.;C:\WINDOWS\system32;C:\WINDOWS;C:/Program Files/Java/jdk1.5.0_18/bin/../jre/bin/client;C:/Program Files/Java/jdk1.5.0_18/bin/../jre/bin;C:/Program Files/Java/jdk1.5.0_18/bin/../jre/lib/i386;C:\Program Files\Java\jdk1.5.0_18/bin;C:\Program Files\PC Connectivity Solution\;C:\Program Files\Java\jdk1.6.0_13\bin;D:\oracle\product\10.1.0\Db_1\bin;D:\oracle\product\10.1.0\Db_1\jre\1.4.2\bin\client;D:\oracle\product\10.1.0\Db_1\jre\1.4.2\bin;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\Program Files\Common Files\Thunder Network\KanKan\Codecs;C:\MinGW\bin;C:\Program Files\QuickTime\QTSystem\;D:\eclipse3.7\eclipse;
//		java.specification.name:Java Platform API Specification
//		java.class.version:49.0
//		sun.management.compiler:HotSpot Client Compiler
//		os.version:5.1
//		user.home:C:\Documents and Settings\Administrator
//		user.timezone:
//		java.awt.printerjob:sun.awt.windows.WPrinterJob
//		file.encoding:UTF-8
//		java.specification.version:1.5
//		java.class.path:D:\workspace\TestClassLoader\bin
//		user.name:Administrator
//		java.vm.specification.version:1.0
//		java.home:C:\Program Files\Java\jdk1.5.0_18\jre
//		sun.arch.data.model:32
//		user.language:zh
//		java.specification.vendor:Sun Microsystems Inc.
//		awt.toolkit:sun.awt.windows.WToolkit
//		java.vm.info:mixed mode, sharing
//		java.version:1.5.0_18
//		java.ext.dirs:C:\Program Files\Java\jdk1.5.0_18\jre\lib\ext
//		sun.boot.class.path:C:\Program Files\Java\jdk1.5.0_18\jre\lib\rt.jar;C:\Program Files\Java\jdk1.5.0_18\jre\lib\i18n.jar;C:\Program Files\Java\jdk1.5.0_18\jre\lib\sunrsasign.jar;C:\Program Files\Java\jdk1.5.0_18\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.5.0_18\jre\lib\jce.jar;C:\Program Files\Java\jdk1.5.0_18\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.5.0_18\jre\classes
//		java.vendor:Sun Microsystems Inc.
//		file.separator:\
//		java.vendor.url.bug:http://java.sun.com/cgi-bin/bugreport.cgi
//		sun.io.unicode.encoding:UnicodeLittle
//		sun.cpu.endian:little
//		sun.desktop:windows
//		sun.cpu.isalist:pentium_pro+mmx pentium_pro pentium+mmx pentium i486 i386 i86

	}
}
