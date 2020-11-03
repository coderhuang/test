package toby.io.netty;

import static java.text.MessageFormat.format;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String reqString = new String(req, StandardCharsets.UTF_8);
		System.out.println(format("the time server receive request: {0} \n", reqString));
		String respStr = "QUERY TIME ORDER".equals(reqString) ? LocalDateTime.now().toString() : "BAD REQUEST";
		ByteBuf respBuf = Unpooled.copiedBuffer(respStr.getBytes());
		ctx.write(respBuf);
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
