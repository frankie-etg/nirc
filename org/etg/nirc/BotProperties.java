package org.etg.nirc;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Properties of the bot
 * @author Mitchell Bolen
 */
public class BotProperties {
	
	/** Singleton */
	private static BotProperties instance;
	
	/** Properties file */
	private Properties properties;
	
	/**
	 * Create a singleton instance
	 */
	private BotProperties() {
		this.properties = new Properties();
		try {
			this.properties.load(new FileInputStream("./data/bot.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the singleton instance
	 * @return
	 */
	public static BotProperties getInstance() {
		if(instance == null)
			instance = new BotProperties();
		return instance;		
	}
	
	/**
	 * Gets a property
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		return this.properties.getProperty(key);
	}

}
