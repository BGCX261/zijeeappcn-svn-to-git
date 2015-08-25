package wxz.test.nio.buffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class TestByte {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		char[] chars = new char[20];
		CharBuffer buffer = CharBuffer.wrap(chars);
		buffer.put('h').put('e').put('l').put('l').put('0');
		System.out.println("capacity:"+buffer.capacity());
		System.out.println("limit:"+buffer.limit());
		System.out.println("position:"+buffer.position());
		System.out.println("");
		buffer.flip();
		System.out.println("capacity:"+buffer.capacity());
		System.out.println("limit:"+buffer.limit());
		System.out.println("position:"+buffer.position());
		System.out.println("");
		buffer.clear();
		buffer.put('a').put('b').put('c').put('d').put('e').put('f').put('g');
		buffer.put("01234567890", 2, 6);
		System.out.println("capacity:"+buffer.capacity());
		System.out.println("limit:"+buffer.limit());
		System.out.println("position:"+buffer.position());
		System.out.println("remaining:"+buffer.remaining());
		for (int i = 0; i < buffer.position(); i++) {
			System.out.print(buffer.get(i)+",");
		}
		System.out.println();
		for (int i = 0; i < chars.length; i++) {
			System.out.print(chars[i]+",");
		}
	}

	private static void test2() {
		CharBuffer buffer = CharBuffer.allocate(20);
		buffer.put('h').put('e').put('l').put('l').put('0');
		System.out.println("capacity:"+buffer.capacity());
		System.out.println("limit:"+buffer.limit());
		System.out.println("position:"+buffer.position());
		System.out.println("");
		buffer.flip();
		System.out.println("capacity:"+buffer.capacity());
		System.out.println("limit:"+buffer.limit());
		System.out.println("position:"+buffer.position());
		System.out.println("");
		buffer.clear();
		buffer.put('a').put('b').put('c').put('d').put('e').put('f').put('g');
		buffer.put("01234567890", 2, 6);
		System.out.println("capacity:"+buffer.capacity());
		System.out.println("limit:"+buffer.limit());
		System.out.println("position:"+buffer.position());
		System.out.println("remaining:"+buffer.remaining());
		for (int i = 0; i < buffer.position(); i++) {
			System.out.print(buffer.get(i)+",");
		}
	}
	
	public static void test1(){

		byte[] b = new byte[100];
		
		ByteBuffer buffer = ByteBuffer.allocate(100);
		buffer.put((byte)'h').put((byte)'e').put((byte)'l').put((byte)'l').put((byte)'0');
		System.out.println("capacity:"+buffer.capacity());
		System.out.println("limit:"+buffer.limit());
		System.out.println("position:"+buffer.position());
		System.out.println("");
		buffer.flip();
		System.out.println("capacity:"+buffer.capacity());
		System.out.println("limit:"+buffer.limit());
		System.out.println("position:"+buffer.position());
		System.out.println("");
		buffer.clear();
		buffer.put((byte)'a').put((byte)'b').put((byte)'c').put((byte)'d').put((byte)'e').put((byte)'f').put((byte)'g');
		System.out.println("capacity:"+buffer.capacity());
		System.out.println("limit:"+buffer.limit());
		System.out.println("position:"+buffer.position());
		System.out.println("remaining:"+buffer.remaining());
		
		
		
		for (int i = 0; i < buffer.position(); i++) {
			System.out.print(buffer.get(i)+",");
		}
		
	
	}

}
