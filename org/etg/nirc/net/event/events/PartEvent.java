package org.etg.nirc.net.event.events;

import java.util.logging.Logger;

import org.etg.nirc.Vars;
import org.etg.nirc.net.event.Event;
import org.etg.nirc.net.packet.Packet;
import org.etg.nirc.net.packet.PacketFactory;

/**
 * Part event
 * @author Mitchell Bolen
 */
public class PartEvent implements Event {

	/** Logger */
	private final static Logger log;
	
	static {
		log = Logger.getLogger(PartEvent.class.getName());
	}
	
	private PacketFactory pf = PacketFactory.getInstance();
	
	/* (non-Javadoc)
	 * @see org.etg.nirc.net.event.Event#actionPerformed(org.etg.nirc.net.packet.Packet)
	 */
	@Override
	public void actionPerformed(Packet p) {
		log.info(p.getSource() + " has left " + p.getDestination() + " ('" + p.getPayload() + "')");


                for(Object o : Vars.opList.toArray()) {
                    if(o.toString().equalsIgnoreCase(p.getSource())) {
                        Vars.opList.remove(o);
                    }
                }
                for(Object h : Vars.halfOpList.toArray()) {
                    if(h.toString().equalsIgnoreCase(p.getSource())) {
                        Vars.halfOpList.remove(h);
                    }
                }
                
		pf.getConnection().write("names " + Vars.chan);
	}

}
