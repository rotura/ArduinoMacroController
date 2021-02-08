package main.utils.renderer;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import com.fazecast.jSerialComm.SerialPort;

public class PortRenderer extends BasicComboBoxRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	  public Component getListCellRendererComponent(JList<?> list, Object value,
	      int index, boolean isSelected, boolean cellHasFocus) {
	    super.getListCellRendererComponent(list, value, index, isSelected,
	        cellHasFocus);
	    if (value != null) {
	      SerialPort port = (SerialPort) value;
	      setText(port.getSystemPortName() + " - " + port.toString());
	    }
	    return this;
	  }
	}
