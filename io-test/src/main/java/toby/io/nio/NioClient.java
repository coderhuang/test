package toby.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class NioClient {

	private Selector selector;
	private ByteBuffer readBuffer = ByteBuffer.allocate(1024);
	private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

	private boolean sendAndReadIsDone = false;

	public void launch(String host, int port) throws Exception {

		selector = Selector.open();
		try {

			SocketChannel sc = SocketChannel.open();
			sc.configureBlocking(false);
			sc.connect(new InetSocketAddress(host, port));
			sc.register(selector, SelectionKey.OP_CONNECT);
			System.err.println("1->client try connect");
			while (!sendAndReadIsDone) {

				if (selector.select(3) < 1) {
					continue;
				}

				processSelectedKey(selector.selectedKeys().iterator());
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void processSelectedKey(Iterator<SelectionKey> itr) throws Exception {

		while (itr.hasNext()) {

			SelectionKey sk = itr.next();
			try {
				if (sk.isConnectable()) {
					connect(sk);
				} else if (sk.isReadable()) {
					read(sk);
				} else if (sk.isWritable()) {
					write(sk);
				}
			} catch (Exception e) {
				throw e;
			} finally {
				itr.remove();
			}
		}
	}

	private void connect(SelectionKey sk) throws IOException {

		SocketChannel clientChannel = ((SocketChannel) sk.channel());
		clientChannel.finishConnect();
		clientChannel.register(selector, SelectionKey.OP_WRITE);
		System.err.println("3->client connected");
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
		clientChannel.close();
		sendAndReadIsDone = true;
		System.err.println("9->client readed");
	}

	private void write(SelectionKey sk) throws IOException {

		SocketChannel clientChannel = ((SocketChannel) sk.channel());
		this.writeBuffer.clear();
		this.writeBuffer.put("4->client is in aha!".getBytes(StandardCharsets.UTF_8));
		this.writeBuffer.flip();
		try {
			clientChannel.write(writeBuffer);
		} catch (IOException e) {

			sk.cancel();
			clientChannel.close();
		}
		clientChannel.register(selector, SelectionKey.OP_READ);
		System.err.println("5->client writed");
	}
}
