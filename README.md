# ArduinoMacroController

Little java application to configure an Arduino with a custom sketch (linked here when uploaded) for use as a macro keyboard with RGB illumination.

## Keys avaiable:
There are 2 modes of use:
- Raw text: Up to 14 characteres (For EEPROM saving, using more only work in "local" mode).
- Special keys: This keys are entered as text in the configurator (if you press the key in the filed the text will be written). Only work 1 Special key for key configured. If more than one Special key or a Special key with other character is written in the field, the data will be send as Raw text. (This is due to the limitation of Strings array parser in Arduino). Avaiable special keys are:
  - **Space bar**: "space" or "".
  - **Enter**: "enter" or "return".
  - **Right arrow**: "rarrow".
  - **Left arrow**: "larrow".
  - **Up arrow**: "uarrow".
  - **Down arrow**: "darrow".
  - **F1-F24**: "f1", "f2", etc.
  
## Keys blocked:
This keys cant be used on the configurator because are used to parse the data sended to the Arduino: **"#", "&", "%" and ","**.


## Settings for Eclipse:

Folder "resource" must be configured as a source folder in the Build Path.

### External library needed: 

jSerialComm-2.6.2.jar: https://fazecast.github.io/jSerialComm/
