package main.ui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import main.ui.ContexMenu;
import main.ui.controller.ContexMenuController;

public class ContexMenuListener extends MouseAdapter{
	private JTextField parent;
	private ContexMenuController controller;
	
	public ContexMenuListener(ContexMenuController controller, JTextField parent)  {
		this.parent = parent;
		this.controller = controller;
	}
	    public void mousePressed(MouseEvent e) {
	        if (e.isPopupTrigger())
	            doPop(e);
	    }

	    public void mouseReleased(MouseEvent e) {
	        if (e.isPopupTrigger())
	            doPop(e);
	    }

	    private void doPop(MouseEvent e) {
	    	if(parent.isEnabled()) {
	    		ContexMenu menu = new ContexMenu(controller.getContextMenuFields(), parent);
	    		menu.show(e.getComponent(), e.getX(), e.getY());
	    	}
    	}
	}