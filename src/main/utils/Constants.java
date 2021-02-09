package main.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	  public static final int STATUS_DISCONNECTED = 0;
	  public static final int STATUS_CONNECTED = 1;
	  public static final int STATUS_CONNECTED_CONFIGURED = 2;
	  public static final int MAX_CHARACTERS = 20;
	  public static final String KEY_SPACE = "space";
	  public static final String KEY_RETURN = "return";
	  public static final String KEY_L_ARROW = "larrow";
	  public static final String KEY_R_ARROW = "rarrow";
	  public static final String KEY_U_ARROW = "uarrow";
	  public static final String KEY_D_ARROW = "darrow";
	  public static final String KEY_VOL_MUTE = "mute";
	  public static final String KEY_VOL_UP = "volup";
	  public static final String KEY_VOL_DOWN = "voldown";
	  public static final String KEY_ESC = "esc";
	  public static final String KEY_CTRL = "ctrl";
	  public static final String KEY_SHIFT = "shift";
	  public static final String KEY_ALT = "alt";
	  public static final String KEY_TAB = "tab";
	  public static final String KEY_GUI = "gui";
	  public static final String KEY_CTRL_NAME = "Control";
	  public static final String KEY_SHIFT_NAME = "Shift";
	  public static final String KEY_ALT_NAME = "Alt";
	  public static final String KEY_TAB_NAME = "Tab";
	  public static final String KEY_GUI_NAME = "Windows/Cmd";
	  public static final String SEPARATOR = "separator";
	  public static final String FILE_PATTERN = "(([0-9]|R|G|B),"
	  		+ "([a-zA-Z-0-9]|-|\\.|\\+|\\*|/|\\?|¿|=|¡|\\!|\\\\|:|;|@|\\||\\$|\\(|\\)|\\{|\\}|\\[|\\])+"
	  		+ "#)+";
	  public static final List<Integer> keysConfigurables = new ArrayList<Integer>(){
		  	private static final long serialVersionUID = 1L;
		  	{
	  			add(2);
	  			add(4);
	  			add(6);
            }
	  	};
	
}
