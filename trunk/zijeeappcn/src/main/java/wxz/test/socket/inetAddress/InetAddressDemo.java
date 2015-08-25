package wxz.test.socket.inetAddress;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class InetAddressDemo {
	public static void main(String[] args) throws Exception {
		method1();
		method2();
	}
	private static void method2() throws UnknownHostException{
		String host = "localhost";
		InetAddress[] addrList = InetAddress.getAllByName(host );
		for (InetAddress inetAddress : addrList) {
			System.out.println(inetAddress.getHostAddress()+","+inetAddress.getHostName());
		}
	}
	
	
	private static void method1() throws SocketException {
		Enumeration<NetworkInterface> interfaceList = NetworkInterface
				.getNetworkInterfaces();
		if(null == interfaceList){
			System.out.println("interface list is null");
		}
		while (interfaceList.hasMoreElements()) {
			NetworkInterface iface = (NetworkInterface) interfaceList
					.nextElement();
			System.out.println("iface:"+iface.getName());
			Enumeration<InetAddress> addr= iface.getInetAddresses(); 
			while (addr.hasMoreElements()) {
				InetAddress inetAddress = (InetAddress) addr.nextElement();
				System.out.print(inetAddress instanceof Inet4Address ? "V4"
						: (inetAddress instanceof Inet6Address ? "V5" : "??"));
				System.out.println("---"+inetAddress.getHostName());
			}
			
		}
	}
}
