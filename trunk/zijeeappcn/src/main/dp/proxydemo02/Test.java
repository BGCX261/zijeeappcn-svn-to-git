package orj.jzkangta.proxydemo02;

public class Test {

	
	public static void main(String[] args) {
		SaleComputer sc=ComputerProxy.getComputerMaker();
		//sc.sale("ÁªÏë");
		sc.sale("ÈýÐÇ");
//		sc.sale("Dell");

	}

}
