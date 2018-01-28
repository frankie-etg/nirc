package org.etg.nirc.net.event.events;

import java.util.logging.Logger;

import org.etg.nirc.net.event.Event;
import org.etg.nirc.net.packet.Packet;

/**
 * Notice event
 * @author Mitchell Bolen
 */
public class NoticeEvent implements Event {

	/** Logger */
	private final static Logger log;
	
	static {
		log = Logger.getLogger(NoticeEvent.class.getName());
	}
	
	/* (non-Javadoc)
	 * @see org.etg.nirc.net.event.Event#actionPerformed(org.etg.nirc.net.packet.Packet)
	 */
	@Override
	public void actionPerformed(Packet p) {
		log.fine(p.getSource() + " NOTICE (" + p.getDestination() + "): " + p.getPayload());
	}

}
