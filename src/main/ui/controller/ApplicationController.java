package main.ui.controller;

import java.util.HashMap;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JOptionPane;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fazecast.jSerialComm.SerialPort;

import main.ui.TextInternalizatorController;
import main.utils.Constants;

public class ApplicationController {
    private final static Logger LOGGER = Logger.getLogger("ApplicationController");
	
	private SerialPort[] ports;
	private SerialPort activePort;
	private int status = Constants.STATUS_DISCONNECTED;
	private HashMap<String,String> keyValues = new HashMap<String, String>();
	private HashMap<String,String> keyValuesPersisted = new HashMap<String, String>();
	private TextInternalizatorController internalizator;	
	
	public ApplicationController(TextInternalizatorController internalizator) {
		super();
		initSetup();
		this.internalizator = internalizator;
		this.ports = SerialPort.getCommPorts();
        try {
			Handler fileHandler = new FileHandler("./arduinoMacroController.log", true);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            LOGGER.addHandler(fileHandler);
            fileHandler.setLevel(Level.ALL);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}

	}

	private void initSetup() {
		keyValues.put("0", "");
		keyValues.put("1", "");
		keyValues.put("2", "");
		keyValues.put("3", "");
		keyValues.put("4", "");
		keyValues.put("5", "");
		keyValues.put("R", "0");
		keyValues.put("G", "0");
		keyValues.put("B", "0");
	}

	public SerialPort[] getPorts() {
		this.ports = SerialPort.getCommPorts();
		return ports;
	}

