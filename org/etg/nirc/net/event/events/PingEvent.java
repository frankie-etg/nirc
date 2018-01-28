package org.etg.nirc.net.event.events;

import org.etg.nirc.net.event.Event;
import org.etg.nirc.net.packet.Packet;
import org.etg.nirc.net.packet.PacketFactory;

/**
 * Ping event
 * @author Mitchell Bolen
 */
public class PingEvent implements Event {
		
	private PacketFactory pf = PacketFactory.getInstance();
	
	@Override
	public void actionPerformed(Packet p) {
		pf.getConnection().write(pf.pong(p.getPayload()));
	}

}
