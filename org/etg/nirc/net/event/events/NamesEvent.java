package org.etg.nirc.net.event.events;

import java.util.logging.Logger;
import org.etg.nirc.Vars;
import org.etg.nirc.net.event.Event;
import org.etg.nirc.net.packet.Packet;
import org.etg.nirc.net.packet.PacketFactory;

public class NamesEvent implements Event {

	/** Logger */
	private final static Logger log;
	
	static {
		log = Logger.getLogger(ModeEvent.class.getName());
	}
	
	/* (non-Javadoc)
	 * @see org.etg.nirc.net.event.Event#actionPerformed(org.etg.nirc.net.packet.Packet)
	 */
	@Override
	public void actionPerformed(Packet p) {
		log.fine("NAMES: " + p.getPayload().substring(2));
                
		String[] names = p.getPayload().substring(2).split(" ");
		for(String name : names) {
			
			if(name.startsWith("@")) {
				Vars.opList.add(name.replaceAll("@", ""));
                                log.info("added op: " + name.replaceAll("@", ""));
			}
			if(name.startsWith("%")) {
				Vars.halfOpList.add(name.replaceAll("%", ""));
                                log.info("added hop: " + name.replaceAll("%", ""));
			}
			
			Vars.inIRC.add(name.replaceAll("%", "").replaceAll("@", "").replaceAll("\\+", ""));
			
		}
	}

}
