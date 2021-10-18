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

import OSP.ClientSide.Enum.Colors;
import OSP.ClientSide.Objects.Bomb;
import OSP.ClientSide.Objects.DefaultPlayerCreator;
import OSP.ClientSide.Objects.WallCreator;
import OSP.ClientSide.Objects.Location;
import OSP.ClientSide.Objects.Player;
import OSP.ClientSide.Objects.PlayerCreator;
import OSP.ClientSide.Objects.WallObject;
import OSP.ClientSide.Objects.DefaultWallCreator;
import OSP.ClientSide.SendMessage.SendMessageWithTCP;

@SuppressWarnings("serial")
public class GameScreen extends JComponent implements KeyListener {
	
	int tempCounterForPlayer = 0;

	BomberFrame frame;
	SendMessageWithTCP messenger;
	String ID;
	final int GRID_HEIGHT = 10;
	final int GRID_WIDTH = 10;
	
	/* Generated from a response from server */
	String map;
	List<Player> players;
	List<Bomb> bombs;
	WallCreator wallCreator = new DefaultWallCreator();
	PlayerCreator playerCreator = new DefaultPlayerCreator();
	
	/* Timers to end */
	Timer updatePlayerPosition;
	Timer updateBombs;
	Timer updateMap;
	Timer checkForBombs;
	Timer checkForEnd;

	public GameScreen(BomberFrame frame, String ID, SendMessageWithTCP messenger) {
		this.frame = frame;
		this.messenger = messenger;
		this.ID = ID;
		
		this.frame.addKeyListener(this);
		this.getResponseFromServer();
		
		updatePlayerPositions();
		updateBombs();
		updateMap();
		checkForEnd();
		checkForBombExplosion();
	}

	private void checkForEnd() {
		checkForEnd = new Timer(50, (e) -> {
			 String[] response = messenger.sendMessage(20);
			 if (response[0] != null && response[0].equals("false")) {
				 END();
			 }
		 });
		checkForEnd.start();
	}

	private void END() {
		updatePlayerPosition.stop();
		updateBombs.stop();
		updateMap.stop();
		checkForBombs.stop();
		checkForEnd.stop();
		
		frame.add(new WaitScreen(frame, ID, messenger));
		frame.remove(this);
		
		this.frame.removeKeyListener(this);
		
    	frame.validate();
    	frame.repaint();
	}

	private void updateBombs() {
		updateBombs = new Timer(50, (e) -> {
			 boolean updateFrame = false;
			 String[] response = messenger.sendMessage(12);
			 if (response[0] == null || response[0].equals("null")) return;
			 for (String line : response[0].split("-")) {
				String[] w = line.split(" ");
				String[] l = w[0].split("/");
				int x = Integer.valueOf(l[0]);
				int y = Integer.valueOf(l[1]);
				boolean exist = false;
				for (Bomb b : this.bombs)
					if (b.getLocation().X() == x && b.getLocation().Y() == y)
						exist = true;
				if (!exist) {
					Player p = null;
					for (Player player : this.players)
						if (player.getID().equals(w[2]))
							p = player;
					
					this.bombs.add(p.getPlayerFactory().bombCreation(p, new Location(x, y), Long.valueOf(w[1])));
					updateFrame = true;
				}
				if (updateFrame)
					update();
			}
		 });
		updateBombs.start();
	}

	private void updateMap() {
		updateMap = new Timer(50, (e) -> {
			 String[] response = messenger.sendMessage(14);
			 if (response[0] == null || response[0].equals("null")) return;
			 if (!this.map.equals(response[0])) {
				 this.map = response[0];
				 update();
			 }
		 });
		updateMap.start();
	}

	private void updatePlayerPositions() {
		updatePlayerPosition = new Timer(50, (e) -> {
			 boolean updateFrame = false;
			 String[] response = messenger.sendMessage(10);
			 if (response[0] == null || response[0].equals("null")) return;
			 for (String line : response[0].split("-")) {
				String[] w = line.split(" ");
				String[] l = w[1].split("/");
				for (Player p : this.players)
					if (p.getID().equals(w[0]))
						if (w[2].equals("true"))
							p.died();
						else {
							Location location = p.getLocation();
							int playerX = location.X();
							int playerY = location.Y();
							int x = Integer.valueOf(l[0]);
							int y = Integer.valueOf(l[1]);
							if (playerX != x || playerY != y) {
								p.getLocation().relocate(x, y);
								updateFrame = true;
							}
						}
				if (updateFrame)
					update();
			}
		 });
		updatePlayerPosition.start();
	}

