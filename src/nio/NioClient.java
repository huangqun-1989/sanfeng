package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {
    public static void main(String[] args) throws IOException {
        SocketChannel client = SocketChannel.open();
        client.connect(new InetSocketAddress("127.0.0.1", 8888));
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.put("abcde".getBytes());
        buf.flip();
        while (buf.hasRemaining()) {
            client.write(buf);
        }

    }

}
