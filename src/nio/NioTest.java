package nio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class NioTest {


    public static void main(String[] args) throws  Exception {

        // 1. nio read test
//        nioReadTest();
        // 2. nio write test
//        nioWriteTest();
        socketChannelTest();


    }


    /**
     * nio read test
     */
    public static void nioReadTest() throws Exception {

        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/sanfeng/项目/testfile/nio_test.txt", "rw");


        FileChannel fc = randomAccessFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(256);


        int bytesRead = fc.read(byteBuffer);
        System.out.println(bytesRead);

        while (bytesRead != -1) {
            System.out.println("read=" +bytesRead);

            byteBuffer.flip();

            while (byteBuffer.hasRemaining()){
                System.out.println((char)byteBuffer.get());
            }

            byteBuffer.clear();
            bytesRead = fc.read(byteBuffer);
        }

        randomAccessFile.close();


    }



    /**
     * nio read test
     */
    public static void nioWriteTest() throws Exception {

        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/sanfeng/项目/testfile/nio_test.txt", "rw");


        FileChannel fc = randomAccessFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(256);


        byteBuffer.put((byte)12);

        fc.write(byteBuffer);


        randomAccessFile.close();


    }



    public  static void socketChannelTest() {
        SocketChannel sc = null;
        try {
            sc = SocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            sc.connect(new InetSocketAddress("www.baidu.cddddddm", 80));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