	private void checkForBombExplosion() {
		checkForBombs = new Timer(50, (e) -> {
			for (Bomb b : new ArrayList<>(bombs)) {
				if (b.isExploded()) {
					b.explode(this, players, map);
					bombs.remove(b);
				}
			}
		 });
		checkForBombs.start();
	}

	private void getResponseFromServer() {
		String[] response = messenger.sendMessage(3);
		if (!response[0].equals("3")) {
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
			if(tempCounterForPlayer==0) {
				this.players.add(playerCreator.playerCretion(w[0], new Location(Integer.valueOf(l[0]), Integer.valueOf(l[1])), Colors.YELLOW));			
				tempCounterForPlayer++;
			}
			else
			{
				this.players.add(playerCreator.playerCretion(w[0], new Location(Integer.valueOf(l[0]), Integer.valueOf(l[1])), Colors.RED));
			}
		}
	}

	@Override
    protected void paintComponent(Graphics g) {
		if (this.map == null) return;
    	super.paintComponent(g);
    	final Graphics2D g2d = (Graphics2D) g;
    	
    	//Paiting walls
    	for (int i = 0; i < GRID_HEIGHT; i++)
        	for (int j = 0; j < GRID_WIDTH; j++) {
        		int[] xy = getPosInGrid(i, j);
        		try {
					paintWall(map.charAt(i * 10 + j), g2d, xy[1], xy[0]);
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
    		if (!p.isDead())
    			paintPlayer(p, g2d);
    	}
    }
	
	private int[] getPosInGrid(int i, int j) {
		return new int[] {i * 64, j * 64};
	}
	
	private void paintWall(char c, Graphics2D g2d, int x, int y) throws IOException {
		WallObject wall = wallCreator.wallPaintFactory(c);
		g2d.drawImage(wall.img, x, y, this);
	}
	
    private void paintPlayer(Player player, Graphics g2d) {
		Location l = player.getLocation();
		int[] xy = getPosInGrid(l.Y(), l.X());
    	
		g2d.setColor(player.getPlayerColour());
		g2d.fillRect(xy[1] + 17, xy[0] + 17, 30, 30);
		if (player.getID().equals(this.ID)) {
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
			g2d.drawString("Y", xy[1] + 25, xy[0] + 40);
		}
    }
    
    private void paintBomb(Bomb b, Graphics g2d) throws IOException {
    	BufferedImage img = ImageIO.read(this.getClass().getResource("/img/bomb.png"));
    	Location l = b.getLocation();
    	int x = l.X();
    	int y = l.Y();

		int[] xy = getPosInGrid(y, x);
		
		g2d.drawImage(img, xy[1]+9, xy[0]+7, this);
    }
    
    private void update() {
		frame.repaint();
    }

	@Override
	public void keyTyped(KeyEvent e) {
		Player p = getSelfPlayer();
		if (p == null) return;
		switch (e.getKeyChar()) {
			case 'w':
				if (p.moveUp(this.map, this.bombs)) {
					update();
					messenger.sendMessage(11, p.getLocation().X()+"", p.getLocation().Y()+"");
				}
				break;
			case 'a':
				if (p.moveLeft(this.map, this.bombs)) {
					update();
					messenger.sendMessage(11, p.getLocation().X()+"", p.getLocation().Y()+"");
				}
				break;
			case 's':
				if (p.moveDown(this.map, this.bombs)) {
					update();
					messenger.sendMessage(11, p.getLocation().X()+"", p.getLocation().Y()+"");
				}
				break;
			case 'd':
				if (p.moveRight(this.map, this.bombs)) {
					update();
					messenger.sendMessage(11, p.getLocation().X()+"", p.getLocation().Y()+"");
				}
				break;
			case ' ':
				if (p.placeBomb(this.bombs)) {
					update();
					messenger.sendMessage(13, p.getLocation().X()+"", p.getLocation().Y()+"");
				}
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
