package org.etg.nirc.net.event.events;

import java.util.logging.Logger;

import org.etg.nirc.net.event.Event;
import org.etg.nirc.net.packet.Packet;
import org.etg.nirc.net.packet.PacketFactory;

/**
 * Message event
 * @author Mitchell Bolen
 */
public class MessageEvent implements Event {
	
	/** Logger */
	private final static Logger log;
	
	static {
		log = Logger.getLogger(MessageEvent.class.getName());
	}
		
	private PacketFactory pf = PacketFactory.getInstance();
	
	@Override
	public void actionPerformed(Packet p) {
		log.info(p.getSource() + " PRIVMSG (" + p.getDestination() + "): " + p.getPayload());
        }

}
