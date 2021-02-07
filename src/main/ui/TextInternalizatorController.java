package main.ui;

import java.util.Locale;
import java.util.ResourceBundle;

public class TextInternalizatorController {

	private Application application;
	private Locale locale = Locale.ENGLISH;
	private String CONFIGURATION_SAVED, CONFIGURATION_SAVED_MESSAGE,
					RESTORE_VALUES, RESTORE_VALUES_MESSAGE,
					EXIT, EXIT_MESSAGE,
					CONNECTED, CONNECTED_MESSAGE,
					DISCONNECTED, DISCONNECTED_MESSAGE,
					ERROR,ERROR_MESSAGE,INFO,
					WARNING_SIZE, WARNING_SIZE_MESSAGE1, WARNING_SIZE_MESSAGE2,
					WARNING_PORT, WARNING_PORT_MESSAGE,
					CANT_OPEN_FILE,FORMAT_INCORRECT,IMPORT_SUCESSFULLY,
					FILE_NO_SAVED,FILE_SAVED,FILE_EXIST;
	
	
	public TextInternalizatorController(Application application, Locale locale) {
		super();
		this.application = application;
		setLocale(locale);
	}
	
	public void updateGUITexts() {
		  ResourceBundle bundle = ResourceBundle.getBundle("Messages", locale);  
		  application.getComLabel().setText(bundle.getString("comLabel"));
		  updateConnectButton(bundle);
		  application.getBtnSave().setText(bundle.getString("btnSave"));
		  application.getBtnTest().setText(bundle.getString("btnTest"));
		  application.getLblKey1().setText(bundle.getString("lblKey") + " 1:");
		  application.getLblKey2().setText(bundle.getString("lblKey") + " 2:");
		  application.getLblKey3().setText(bundle.getString("lblKey") + " 3:");
		  application.getLblKey4().setText(bundle.getString("lblKey") + " 4:");
		  application.getLblKey5().setText(bundle.getString("lblKey") + " 5:");
		  application.getLblKey6().setText(bundle.getString("lblKey") + " 6:");
		  application.getBtnUpdatePorts().setText(bundle.getString("btnUpdatePorts"));
		  application.getMenuOptions().setText(bundle.getString("menuOptions"));
		  application.getBtnExit().setText(bundle.getString("btnExit"));
		  application.getMntmExportConfiguration().setText(bundle.getString("exportConf"));
		  application.getMntmImportConfiguration().setText(bundle.getString("importConf"));
		  application.getBtnRestore1().setText(bundle.getString("btnRestore"));
		  application.getBtnRestore2().setText(bundle.getString("btnRestore"));
		  application.getBtnRestore3().setText(bundle.getString("btnRestore"));
		  application.getBtnRestore4().setText(bundle.getString("btnRestore"));
		  application.getBtnRestore5().setText(bundle.getString("btnRestore"));
		  application.getBtnRestore6().setText(bundle.getString("btnRestore"));
		  application.getBtnRestoreRGB().setText(bundle.getString("btnRestore"));
		  application.getBtnRestoreAll().setText(bundle.getString("btnRestoreAll"));
		  application.getLblKeys().setText(bundle.getString("lblKeys"));
		  application.getMnLanguage().setText(bundle.getString("language"));
		  application.getMntmEnglish().setText(bundle.getString("english"));
		  application.getMntmSpanish().setText(bundle.getString("spanish"));
		  application.getLblExample().setText(bundle.getString("example"));
	}

	public void setLocale(Locale locale) {
		if(locale.equals(Locale.ENGLISH) || locale.getLanguage().equals("es")) {
			this.locale = locale;
		} else {
			this.locale = Locale.ENGLISH;
		}
		updateDialogMessages();
	}

