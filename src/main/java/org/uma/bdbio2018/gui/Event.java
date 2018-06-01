package org.uma.bdbio2018.gui;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
* @author Elena Espinosa Garcia
*/

public abstract class Event implements ActionListener {
	
		public Event() {}
		
		public abstract void actionPerformed(ActionEvent e);		
	
}


