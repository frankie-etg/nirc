package org.etg.nirc;

import java.util.ArrayList;

/**
 * Variables that'll get used everywhere
 * @author Mitchell Bolen
 */

public class Vars {
	//General Variables
    public static String server = "chat.freenode.net";
    public static int port = 6667;
	public static String nick = "IrcBot";
	public static String chan = "#channelname";
	public static String pass = "";
	public static ArrayList<String> opList = new ArrayList<String>();
	public static ArrayList<String> halfOpList = new ArrayList<String>();
	public static ArrayList<String> inIRC = new ArrayList<String>();

	//Color Variables
	public static String white = "0,1";
	public static String black = "1,0";
	public static String darkBlue = "2,0";
	public static String darkGreen = "3,0";
	public static String red = "4,0";
	public static String darkRed = "5,0";
	public static String purple = "6,0";
	public static String orange = "7,0";
	public static String yellow = "8,1";
	public static String lime = "9,1";
	public static String teal = "10,0";
	public static String cyan = "11,1";
	public static String blue = "12,0";
	public static String pink = "13,0";
	public static String darkGray = "14,0";
	public static String lightGray = "15,0";
	public static String bold = "";
}
