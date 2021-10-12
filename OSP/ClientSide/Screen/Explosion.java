package OSP.ClientSide.Screen;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;

import javax.swing.JComponent;
import javax.swing.Timer;

import OSP.ClientSide.Objects.Player;

@SuppressWarnings("serial")
public class Explosion extends JComponent {

	int x;
	int y;
	Player p;
	
	public Explosion(GameScreen frame, int x, int y, Player p) {
		this.x = x;
		this.y = y;
		this.p = p;
		
		Timer t = new Timer(1000, (e) -> {
			frame.remove(this);
			frame.repaint();
		});
		t.setRepeats(false);
		t.start();
		paintComponent(frame.getGraphics());
	}
	
	@Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	final Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLUE);
		
    	for (int i = y - 3; i <= y + 3; i++) {
    		int[] xy = getPosInGrid(i, x);
    		g2d.fillRect(xy[1] + 4, xy[0] + 4, 48, 48);
    		System.out.println((xy[1] + 4) + " " + (xy[0] + 4));
    	}
		for (int j = x - 3; j <= x + 3; j++) {
    		int[] xy = getPosInGrid(y, j);
    		g2d.fillRect(xy[1] + 4, xy[0] + 4, 48, 48);
    		System.out.println((xy[1] + 4) + " " + (xy[0] + 4));
		}
		
	}
	
	private int[] getPosInGrid(int i, int j) {
		return new int[] {i * 64, j * 64};
	}
}