	public void setPorts(SerialPort[] ports) {
		this.ports = ports;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	// Connecto to port and try to get default configuration
	public int connect(String portName) {
	    LOGGER.log(Level.INFO, "Connecting to port: " + portName + "...");
		for (SerialPort port: this.ports) {
			if(port.getSystemPortName().equals(portName)) {
				activePort = SerialPort.getCommPort(portName);
				this.status = Constants.STATUS_CONNECTED;
				getArduinoConfiguration();
			    LOGGER.log(Level.INFO, "Connection successful");
			    return this.status;
			}
		}
	    LOGGER.log(Level.INFO, "Connection fail");
		return this.status;
	}
	
	// Change the application to a Disconnected state
	public void disconnect() {
	    LOGGER.log(Level.INFO, "Disconnected from port: " + this.activePort.getSystemPortName());
		this.status = Constants.STATUS_DISCONNECTED;
		this.activePort = null;
	}
	
	// Send the new configuration to the Arduino
	public boolean saveConfiguration(HashMap<String, String> keys, boolean persist) {
		boolean status = true;
		if(this.status != Constants.STATUS_DISCONNECTED && this.activePort != null) {
			activePort.openPort();
			try {
			   byte[] writeBuffer = generateConfigurationByteArray(keys, persist);
			   if(writeBuffer == null) {
				   return false;
			   }
			   activePort.writeBytes(writeBuffer, writeBuffer.length);
			} catch (Exception e) { 
				status = false;
				LOGGER.log(Level.SEVERE,"Error sending configuration to Arduino.",e); 
			} finally {
				activePort.closePort();
			}
			return status;
		}
		return false;
	}
	
	private byte[] generateConfigurationByteArray(HashMap<String,String> keys, boolean persist) {
		boolean maxLenght = keys.keySet().stream().anyMatch(key -> keys.get(key).length() > Constants.MAX_CHARACTERS);
		String writeBuffer = "";
		Set<String> keysList = keys.keySet();
		for(String key: keysList) {
			if(persist) {
				if(!keys.get(key).equals(keyValuesPersisted.get(key))) {
					writeBuffer += key + "," + keys.get(key) + "%";
				}
			} else {
				if(!keys.get(key).equals(keyValues.get(key))) {
						writeBuffer += key + "," + keys.get(key) + "#";
				}
			}
		}
		LOGGER.log(Level.INFO, "Configuration sended: " + writeBuffer);
		if(maxLenght && persist) {
			int input = JOptionPane.showConfirmDialog(null, internalizator.getWARNING_SIZE_MESSAGE1()
					+ Constants.MAX_CHARACTERS + internalizator.getWARNING_SIZE_MESSAGE2(), 
					internalizator.getWARNING_SIZE(), JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if(input == JOptionPane.CANCEL_OPTION) {
				return null;
			}
		}
		// If the configuration is updated on the Arduino is also updated in local
		this.keyValues = keys;
		if(persist) {
			this.keyValuesPersisted = keys;
		}
		return writeBuffer.getBytes();
	}
	
	// Send a petition to the Arduino to get the Keys data
	private HashMap<String,String> getArduinoConfiguration() {
	    LOGGER.log(Level.INFO, "Getting configuration from serial...");
		if(this.activePort != null) {
			activePort.openPort();
			try {
				byte[] writeBuffer = {'&'};
				activePort.writeBytes(writeBuffer, writeBuffer.length);
				
				// Little pause to get the info
			    Thread.sleep(500);

			    byte[] readBuffer = new byte[activePort.bytesAvailable()];
			    activePort.readBytes(readBuffer, readBuffer.length, 0);
			    String result = new String(readBuffer, StandardCharsets.UTF_8);
			    if(convertToKeyValues(result, false)) {
			    	LOGGER.log(Level.INFO, "Configuration detected: " + result);
			    } else {
			    	LOGGER.log(Level.INFO, "Configuration not detected");
	    			JOptionPane.showConfirmDialog(null, internalizator.getWARNING_PORT_MESSAGE(),
	    					internalizator.getWARNING_PORT(), JOptionPane.CLOSED_OPTION, 
	    					JOptionPane.WARNING_MESSAGE);
			    }
			} catch (Exception e) { 
				LOGGER.log(Level.WARNING, "Exceptions getting configuration: ", e);
			} finally {
				activePort.closePort();
			}
		}
		return this.keyValues;
	}
	
	// Convert a String to a map with the Keys and values configured
	// The expression is: key,value#[...]key,value#&
	private boolean convertToKeyValues(String configuration, boolean fromFile){
		String keyValue = "";
		String keyConfigured = "";
		for(char c : configuration.toCharArray()) {
			if (c == '&') {
				// Detected last command
				this.status = Constants.STATUS_CONNECTED_CONFIGURED;
				return true;
			}
			if(keyConfigured.equals("")) {
				keyConfigured = "" + c;
			} else {
				if(c != ',' && c != '&' && c != '#') {
					keyValue += c;
				} else if (c == '#'){
					this.keyValues.put(keyConfigured,keyValue);
					// If data come from arduino is saved as persisted
					if(!fromFile) {
						this.keyValuesPersisted.put(keyConfigured,keyValue);
					}
					// Reset variables for other key
					keyConfigured = "";
					keyValue = "";
				}  
			}
		}
		return false;
	}
	
	public HashMap<String,String> getKeyConfiguration(){
		return this.keyValues;
	}

	public void restoreValues() {
		this.keyValues = new HashMap<String, String>();
	}

	public void exportConfiguration(File fileToSave, HashMap<String, String> conf) throws IOException {
		FileWriter save = new FileWriter(fileToSave);
		String writeBuffer = "";
		Set<String> keysList = conf.keySet();
		for(String key: keysList) {
			writeBuffer += key + "," + conf.get(key) + "#";
		}
		save.write(writeBuffer);
		save.close();		
	}

	public HashMap<String, String> importConfiguration(File fileToOpen) throws IOException {
		HashMap<String, String> result = null;
        String fileReaded = Files.readString(Paths.get(fileToOpen.getAbsolutePath()));
        if(fileReaded.matches(Constants.FILE_PATTERN)) {
    		result = new HashMap<String, String>();
	        String[] pairs = fileReaded.split("#");
	        for(String pair : pairs) {
	        	String[] keyValue = pair.split(",");
	        	result.put(keyValue[0], keyValue[1]);
	        }
        }

		return result;
	}
	
}
