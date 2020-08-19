package IOTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOTest {
    public static void fastCopy(String src, String dist) throws IOException {
        //输入字节流
        FileInputStream fin = new FileInputStream(src);
        //获取输入字节流的文件通道
        FileChannel fcin = fin.getChannel();
        //获取目标文件的输出字节流
        FileOutputStream fout = new FileOutputStream(dist);
        //获取输出字节流的文件通道
        FileChannel fcout = fout.getChannel();
        //为缓冲区分配1024个字节
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while (true) {
            int r = fcin.read(buffer);
            if (r == -1) {
                break;
            }
            //切换读写 先flip 再write
            buffer.flip();
            //把缓冲区的内容写入输出文件中
            fcout.write(buffer);
            //清空缓冲区
            buffer.clear();
        }
    }

    public static void NIOtest1() throws IOException {
        //创建选择器
        Selector selector = Selector.open();
        //将通道注册到选择器上
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        //通道必须配置为非阻塞模式，否则使用选择器就没有任何意义了
        //因为如果通道在某个事件上被阻塞，那么服务器就不能响应其它事件
        //必须等待这个事件处理完毕才能去处理其它事件,显然这和选择器的作用背道而驰。
        ssChannel.configureBlocking(false);
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            //使用 select() 来监听到达的事件，它会一直阻塞直到有至少一个事件到达。
            int num = selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()) {
                    // ...
                } else if (key.isReadable()) {
                    // ...
                }
                keyIterator.remove();
            }
        }


    }

    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();

        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        ssChannel.configureBlocking(false);
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        ServerSocket serverSocket = ssChannel.socket();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8888);
        serverSocket.bind(address);

        while (true) {

            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keys.iterator();

            while (keyIterator.hasNext()) {

                SelectionKey key = keyIterator.next();

                if (key.isAcceptable()) {

                    ServerSocketChannel ssChannel1 = (ServerSocketChannel) key.channel();

                    // 服务器会为每个新连接创建一个 SocketChannel
                    SocketChannel sChannel = ssChannel1.accept();
                    sChannel.configureBlocking(false);

                    // 这个新连接主要用于从客户端读取数据
                    sChannel.register(selector, SelectionKey.OP_READ);

                } else if (key.isReadable()) {

                    SocketChannel sChannel = (SocketChannel) key.channel();
                    System.out.println(key.toString()+": "+readDataFromSocketChannel(sChannel));
                    sChannel.close();
                }

                keyIterator.remove();
            }
        }
    }

    private static String readDataFromSocketChannel(SocketChannel sChannel) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuilder data = new StringBuilder();

        while (true) {

            buffer.clear();
            int n = sChannel.read(buffer);
            if (n == -1) {
                break;
            }
            buffer.flip();
            int limit = buffer.limit();
            char[] dst = new char[limit];
            for (int i = 0; i < limit; i++) {
                dst[i] = (char) buffer.get(i);
            }
            data.append(dst);
            buffer.clear();
        }
        return data.toString();
    }
}
