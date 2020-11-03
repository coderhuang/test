package toby.io.nio;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler extends ChannelHandlerAdapter {

	private final ByteBuf message;

	public TimeClientHandler() {

		byte[] bytes = "QUERY TIME ORDER".getBytes();
		message = Unpooled.buffer(bytes.length);
		message.writeBytes(bytes);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(message);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		ByteBuf buf = (ByteBuf) msg;
		byte[] bytes = new byte[buf.readableBytes()];
		String readMessage = new String(bytes, StandardCharsets.UTF_8);

		System.out.println(MessageFormat.format("time client receive message:{0} \n", readMessage));
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}

}
