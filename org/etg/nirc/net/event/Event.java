package org.etg.nirc.net.event;

import org.etg.nirc.net.packet.Packet;

/**
 * Event template
 * 
 * @author Mitchell Bolen
 */
public interface Event {
	
	/**
	 * Method to be triggered when received
	 * @param p
	 */
	public abstract void actionPerformed(Packet p);
}
