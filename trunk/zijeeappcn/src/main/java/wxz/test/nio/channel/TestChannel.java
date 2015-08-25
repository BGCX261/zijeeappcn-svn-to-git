package wxz.test.nio.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class TestChannel {

    public static void main(String[] args) throws IOException {
        
        ReadableByteChannel in = Channels.newChannel(System.in);
        WritableByteChannel out = Channels.newChannel(System.out);
        
        ByteBuffer buffer = ByteBuffer.allocate(1 * 1024);
        while (in.read(buffer) != -1) {
            buffer.flip();
            if (buffer.hasRemaining()) {
                out.write(buffer);
            }
            buffer.compact();
            buffer.clear();
        }
        in.close();
        out.close();
        
    }

}
