package OSP.ClientSide.Screen;

import java.awt.*;
import javax.swing.*;

import OSP.ClientSide.SendMessage.SendMessageWithTCP;

@SuppressWarnings("serial")
public class BomberFrame extends JFrame {
	
	SendMessageWithTCP messenger;
	String ID;
	final String backgroundImage = "/img/background.jpg";

	public BomberFrame(String title, SendMessageWithTCP messenger) {
		super(title);
		this.messenger = messenger;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(652, 670));
		this.setMinimumSize(new Dimension(652, 670));
		this.setMaximumSize(new Dimension(652, 670));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		setDefaultBackground();

		this.pack();
		this.setVisible(true);

		
		if (askUser("Do you want to connect to the server?")) {
			String[] response = messenger.sendMessage(0);
			this.ID = response[0];
			this.add(new WaitScreen(this, ID, messenger));
		} else {
			System.exit(0);
		}
	}

    public void setDefaultBackground() {
    	JLabel background = new JLabel();
    	background.setIcon(new ImageIcon(this.getClass().getResource(this.backgroundImage)));
    	background.setLayout(new BorderLayout());
	    this.setContentPane(background);
	}

	private boolean askUser(String question) {
    	return JOptionPane.showConfirmDialog(null, question, "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
}
