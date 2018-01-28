package org.etg.nirc.net;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.etg.nirc.net.event.Event;
import org.etg.nirc.net.event.EventController;
import org.etg.nirc.net.packet.Packet;
import org.etg.nirc.plugin.Plugin;
import org.etg.nirc.plugin.PluginController;

/**
 * Handles aspects of the connection
 * @author Mitchell Bolen
 */
public class ConnectionHandler extends SimpleChannelHandler {
		
	/** Logger */
	private static final Logger log;
	
	static {		
		log = Logger.getLogger(ConnectionHandler.class.getName());
		log.setLevel(Level.INFO);
		/*
	    Handler consoleHandler = null;
	    for(Handler handler : log.getHandlers()) {
	        if (handler instanceof ConsoleHandler) {
	            consoleHandler = handler;
	            break;
	        }
	    }
	    if(consoleHandler == null) {
	        consoleHandler = new ConsoleHandler();
	        log.addHandler(consoleHandler);
	    }
	    consoleHandler.setLevel(Level.WARNING);*/
	}
	
	/**
	 * Parses a given message and crafts a packet representation
	 * @param message incoming message
	 * @return packet
	 */
	private Packet parseMessage(String message) {
		String source = null;
		String opcode = null;
		String destination = null;
		String payload = null;		
		if(!message.startsWith(":")) {
			String[] args = message.split(" :");
			opcode = args[0];
			payload = args[1];
		} else {
			final int EXTRA_CHAR = 5;
			Scanner scanner = new Scanner(message.substring(1));		
			source = scanner.next();
			opcode = scanner.next();
			destination = scanner.next();
			if(scanner.hasNext())
				payload = message.substring(source.length() + opcode.length() + destination.length() + (scanner.next().startsWith(":") ? EXTRA_CHAR : EXTRA_CHAR - 1));
		}
		Packet p = new Packet(opcode, source, destination, payload);
		p.addEvent(EventController.getInstance().getEvents(opcode));
		if(!p.hasEvents())
			log.warning("No events for: " + p);
		return p;
	}
	
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
    	log.info("Connected!");
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) {
    	log.info("Disconnected!");
    }
    
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
    	String message = e.getMessage().toString().trim();
    	Packet p = parseMessage(message);
    	log.fine("Received Packet: " + p);
		if(p.hasEvents())
			for(Event eve : p.getEvents())
				eve.actionPerformed(p);
		PluginController pc = PluginController.getInstance();
		LinkedList<Plugin> plugins = pc.getTriggeredPlugins(p.getOpcode(), p);
		if(plugins != null)
			for(Plugin plugin : plugins)
				plugin.actionPerformed(p);
    }
   
    
    @Override
    public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
    	String message = e.getMessage().toString().trim();
    	log.fine("Writting Packet: " + message);
    	super.writeRequested(ctx, e);
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        log.severe("Unexpected exception from downstream. " + e.getCause());
    }
}
