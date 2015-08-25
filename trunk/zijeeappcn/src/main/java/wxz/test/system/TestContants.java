package wxz.test.system;

public class TestContants {
	public static final int OP_READ = 1 << 0;
	public static final int OP_WRITE = 1 << 2;
	public static final int OP_CONNECT = 1 << 3;
	public static final int OP_ACCEPT = 1 << 4;

	public static void main(String[] args) throws Exception {
		// SelectionKey
		System.out.println(OP_READ);
		System.out.println(OP_WRITE);
		System.out.println(OP_CONNECT);
		System.out.println(OP_ACCEPT);
		System.out.println((OP_ACCEPT + OP_WRITE) & OP_WRITE);
		System.out.println(0 & OP_WRITE);
	}
}
