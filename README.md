# ArduinoMacroController

Little java application to configure an Arduino with a custom sketch (linked here when uploaded) for use as a macro keyboard with RGB illumination.

## Manual
Steps:
1. Connect the Arduino to the PC and charge the sketch if is not charged.
1. Open the application, select the COM port of the Arduino, select the number of keys to configure and "Connect".
1. Modify the keys and RGB color and test it with "Test".
1. When you are done, press "Save" the burn the configuration on the Arduino's EEPROM and persist it after reboot.
1. You can export the actual configuration or import one in the "Options" menu.

### Keys available:
There are 2 modes of use:
- **Raw text**: Up to 14 characters (For EEPROM saving, using more only work in "local" mode).
- **Special keys**: This keys are entered as text in the key's fields (if you press the key in the field the correspond text will be written). Only work 1 Special key for key configured. If more than one Special key or a Special key with other character is written in the field, the data will be send but the Arduino will catch only the first Special key and ignore the rest of the data. (This is due to the limitation of Strings array parser in Arduino). Available special keys are:
  - **Space bar**: "space" or "".
  - **Enter**: "enter" or "return".
  - **Right arrow**: "rarrow".
  - **Left arrow**: "larrow".
  - **Up arrow**: "uarrow".
  - **Down arrow**: "darrow".
  - **F1-F24**: "F1", "F2", etc. Only upper case work. Lower case will send it as Raw text.
  
Combinations of keys like "crtl+t" are not allowed for now. Keys "Windows", "Cmd", "Tab", "Shift", "Control", "Alt" and the rest of non printable keys don't do nothing.

Backspace key is used to erase the configured keys.  
  
### Keys blocked:
This keys can't be used on the key's fields because are used to parse the data sent to the Arduino: **"#", "&", "%" and ","**.

### RGB led:
The RGB led is configured as a combination of the R, G and B channels.
To turn off the led configure each channel as "0".
To dim the led, scale down the values of each channel equally.


## Settings for Eclipse:

Folder "resource" must be configured as a source folder in the Build Path.

### External library needed: 

jSerialComm-2.6.2.jar: https://fazecast.github.io/jSerialComm/


