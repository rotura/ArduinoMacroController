package main.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import main.ui.model.Key;
import main.utils.Constants;

public class ContexMenu extends JPopupMenu {
	private static final long serialVersionUID = 1L;
	List<JMenuItem> itemList;
    
    public ContexMenu(List<Key> values, JTextField parent) {
    	for(Key key: values) {
    		if(!key.getKey().equals(Constants.SEPARATOR)) {
	    		JMenuItem item = new JMenuItem();
	    		item.setText(key.getDisplayName());
	    		item.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent me) {
						parent.setText(parent.getText() + key.getKey());
					}
	    		});
	    		add(item);
    		} else {
    			add(new JSeparator());
    		}
    	}
    }
}
