package toby.io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;


public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AioServer> {

	@Override
	public void completed(AsynchronousSocketChannel result, AioServer attachment) {

		attachment.serverSocketChannel.accept(attachment, this);
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//		result.read(byteBuffer, byteBuffer, new ReadCompletionHandler());
	}

	@Override
	public void failed(Throwable exc, AioServer attachment) {
		// TODO Auto-generated method stub

	}

}
