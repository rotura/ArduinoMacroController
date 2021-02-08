package main.utils.renderer;

import java.awt.Component;
import java.io.File;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class TemplateRenderer extends BasicComboBoxRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	  public Component getListCellRendererComponent(JList<?> list, Object value,
	      int index, boolean isSelected, boolean cellHasFocus) {
	    super.getListCellRendererComponent(list, value, index, isSelected,
	        cellHasFocus);
	    if (value != null) {
	      File file = (File) value;
	      setText(file.getName());
	    }
	    return this;
	  }
}
