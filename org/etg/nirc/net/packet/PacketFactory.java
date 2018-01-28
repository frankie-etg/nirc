package org.etg.nirc.net.packet;

import org.etg.nirc.net.Connection;

/**
 * Crafts packets
 * @author Mitchell Bolen
 */
public class PacketFactory {
	
	/** Singleton */
	private static PacketFactory instance;
	
	/** The connection */
	private Connection connection;	
	
	/**
	 * Gets the active connection
	 * @return
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * Sets the active connection
	 * @param connection
	 */
	public void setConnection(Connection connection) {
		if(this.connection == null)
			this.connection = connection;
	}
	
	/**
	 * Gets the singleton instance
	 * @return
	 */
	public static PacketFactory getInstance() {
		if(instance == null)
			instance = new PacketFactory();
		return instance;		
	}
	
	/**
	 * Pong packet
	 * @param payload
	 * @return
	 */
	public String pong(String payload) {
		return new Packet("PONG", null, null, payload).format();
	}
	
	/**
	 * Nick packet
	 * @param nick
	 * @return
	 */
	public String nick(String nick) {
		return new Packet("NICK", null, null, nick).format();
	}
	
	/**
	 * User packet
	 * @param ident
	 * @param realname
	 * @return
	 */
	public String user(String ident, String realname) {
		return new Packet("USER", null, null, ident + " 0 * :" + realname).format();
	}
	
	/**
	 * Join packet
	 * @param channel
	 * @return
	 */
	public String join(String... channel) {
		StringBuilder builder = new StringBuilder();
		if(channel.length == 1)
			return new Packet("JOIN", null, null, channel[0]).format();
		for(int i = 0; i < channel.length - 1; ++i)
			builder.append(channel[i] + ",");
		builder.append(channel[channel.length - 1]);
		return new Packet("JOIN", null, null, builder.toString()).format();
	}
	

	/**
	 * Messages destination
	 * @param dest
	 * @param payload
	 * @return
	 */
	public String message(String dest, String payload) {
		return new Packet("PRIVMSG", null, dest, payload).format();
	}

    public void write(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
