package main.ui;

import javax.swing.*;

import com.fazecast.jSerialComm.SerialPort;

import java.awt.*; 
import java.util.Vector; 

// got this workaround from the following bug: 
//      http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4618607 
public class WideComboBox<E> extends JComboBox<SerialPort>{ 

	private static final long serialVersionUID = 1L;

	public WideComboBox() { 
    } 

    public WideComboBox(final SerialPort items[]){ 
        super(items); 
    } 

    public WideComboBox(Vector<SerialPort> items) { 
        super(items); 
    } 

        public WideComboBox(ComboBoxModel<SerialPort> aModel) { 
        super(aModel); 
    } 

    private boolean layingOut = false; 

    public void doLayout(){ 
        try{ 
            layingOut = true; 
                super.doLayout(); 
        }finally{ 
            layingOut = false; 
        } 
    } 

    public Dimension getSize(){ 
        Dimension dim = super.getSize(); 
        if(!layingOut) 
            dim.width = Math.max(dim.width, getPreferredSize().width); 
        return dim; 
    } 
}
