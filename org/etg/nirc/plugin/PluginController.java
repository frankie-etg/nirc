package org.etg.nirc.plugin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Logger;

import org.etg.nirc.net.packet.Packet;

/**
 * Plugin controller
 * @author Mitchell Bolen
 */
public class PluginController {

	
	/** Logger */
	private static final Logger log;

	static {		
		log = Logger.getLogger(PluginController.class.getName());
	}
	
	/** Singleton */
	private static PluginController instance;
	
	/** Plugins opcode, plugin */
	private HashMap<String, LinkedList<Plugin>> plugins;
	
	/**
	 * Create a singleton instance
	 */
	private PluginController() {
		this.plugins = new HashMap<String, LinkedList<Plugin>>();
	}
	
	/**
	 * Gets the singleton instance
	 * @return
	 */
	public static PluginController getInstance() {
		if(instance == null)
			instance = new PluginController();
		return instance;		
	}
	
	/**
	 * Gets a list of triggered plugins
	 * @param opcode
	 * @param p
	 * @return
	 */
	public LinkedList<Plugin> getTriggeredPlugins(String opcode, Packet p) {
		if(!plugins.containsKey(opcode))
			return null;
		
		LinkedList<Plugin> plugs = new LinkedList<Plugin>(plugins.get(opcode));
		Iterator<Plugin> it = plugs.iterator();
		while(it.hasNext())
			if(!it.next().trigger(p))
				it.remove();
		return plugs;
	}
	
	/**
	 * Loads plugin
	 * @param pluginName
	 * @return
	 */
	public boolean load(String pluginName) {
		pluginName = "plugins." + pluginName;
		try {		
			ClassLoader loader = PluginController.class.getClassLoader();
			@SuppressWarnings("unchecked")
			Class<Plugin> clazz = (Class<Plugin>) loader.loadClass(pluginName);			
			Plugin plug = clazz.newInstance();
			
			String opcode = plug.opcode();
			if(opcode.length() != 0) {
				if(plugins.containsKey(opcode))
					plugins.get(opcode).add(plug);
				else {
					LinkedList<Plugin> ps = new LinkedList<Plugin>();
					ps.add(plug);
					plugins.put(opcode, ps);
				}					
			}
			
		} catch(Exception e) {
			log.info("Error loading plugin: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;	
	}
	
	/**
	 * Unloads a plugin
	 * @param plugin
	 * @return
	 */
	public boolean unload(String pluginName) {
		pluginName = "plugins." + pluginName;
		for(String opcode : plugins.keySet()) {
			LinkedList<Plugin> ps = plugins.get(opcode);
			if(ps.size() == 0)
				continue;
			
			Iterator<Plugin> it = ps.iterator();
			while(it.hasNext()) {
				if(pluginName.equals(it.next().getClass().getName())) {
					it.remove();
					return true;
				}
			}
		}
		return false;
	}
	
}
