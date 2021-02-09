# ArduinoMacroController

Little java application to configure an Arduino with a custom sketch (https://github.com/rotura/ArduinoMacroKeyboard) throw the serial port for use as a mini macro keyboard with RGB illumination.
![GUI of the app](https://github.com/rotura/ArduinoMacroController/blob/main/gitImages/app_interface.jpeg);

## Manual
Steps:
1. Connect the Arduino to the PC and charge the sketch if is not charged.
1. Open the application, select the COM port of the Arduino, select the number of keys to configure and "Connect".
1. Modify the keys and RGB color and test it with "Test".
1. When you are done, press "Save" the burn the configuration on the Arduino's EEPROM and persist it after reboot.
1. You can export the actual configuration or import one in the "Options" menu.

### Action buttons
- **Test**: Will send the data to the Arduino but will be lost when the Arduino restart.
- **Save**: Will send the data to the Arduino and the Arduino will save it in the EEPROM. This configuration persist over restart.

### Configuration management
You can export a ".arconf" file with the App actual configuration (not Arduino saved configuration) with the menu "Options -> Export".
You can import any ".arconf" file with a valid format that will be charged on the App (you need to "Test" or "Save" it after charged).
If you have an "arconf" folder with valid files in the same folder than the App, all valid templates will be charged on a selector.

### Keys available:
The Keyboard Layout is US. Some special characteres like "ñ" can't be used.
- **Raw text**: Up to 19 characters (For EEPROM saving, using more only store the data on the variables).
- **Special keys**: This keys are entered as text in the key's fields (if you press the key in the field the correspond text will be written). Only work 1 Special key for key configured. If more than one Special key or a Special key with other character is written in the field, the data will be send but the Arduino will catch only the first Special key and ignore the rest of the data. Available special keys are:
  - **Space bar**: "space" or "". Pressing it will write the command in the field.
  - **Enter**: "enter" or "return". Pressing it will write the command in the field.
  - **Right arrow**: "rarrow". Pressing it will write the command in the field.
  - **Left arrow**: "larrow". Pressing it will write the command in the field.
  - **Up arrow**: "uarrow". Pressing it will write the command in the field.
  - **Down arrow**: "darrow". Pressing it will write the command in the field.
  - **Esc**: "esc". Pressing it will write the command in the field.
  - **Control**: "ctrl". Right click to open context menu.
  - **Shift**: "shift". Right click to open context menu.
  - **Alt**: "alt". Right click to open context menu.
  - **Windows or Cmd(Mac)**: "gui". Right click to open context menu.
  - **Tab**: "tab". Right click to open context menu.
  - **Mute volume**: "mute". Right click to open context menu.
  - **Increase volume**: "volup". Right click to open context menu.
  - **Decrease volume**: "volumedown".  Right click to open context menu.
  - **F1-F24**: "F1", "F2", etc. Only upper case work. Lower case will send it as Raw text. Pressing it will write the command in the field. Keys from F13 to F24 could not work in Windows.
- **Combinations**: Combination of keys pressed. All **Special keys** can be combined with single keys or between them using "+" as separator. Example: "ctrl+shift+t", "alt+tab", "ctrl+alt+F1".

Backspace key is used to erase the configured keys.  
  
### Keys blocked:
This keys can't be used on the key's fields because are used to parse the data sent to the Arduino: **"#", "&", "%" and ","**.

Key **"+"** can't be use as a single key. It's used to made **"Combinations"**.

### RGB led:
The RGB led is configured as a combination of the R, G and B channels.
To turn off the led configure each channel as "0".
To dim the led, scale down the values of each channel equally.


## Settings for Eclipse:

Folder "resource" must be configured as a source folder in the Build Path.

### External library needed: 

jSerialComm-2.6.2.jar: https://fazecast.github.io/jSerialComm/

## Updates:
- **v1.3**: 
1. Added context menu over the text fields with the Special keys. Right click on the field open it, and selecting a key will write it on the field directly. Keys in the ContextMenu are media keys and modified keys.
1. Fixed bug with export file extension in the selector.
1. Fixed bug with template button and select. Not going disabled after disconnect.

- **v1.2**: 
1. Folder generation to all files. "./arconf" and "./arconf/log" will be generated if not exist.
1. Template ".conf" files change to ".arconf".
1. User Settings (Language and number of Keys) are now saved when the App is closed.
1. More Special keys added.
1. New combination of keys available:
	- Now "+" key is a blocked key and don't work alone.
	- Combinations commands are like "ctrl+shift+c".
	- Single character keys and Special keys can be used.
	
- **v1.1**: Now apart of Import and Export configuration, if an "arconf" folder is situated in the same folder than the executable, the app will charge all ".arconf" files and show it for direct import.

