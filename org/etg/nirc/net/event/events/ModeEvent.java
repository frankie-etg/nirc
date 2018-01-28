package org.etg.nirc.net.event.events;

import java.util.logging.Logger;

import org.etg.nirc.Vars;
import org.etg.nirc.net.event.Event;
import org.etg.nirc.net.packet.Packet;
import org.etg.nirc.net.packet.PacketFactory;

public class ModeEvent implements Event {

	/** Logger */
	private final static Logger log;
	
	static {
		log = Logger.getLogger(ModeEvent.class.getName());
	}

	private PacketFactory pf = PacketFactory.getInstance();
	
	/* (non-Javadoc)
	 * @see org.etg.nirc.net.event.Event#actionPerformed(org.etg.nirc.net.packet.Packet)
	 */
	@Override
	public void actionPerformed(Packet p) {
		log.info(p.getSource() + " sets mode " + p.getPayload() + " " + p.getDestination());
		
		if(p.getPayload().contains("+o")) {
			Vars.opList.add(p.getPayload().replaceAll("\\+o ", ""));
		}
		if(p.getPayload().contains("+h")) {
			Vars.opList.add(p.getPayload().replaceAll("\\+h ", ""));
		}
		if(p.getPayload().contains("-o")) {
			Vars.opList.remove(p.getPayload().replaceAll("\\-o ", ""));
		}
		if(p.getPayload().contains("-h")) {
			Vars.opList.remove(p.getPayload().replaceAll("\\-h ", ""));
		}
	}

}
