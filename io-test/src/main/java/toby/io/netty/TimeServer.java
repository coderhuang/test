package toby.io.netty;

import org.apache.commons.lang3.ArrayUtils;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;

public class TimeServer {

	public static void main(String[] args) {

		int port = 7513;
		if (ArrayUtils.isNotEmpty(args) && args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			} catch (Exception e) {
				port = 7513;
			}
		}
		new TimeServer().bind(port);
	}

	public void bind(int port) {

		try (NioEventLoopGroup bossGroup = new NioEventLoopGroup();
				NioEventLoopGroup workGroup = new NioEventLoopGroup();) {

			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workGroup).channel(ServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 1024).option(ChannelOption.SO_KEEPALIVE, true)
					.childHandler(new ChildChannelHandler());
			ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

			channelFuture.channel().closeFuture().sync();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			ch.pipeline().addLast(new TimeServerHandler());
		}

	}
}
