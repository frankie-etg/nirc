package org.etg.nirc.net.event.events;

import java.util.logging.Logger;

import org.etg.nirc.net.event.Event;
import org.etg.nirc.net.packet.Packet;

public class TopicEvent implements Event {

	/** Logger */
	private final static Logger log;
	
	static {
		log = Logger.getLogger(TopicEvent.class.getName());
	}
	
	/* (non-Javadoc)
	 * @see org.etg.nirc.net.event.Event#actionPerformed(org.etg.nirc.net.packet.Packet)
	 */
	@Override
	public void actionPerformed(Packet p) {
		log.fine("TOPIC: " + p.getPayload());
	}

}
