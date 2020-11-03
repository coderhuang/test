package toby.io.nio;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;

public class TimeClient {

	public static void main(String[] args) {

		new TimeClient().connect("127.0.0.1", 7513);
	}

	public void connect(String host, int port) {

		try (NioEventLoopGroup group = new NioEventLoopGroup();) {
			Bootstrap bootStrap = new Bootstrap();
			bootStrap.group(group).channel(SocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true)
					.handler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {

							ch.pipeline().addLast(new TimeClientHandler());
						}
					});

			ChannelFuture channelFuture = bootStrap.connect(host, port).sync();

			channelFuture.channel().close().sync();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
