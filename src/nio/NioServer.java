package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NioServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(8888));
        server.configureBlocking(false);


        while (true) {
            SocketChannel sc = server.accept();
            if (sc != null) {
                ByteBuffer buf = ByteBuffer.allocate(48);
                int reads = sc.read(buf);

                while(reads!=-1) {
                    System.out.println("reads = " + reads);
                    buf.flip();
                    while (buf.hasRemaining()) {
                        System.out.println((char)buf.get());
                    }

                    buf.clear();
                    reads = sc.read(buf);
                }
            }


        }


    }
}
