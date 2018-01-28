package org.etg.nirc.net;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.etg.nirc.net.packet.PacketFactory;

/**
 * Networking
 * @author Mitchell Bolen
 */
public class Connection {
	
	/** The channel */
	private Channel channel;
	
	/**
	 * Creates a new connection
	 * @param host
	 * @param port
	 */
	public Connection(String host, int port, String username, String ident, String realname, String channel, String pass) {
		ChannelFactory factory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool(), 3);
        ClientBootstrap bootstrap = new ClientBootstrap(factory);
        bootstrap.setPipelineFactory(new PipelineFactory());
        ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port)).awaitUninterruptibly();
        this.channel = future.awaitUninterruptibly().getChannel();
        PacketFactory.getInstance().setConnection(this);
        write(PacketFactory.getInstance().nick(username));
		write(PacketFactory.getInstance().user(ident, realname));
		write("ns id " + pass);	
		write("join " + channel);
	}

	/**
	 * Writes a raw line to the server based on IRC protocol
	 * @param raw
	 */
	public void write(String raw) {
		this.channel.write(raw + "\r\n");
	}
	
}
