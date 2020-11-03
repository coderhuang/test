package toby.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

	private Selector selector;
	private ByteBuffer readBuffer = ByteBuffer.allocate(1024);
	private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
	private boolean readAndSendIsDone = false;

	public void launch(int port) throws Exception {

		selector = Selector.open();
		try {

			ServerSocketChannel ssc = ServerSocketChannel.open();
			ssc.socket().bind(new InetSocketAddress(port));
			ssc.configureBlocking(false);
			ssc.register(selector, SelectionKey.OP_ACCEPT);
			while (!readAndSendIsDone) {

				if (selector.select(3) < 1) {
					continue;
				}

				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> itr = selectedKeys.iterator();
				processSelectedKey(itr);
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void processSelectedKey(Iterator<SelectionKey> itr) {

		while (itr.hasNext()) {

			SelectionKey sk = itr.next();
			try {
				if (sk.isAcceptable()) {
					accept(sk);
				} else if (sk.isReadable()) {
					read(sk);
				} else if (sk.isWritable()) {
					write(sk);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				itr.remove();
			}
		}
	}

	private void accept(SelectionKey sk) throws IOException {

		SocketChannel clientChannel = ((ServerSocketChannel) sk.channel()).accept();
		clientChannel.configureBlocking(false);
		clientChannel.register(selector, SelectionKey.OP_READ);
		System.err.println("2->server accepted");
	}

	private void read(SelectionKey sk) throws IOException {

		SocketChannel clientChannel = ((SocketChannel) sk.channel());
		this.readBuffer.clear();
		int readCount = 0;
		try {

			readCount = clientChannel.read(readBuffer);
		} catch (IOException e) {

			sk.cancel();
			clientChannel.close();
		}
		this.readBuffer.flip();
		String readString = new String(readBuffer.array(), 0, readCount);
		System.err.println(readString);
		clientChannel.register(selector, SelectionKey.OP_WRITE);
		System.err.println("6->server readed");
	}

	private void write(SelectionKey sk) throws IOException {

		SocketChannel clientChannel = ((SocketChannel) sk.channel());
		this.writeBuffer.clear();
		this.writeBuffer.put("7->server is done!".getBytes(StandardCharsets.UTF_8));
		this.writeBuffer.flip();
		try {
			clientChannel.write(writeBuffer);
		} catch (IOException e) {

			sk.cancel();
		} finally {
			clientChannel.close();
			readAndSendIsDone = true;
		}
		System.err.println("8->server writed");
	}
}
