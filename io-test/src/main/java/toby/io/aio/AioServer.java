package toby.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.Selector;
import java.util.concurrent.CountDownLatch;

public class AioServer {
	
	public final CountDownLatch latch = new CountDownLatch(1);

	private int port = 1733;
	private Selector selector;
	private ByteBuffer readBuffer = ByteBuffer.allocate(1024);
	private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
	private boolean readAndSendIsDone = false;

	AsynchronousServerSocketChannel serverSocketChannel;
	
	public void launch() throws IOException {

		selector = Selector.open();
		serverSocketChannel = AsynchronousServerSocketChannel.open();
		serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", port));
		System.out.println(String.format("AIO bind to port: $s%1", port));
	}

}
