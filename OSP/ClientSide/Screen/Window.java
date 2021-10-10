package OSP.ClientSide.Screen;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;


import OSP.ClientSide.Enum.WallType;
import OSP.ClientSide.SendMessage.SendMessageWithTCP;

public class Window {
	
	JFrame frame;
	SendMessageWithTCP messenger;

	public Window(SendMessageWithTCP messenger) {
		this.messenger = messenger;
		
		frame = new JFrame("Bomber");
		
		frame.setPreferredSize(new Dimension(640, 640));
		frame.setMinimumSize(new Dimension(640, 640));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		setUpButtons();
	}
	
	public void setUpButtons() {
		addButton("Connect to the server", 160, 200, 320, e -> {
        	String[] response = messenger.sendMessage(0);
			frame.getContentPane().removeAll();
        	if (response[0] == null) {
				addText("Error connecting to server: " + response[1], 160, 200, 320);
        	} else
            	updateScreenByResponse();
			repaintFrame();
        });
	}
	
	private void addButton(String title, int x, int y, int width, ActionListener listener) {
		JButton b = new JButton(title);
		b.setBounds(x, y, width, 30);
		b.addActionListener(listener);
		frame.add(b);
	}

	private void updateScreenByResponse() {
		new Timer(50, new ActionListener() {
			int prevStatus = -1;
            public void actionPerformed(ActionEvent e) {
    			String[] response = messenger.sendMessage(2);
    			if (response[0].equals("0") && prevStatus != 0) {
    				prevStatus = 0;
    				frame.getContentPane().removeAll();
    				addText("Waiting for more players...", 160, 200, 320);
    				repaintFrame();
    				
    				frame.getContentPane().removeAll();
    				paintGame();
    			}
    			if (response[0].equals("1")) {
    				prevStatus = 1;
    				frame.getContentPane().removeAll();
    				String timeLeft = response[1];
    				String playerAmount = response[2];
    				addText("Waiting for more players... ("+playerAmount+"/4) ("+timeLeft+"s)", 160, 200, 320);
    				repaintFrame();
    			}
            }
            
        }).start();
	}
	
	String map = "2222222222200111100220111111022111111112211111111221111111122111111112201111110220011110022222222222";
	private void paintGame() {
		frame.setLayout(new GridLayout(10, 10));
		
		for (int i = 0; i < 10; i++) {
			
			for (int a = 0; a < 10; a++) {
				char type = map.charAt(i * 10 + a);
				switch (type) {
					case '2':
						addWall(WallType.INDISTRUCTABLE);
						break;
					case '1':
						addWall(WallType.DESTRUCTABLE);
						break;
					case '0':
						addWall(WallType.NONE);
						break;
				}
			}
		}
		repaintFrame();
	}
	
	private void addWall(WallType type) {
		ImageIcon img = null;
		switch (type) {
			case INDISTRUCTABLE:
				img = new ImageIcon(this.getClass().getResource("/wall.jpg"));
				break;
			case DESTRUCTABLE:
				img = new ImageIcon(this.getClass().getResource("/wall2.jpg"));
				break;
			case NONE:
				img = new ImageIcon(this.getClass().getResource("/none.jpg"));
				break;
		}
		JLabel label = new JLabel(img);
		label.setPreferredSize(new Dimension(64, 64));
		label.setMaximumSize(new Dimension(64, 64));
		frame.add(label);
		
	}

	private void addText(String text, int x, int y, int width) {
		JLabel b = new JLabel(text);
		b.setBounds(x, y, width, 30);
		frame.add(b);
	}
	
	private void repaintFrame() {
    	frame.revalidate();
    	frame.repaint();
	}
}
