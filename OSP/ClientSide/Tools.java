package OSP.ClientSide;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Tools {
	
	public static void addButton(JFrame frame, String title, int x, int y, int width, ActionListener listener) {
		JButton b = new JButton(title);
		b.setBounds(x, y, width, 30);
		b.addActionListener(listener);
		frame.add(b);
	}

}
