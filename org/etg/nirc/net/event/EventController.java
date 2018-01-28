package org.etg.nirc.net.event;

import org.etg.nirc.net.event.events.MOTDEvent;
import org.etg.nirc.net.event.events.KickEvent;
import org.etg.nirc.net.event.events.TopicEvent;
import org.etg.nirc.net.event.events.MessageEvent;
import org.etg.nirc.net.event.events.PartEvent;
import org.etg.nirc.net.event.events.ModeEvent;
import org.etg.nirc.net.event.events.NoticeEvent;
import org.etg.nirc.net.event.events.PingEvent;
import org.etg.nirc.net.event.events.NamesEvent;
import org.etg.nirc.net.event.events.QuitEvent;
import org.etg.nirc.net.event.events.NickEvent;
import org.etg.nirc.net.event.events.SilentEvent;
import org.etg.nirc.net.event.events.JoinEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


/**
 * Registers known events to OPCodes
 * @author Mitchell Bolen
 */
public class EventController {
	
	/** Singleton */
	private static EventController instance;
	
	/** Map of opcode, event */
	private HashMap<String, LinkedList<Event>> events;
	
	/**
	 * Create a singleton instance
	 */
	private EventController() {
		this.events = new HashMap<String, LinkedList<Event>>();
		registerEvent("PING", new PingEvent());
		registerEvent("PRIVMSG", new MessageEvent());
		registerEvent("NOTICE", new NoticeEvent());
		registerEvent("JOIN", new JoinEvent());
		registerEvent("PART", new PartEvent());
		registerEvent("QUIT", new QuitEvent());
		registerEvent("NICK", new NickEvent());
		registerEvent("MODE", new ModeEvent());
		registerEvent("332", new TopicEvent());
		registerEvent("353", new NamesEvent());
		registerEvent("KICK", new KickEvent());
		
		/* Start of opcodes that don't really effect behavior */
		MOTDEvent motd = new MOTDEvent();
		registerEvent("375", motd);//motd start
		registerEvent("372", motd);//motd
		registerEvent("376", motd);//motd end	
		
		SilentEvent silent = new SilentEvent();
		registerEvent("001", silent);//welcome
		registerEvent("002", silent);//ircd info
		registerEvent("003", silent);//uptime
		registerEvent("004", silent);//ircd info
		registerEvent("005", silent);//supported opcodes
		registerEvent("042", silent);//unique id
		
		registerEvent("251", silent);//n users and m invis
		registerEvent("252", silent);//n ops online
		registerEvent("254", silent);//n chans

		registerEvent("255", silent);//i have x client y servers
		registerEvent("265", silent);//current global users
		registerEvent("266", silent);//current global users
		
		registerEvent("396", silent);//bla is now your displayed host
		
		registerEvent("333", silent);//topic set by
		registerEvent("366", silent);//end of /NAMES
	}
	
	/**
	 * Gets the singleton instance
	 * @return
	 */
	public static EventController getInstance() {
		if(instance == null)
			instance = new EventController();
		return instance;		
	}
	
	/**
	 * Gets the event for an opcode
	 * @param opcode
	 * @return
	 */
	public LinkedList<Event> getEvents(String opcode) {
		return events.get(opcode);
	}
	
	/**
	 * Registers an event to an opcode
	 * @param opcode
	 * @param e
	 */
	public void registerEvent(String opcode, Event e) {
		if(events.containsKey(opcode)) {
			events.get(opcode).add(e);
			return;
		}
		LinkedList<Event> elist = new LinkedList<Event>();
		elist.add(e);
		events.put(opcode, elist);
	}
	
	/**
	 * Unregisters all events for an opcode
	 * @param opcode
	 */
	public void unregisterEvent(String opcode) {
		events.remove(opcode);
	}
	
	/**
	 * Unregisters an event for an opcode
	 * @param opcode
	 */
	public void unregisterEvent(String opcode, Event e) {
		LinkedList<Event> elist = events.get(opcode);
		Iterator<Event> it = elist.iterator();
		while(it.hasNext()) {
			if(it.next().equals(e))
				it.remove();
		}
	}
	
}
