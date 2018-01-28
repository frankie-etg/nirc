package org.etg.nirc.plugin;

import org.etg.nirc.net.packet.Packet;

/**
 * Plugin template
 * @author Mitchell Bolen
 */
public interface Plugin {
	
	/**
	 * Returns true if the action should be triggered
	 * @param p
	 * @return
	 */
	public abstract boolean trigger(Packet p);
	
	/**
	 * Performs the action
	 * @param p
	 */
	public abstract void actionPerformed(Packet p);

	/**
	 * Opcode
	 * @return
	 */
	public abstract String opcode();

}
