package org.etg.nirc.net.packet;

import java.util.LinkedList;

import org.etg.nirc.net.event.Event;

/**
 * Packet class
 * @author Mitchell Bolen
 */
public class Packet {

	/** Packet credentials */
	private String opcode, source, destination, payload;
	
	/** Events to be triggered on packet being received */
	private LinkedList<Event> events;
	
	/**
	 * Creates a new packet
	 * @param opcode
	 * @param source
	 * @param destination
	 * @param payload
	 */
	public Packet(String opcode, String source, String destination, String payload) {
		this.opcode = opcode;
		this.source = source;
		this.destination = destination;
		this.payload = payload;
	}
	
	/**
	 * Creates a packet with a different opcode, but same credentials
	 * @param opcode
	 * @param p
	 */
	public Packet(String opcode, Packet p) {
		this.opcode = opcode;
		this.source = p.getSource();
		this.destination = p.getDestination();
		this.payload = p.getPayload();
	}
	
	/**
	 * Copies and existing packet
	 * @param p
	 */
	public Packet(Packet p) {
		this.opcode = p.getOpcode();
		this.source = p.getSource();
		this.destination = p.getDestination();
		this.payload = p.getPayload();
	}
	
	/**
	 * Does the packet have events
	 * @return
	 */
	public boolean hasEvents() {
		return events != null;
	}
	
	/**
	 * Adds an event to the packet
	 * @param event
	 */
	public void addEvent(LinkedList<Event> event) {
		if(this.events == null)
			this.events = event;
	}
	
	/**
	 * Gets the events
	 * @return
	 */
	public LinkedList<Event> getEvents() {
		return events;
	}
	
	/**
	 * Get opcode
	 * @return
	 */
	public String getOpcode() {
		return opcode;
	}
	
	/**
	 * Get source
	 * @return
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Get destination
	 * @return
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Get payload
	 * @return
	 */
	public String getPayload() {
		return payload;
	}
	
	/**
	 * Get string representation
	 * @return
	 */
	@Override
	public String toString() {
		return "Opcode: " + opcode + ", Source: " + source + ", Destination: " + destination + ", Payload: " + payload;
	}
	
	/**
	 * Format the packet to be sent
	 * @return
	 */
	public String format() {
		return opcode + " " + (destination == null ? "" : destination + " ") + payload;
	}
	
	/**
	 * Gets equality by opcode
	 * @param o
	 * @return
	 */
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Packet))
			return false;
		Packet other = (Packet) o;
		return other.opcode == this.opcode;
	}
}
