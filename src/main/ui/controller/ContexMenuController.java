package main.ui.controller;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import main.ui.TextInternalizatorController;
import main.ui.model.Key;
import main.utils.Constants;

public class ContexMenuController {
    private final static Logger LOGGER = Logger.getLogger("ContexMenuController");

	private ArrayList<Key> contextMenuFields;
	private TextInternalizatorController internalizator;	

	
	public ContexMenuController(TextInternalizatorController internalizator, Handler logger) {
		this.internalizator = internalizator;
		try {
			Handler fileHandler = logger;
			SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            LOGGER.addHandler(fileHandler);
            fileHandler.setLevel(Level.ALL);
		} catch (SecurityException e) {
			LOGGER.log(Level.SEVERE, "Error creating ContexMenuController", e);
		}
		generateContextMenu();
	}

	public void generateContextMenu() {
		contextMenuFields = new ArrayList<Key>();
		contextMenuFields.add(new Key(internalizator.getKEY_VOL_MUTE(), Constants.KEY_VOL_MUTE));
		contextMenuFields.add(new Key(internalizator.getKEY_VOL_UP(), Constants.KEY_VOL_UP));
		contextMenuFields.add(new Key(internalizator.getKEY_VOL_DOWN(), Constants.KEY_VOL_DOWN));
		contextMenuFields.add(new Key("",Constants.SEPARATOR));
		contextMenuFields.add(new Key(Constants.KEY_CTRL_NAME, Constants.KEY_CTRL + "+"));
		contextMenuFields.add(new Key(Constants.KEY_ALT_NAME, Constants.KEY_ALT + "+"));
		contextMenuFields.add(new Key(Constants.KEY_SHIFT_NAME, Constants.KEY_SHIFT + "+"));
		contextMenuFields.add(new Key(Constants.KEY_TAB_NAME, Constants.KEY_TAB));
		contextMenuFields.add(new Key(Constants.KEY_GUI_NAME, Constants.KEY_GUI + "+"));
	}
	
	public ArrayList<Key> getContextMenuFields(){
		return this.contextMenuFields;
	}

}
