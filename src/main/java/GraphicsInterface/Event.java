package GraphicsInterface;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public abstract class Event implements ActionListener {
	
		public Event() {}
		
		public abstract void actionPerformed(ActionEvent e);		
	
}


