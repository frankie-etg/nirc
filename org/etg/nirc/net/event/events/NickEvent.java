package org.etg.nirc.net.event.events;

import java.util.logging.Logger;
import org.etg.nirc.Vars;
import org.etg.nirc.net.event.Event;
import org.etg.nirc.net.packet.Packet;

/**
 * @author Mitchell Bolen
 *
 */
public class NickEvent implements Event {
	
	/** Logger */
	private final static Logger log;
	
	static {
		log = Logger.getLogger(NickEvent.class.getName());
	}
	
	/* (non-Javadoc)
	 * @see org.etg.nirc.net.event.Event#actionPerformed(org.etg.nirc.net.packet.Packet)
	 */
	@Override
	public void actionPerformed(Packet p) {
		log.fine(p.getSource() + " is now known as " + p.getDestination());

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
	}


}
