package org.etg.nirc;

import org.etg.nirc.net.Connection;
import org.etg.nirc.plugin.PluginController;

/**
 * Main class that operates the bot
 * @author Mitchell Bolen
 */
public class Bot {

    public static void main(String[] args) {
        new Connection(Vars.server, Vars.port, Vars.nick, Vars.nick, Vars.nick, Vars.chan, Vars.pass);
 
        PluginController plugs = PluginController.getInstance();
        //plugs.load("");
    }
}