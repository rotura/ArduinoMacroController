package main.ui.listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import main.utils.Constants;

public class TextFieldListener extends KeyAdapter{

	private JTextField field;
	
	public TextFieldListener(JTextField field) {
		super();
		this.field = field;
	}

	@Override
	public void keyPressed(KeyEvent e) {
	      char c = e.getKeyChar();
	      if (c == ',' || c == '&' || c == '#' || c == '%') {
	         e.consume();  // ignore event
	      } else {
	    	  switch (e.getKeyCode()) {
	    	  	case KeyEvent.VK_SPACE:
	    	  		field.setText(field.getText() + Constants.KEY_SPACE);
	    	  		e.consume();
	    	  		break;
	    	  	case KeyEvent.VK_ENTER:
	    	  		field.setText(field.getText() + Constants.KEY_RETURN);
	    	  		e.consume();
	    	  		break;
	    	  	case KeyEvent.VK_KP_RIGHT,KeyEvent.VK_RIGHT :
	    	  		field.setText(field.getText() + Constants.KEY_R_ARROW);
	    	  		e.consume();
	    	  		break;
	    	  	case KeyEvent.VK_KP_LEFT,KeyEvent.VK_LEFT :
	    	  		field.setText(field.getText() + Constants.KEY_L_ARROW);
	    	  		e.consume();
	    	  		break;
	    	  	case KeyEvent.VK_KP_UP,KeyEvent.VK_UP:
	    	  		field.setText(field.getText() + Constants.KEY_U_ARROW);
	    	  		e.consume();
	    	  		break;
	    	  	case KeyEvent.VK_KP_DOWN,KeyEvent.VK_DOWN:
	    	  		field.setText(field.getText() + Constants.KEY_D_ARROW);
	    	  		e.consume();
	    	  		break;
	    	  }
	      }
	   }
	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
	      if (c == ',' || c == '&' || c == '#' || c == ' ' || c == '%') {
	         e.consume();  // ignore event
	      } else {
	    	  switch (e.getKeyCode()) {
	    	  	case KeyEvent.VK_SPACE:
	    	  		e.consume();
	    	  		break;
	    	  	case KeyEvent.VK_ENTER:
	    	  		e.consume();
	    	  		break;
	    	  	case KeyEvent.VK_KP_RIGHT,KeyEvent.VK_RIGHT :
	    	  		e.consume();
	    	  		break;
	    	  	case KeyEvent.VK_KP_LEFT,KeyEvent.VK_LEFT :
	    	  		e.consume();
	    	  		break;
	    	  	case KeyEvent.VK_KP_UP,KeyEvent.VK_UP:
	    	  		e.consume();
	    	  		break;
	    	  	case KeyEvent.VK_KP_DOWN,KeyEvent.VK_DOWN:
	    	  		e.consume();
	    	  		break;
	    	  }
	      }
	   }
	
	@Override
	public void keyReleased(KeyEvent e) {
		e.consume();  // ignore event
	}
}
