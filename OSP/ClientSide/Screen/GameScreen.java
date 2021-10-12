package OSP.ClientSide.Screen;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.Timer;

import OSP.ClientSide.Objects.Bomb;
import OSP.ClientSide.Objects.Location;
import OSP.ClientSide.Objects.Player;
import OSP.ClientSide.SendMessage.SendMessageWithTCP;

@SuppressWarnings("serial")
public class GameScreen extends JComponent implements KeyListener {

	BomberFrame frame;
	SendMessageWithTCP messenger;
	String ID;
	final int GRID_HEIGHT = 10;
	final int GRID_WIDTH = 10;
	
	/* Generated from a response from server */
	String map;
	List<Player> players;
	List<Bomb> bombs;

	public GameScreen(BomberFrame frame, String ID, SendMessageWithTCP messenger) {
		this.frame = frame;
		this.messenger = messenger;
		this.ID = ID;
		
		this.frame.addKeyListener(this);
		this.getResponseFromServer();
		
		checkForBombExplosion();
	}

	private void getResponseFromServer() {
		String[] response = messenger.sendMessage(2);
		response = new String[] {"2",
				"2222222222200111100220111111022111001112211111111221111001122111111112201111110220011110022222222222",
				"6785 1/1-6988 1/8-7777 8/1-"+this.ID+" 8/8"};
		if (response[0] != "2") {
			frame.add(new WaitScreen(frame, ID, messenger));
			frame.remove(this);
			return;
		}
		this.map = response[1];
		this.players = new ArrayList<>();
		this.bombs = new ArrayList<>();
		for (String line : response[2].split("-")) {
			String[] w = line.split(" ");
			String[] l = w[1].split("/");
			this.players.add(new Player(w[0], new Location(Integer.valueOf(l[0]), Integer.valueOf(l[1]))));
		}
	}

	private void checkForBombExplosion() {
		 new Timer(50, (e) -> {
			for (Bomb b : new ArrayList<>(bombs)) {
				if (b.isExploded()) {
					b.explode(this, players, map);
					bombs.remove(b);
				}
			}
		 }).start();
	}

	@Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	final Graphics2D g2d = (Graphics2D) g;
    	
    	//Paiting walls
    	for (int i = 0; i < GRID_HEIGHT; i++)
        	for (int j = 0; j < GRID_WIDTH; j++) {
        		int[] xy = getPosInGrid(i, j);
        		try {
					paintWall(map.charAt(i * 10 + j), g2d, xy[0], xy[1]);
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
    	
    	//Paint bombs
    	for (Bomb b : this.bombs) {
    		try {
				paintBomb(b, g2d);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    	//Paint players in their position
    	for (Player p : this.players) {
    		paintPlayer(p, g2d);
    	}
    }
	
	private int[] getPosInGrid(int i, int j) {
		return new int[] {i * 64, j * 64};
	}
	
	private void paintWall(char c, Graphics2D g2d, int x, int y) throws IOException {
		BufferedImage img = null;
		switch (c) {
			case '0':
				img = ImageIO.read(this.getClass().getResource("/grass.png"));
				break;
			case '1':
				img = ImageIO.read(this.getClass().getResource("/destructableWall.png"));
				break;
			case '2':
				img = ImageIO.read(this.getClass().getResource("/indistructableWall.png"));
				break;
				
		}
		g2d.drawImage(img, x, y, this);
	}
	
    private void paintPlayer(Player player, Graphics g2d) {
		Location l = player.getLocation();
		int[] xy = getPosInGrid(l.Y(), l.X());
    	
		g2d.setColor(Color.RED);
		g2d.fillRect(xy[1] + 17, xy[0] + 17, 30, 30);
		if (player.getID().equals(this.ID)) {
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
			g2d.drawString("Y", xy[1] + 25, xy[0] + 40);
		}
    }
    
    private void paintBomb(Bomb b, Graphics g2d) throws IOException {
    	BufferedImage img = ImageIO.read(this.getClass().getResource("/bomb.png"));
    	Location l = b.getLocation();
    	int x = l.X();
    	int y = l.Y();

		int[] xy = getPosInGrid(y, x);
		g2d.drawImage(img, xy[1]+9, xy[0]+7, this);
    }
    
    private void update() {
		frame.validate();
		frame.repaint();
    }

	@Override
	public void keyTyped(KeyEvent e) {
		Player p = getSelfPlayer();
		if (p == null) return;
		switch (e.getKeyChar()) {
			case 'w':
				if (p.moveUp(this.map, this.bombs))
					update();
				break;
			case 'a':
				if (p.moveLeft(this.map, this.bombs))
					update();
				break;
			case 's':
				if (p.moveDown(this.map, this.bombs))
					update();
				break;
			case 'd':
				if (p.moveRight(this.map, this.bombs))
					update();
				break;
			case ' ':
				if (p.placeBomb(this.bombs))
					update();
				break;
		}
	}

	private Player getSelfPlayer() {
		for (Player p : this.players)
			if (p.getID().equals(this.ID) && !p.isDead())
				return p;
		return null;
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