	private void updateDialogMessages() {
		  ResourceBundle bundle = ResourceBundle.getBundle("Messages", locale);  
		  this.CONNECTED = bundle.getString("CONNECTED");
		  this.CONNECTED_MESSAGE = bundle.getString("CONNECTED_MESSAGE");
		  this.DISCONNECTED = bundle.getString("DISCONNECTED");
		  this.DISCONNECTED_MESSAGE = bundle.getString("DISCONNECTED_MESSAGE");
		  this.ERROR = bundle.getString("ERROR");
		  this.ERROR_MESSAGE = bundle.getString("ERROR_MESSAGE");
		  this.EXIT = bundle.getString("EXIT");
		  this.EXIT_MESSAGE = bundle.getString("EXIT_MESSAGE");
		  this.RESTORE_VALUES = bundle.getString("RESTORE_VALUES");
		  this.RESTORE_VALUES_MESSAGE = bundle.getString("RESTORE_VALUES_MESSAGE");
		  this.CONFIGURATION_SAVED = bundle.getString("CONFIGURATION_SAVED");
		  this.CONFIGURATION_SAVED_MESSAGE = bundle.getString("CONFIGURATION_SAVED_MESSAGE");
		  this.WARNING_PORT = bundle.getString("WARNING_PORT");
		  this.WARNING_PORT_MESSAGE = bundle.getString("WARNING_PORT_MESSAGE");
		  this.WARNING_SIZE_MESSAGE1 = bundle.getString("WARNING_SIZE_MESSAGE1");
		  this.WARNING_SIZE_MESSAGE2 = bundle.getString("WARNING_SIZE_MESSAGE2");
		  this.WARNING_SIZE = bundle.getString("WARNING_SIZE");
		  this.INFO = bundle.getString("INFO");
		  this.CANT_OPEN_FILE = bundle.getString("CANT_OPEN_FILE");
		  this.FORMAT_INCORRECT = bundle.getString("FORMAT_INCORRECT");
		  this.IMPORT_SUCESSFULLY = bundle.getString("IMPORT_SUCESSFULLY");
		  this.FILE_NO_SAVED = bundle.getString("FILE_NO_SAVED");
		  this.FILE_SAVED = bundle.getString("FILE_SAVED");
		  this.FILE_EXIST = bundle.getString("FILE_EXIST");
	}

	public void updateConnectButton() {
		ResourceBundle bundle = ResourceBundle.getBundle("Messages", locale);  
		updateConnectButton(bundle);		
	}

	private void updateConnectButton(ResourceBundle bundle) {
		if(application.getBtnSave().isEnabled()) {
			application.getBtnConnect().setText(bundle.getString("btnDisconnect"));
		} else {
			application.getBtnConnect().setText(bundle.getString("btnConnect"));
		}
	}

	public String getCONFIGURATION_SAVED() {
		return CONFIGURATION_SAVED;
	}

	public String getCONFIGURATION_SAVED_MESSAGE() {
		return CONFIGURATION_SAVED_MESSAGE;
	}

	public String getRESTORE_VALUES() {
		return RESTORE_VALUES;
	}

	public String getRESTORE_VALUES_MESSAGE() {
		return RESTORE_VALUES_MESSAGE;
	}

	public String getEXIT() {
		return EXIT;
	}

	public String getEXIT_MESSAGE() {
		return EXIT_MESSAGE;
	}

	public String getCONNECTED() {
		return CONNECTED;
	}

	public String getCONNECTED_MESSAGE() {
		return CONNECTED_MESSAGE;
	}

	public String getERROR() {
		return ERROR;
	}

	public String getERROR_MESSAGE() {
		return ERROR_MESSAGE;
	}

	public String getWARNING_SIZE() {
		return WARNING_SIZE;
	}

	public String getWARNING_SIZE_MESSAGE1() {
		return WARNING_SIZE_MESSAGE1;
	}

	public String getWARNING_SIZE_MESSAGE2() {
		return WARNING_SIZE_MESSAGE2;
	}

	public String getWARNING_PORT() {
		return WARNING_PORT;
	}

	public String getWARNING_PORT_MESSAGE() {
		return WARNING_PORT_MESSAGE;
	}

	public String getDISCONNECTED() {
		return DISCONNECTED;
	}

	public String getDISCONNECTED_MESSAGE() {
		return DISCONNECTED_MESSAGE;
	}
	
	public String getINFO() {
		return INFO;
	}
	
	public String getCANT_OPEN_FILE() {
		return CANT_OPEN_FILE;
	}
	
	public String getFORMAT_INCORRECT() {
		return FORMAT_INCORRECT;
	}
	
	public String getIMPORT_SUCESSFULLY() {
		return IMPORT_SUCESSFULLY;
	}
	
	public String getFILE_NO_SAVED() {
		return FILE_NO_SAVED;
	}
	
	public String getFILE_SAVED() {
		return FILE_SAVED;
	}
	
	public String getFILE_EXIST() {
		return FILE_EXIST;
	}
	
}
