package org.etg.nirc.net.event.events;

import java.util.logging.Logger;

import org.etg.nirc.Vars;
import org.etg.nirc.net.event.Event;
import org.etg.nirc.net.packet.Packet;
import org.etg.nirc.net.packet.PacketFactory;

public class QuitEvent implements Event {

	/** Logger */
	private final static Logger log;
	
	static {
		log = Logger.getLogger(QuitEvent.class.getName());
	}

	private PacketFactory pf = PacketFactory.getInstance();
	
	/* (non-Javadoc)
	 * @see org.etg.nirc.net.event.Event#actionPerformed(org.etg.nirc.net.packet.Packet)
	 */
	@Override
	public void actionPerformed(Packet p) {
		log.info(p.getSource() + " has quit ('" + p.getPayload() + "')");
		
		pf.getConnection().write("names " + Vars.chan);
	}

}
