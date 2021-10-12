package OSP.ClientSide.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import OSP.ClientSide.SendMessage.SendMessageWithTCP;

@SuppressWarnings("serial")
public class WaitScreen extends JComponent {
	
	BomberFrame frame;
	SendMessageWithTCP messenger;
	String ID;
	String text = "Waiting for more players...";
	String timeLeft;
	String currentPlayerAmount;
	
	public WaitScreen(BomberFrame frame, String ID, SendMessageWithTCP messenger) {
		this.frame = frame;
		this.messenger = messenger;
		this.ID = ID;
		if (ID == null) {
			this.text = "Wasn't able to connect to the server...";
			update();
			return;
		}
		
		//startGame();
		setupTimer();
	}
	
    private void setupTimer() {
		final Timer timer = new Timer(50, null);
		timer.addActionListener(new ActionListener() {

			int prevStatus = -1;
			@Override
			public void actionPerformed(ActionEvent e) {
    			String[] response = messenger.sendMessage(2);
    			if (response[0].equals("0") && prevStatus != 0) {
    				prevStatus = 0;
    				timeLeft = null;
    				currentPlayerAmount = null;
    				update();
    			}
    			if (response[0].equals("1")) {
    				prevStatus = 1;
    				timeLeft = response[1];
    				currentPlayerAmount = response[2];
    				update();
    			}
    			if (response[0].equals("2") && prevStatus != 2) {
    				prevStatus = 2;
    				startGame();
    				timer.stop();
    			}
			}
		});
		timer.start();
	}

	protected void startGame() {
		this.frame.add(new GameScreen(this.frame, this.ID, this.messenger));
		update();
		this.frame.remove(this);
	}

	@Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	final Graphics2D g2d = (Graphics2D) g;
    	
    	g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
    	g2d.setColor(Color.WHITE);
    	g2d.drawString(this.text, 190, 150);
    	
    	if (this.timeLeft != null && this.currentPlayerAmount != null) {
        	g2d.drawString("Time left: " + this.timeLeft + "s", 250, 600);

        	g.setFont(new Font("TimesRoman", Font.PLAIN, 45)); 
        	g2d.drawString(this.currentPlayerAmount + "/4", 280, 420);
    	}
    }
    
    private void update() {
    	this.frame.validate();
    	this.frame.repaint();
    }
    
}
