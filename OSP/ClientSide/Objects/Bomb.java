package OSP.ClientSide.Objects;

import java.util.List;

import OSP.ClientSide.Screen.Explosion;
import OSP.ClientSide.Screen.GameScreen;

public class Bomb implements Cloneable{
	
	Player p;
	Location l;
	long explodeIn;

	public Bomb(Player p, Location l) {
		this.p = p;
		this.l = l;
		explodeIn = System.currentTimeMillis() + 1000 * 4;
	}
	
	public Bomb(Player p, Location l, Long explodeIn) {
		this.p = p;
		this.l = l;
		this.explodeIn = explodeIn;
	}

	public Location getLocation() {
		return this.l;
	}
	public void setLocation(Location ll) {
		this.l = ll;
	}
	public void setPlayer(Player pp) {
		this.p = pp;
	}
	public boolean isExploded() {
		return explodeIn < System.currentTimeMillis();
	}

	public void explode(GameScreen frame, List<Player> players, String map) {
		int currentX = l.X();
		int currentY = l.Y();
		frame.add(new Explosion(frame, currentX, currentY, p));
	}
	
	public Bomb makeCopyShallow() {
		try {
			return (Bomb) this.clone();
		}catch(CloneNotSupportedException ex){
		ex.printStackTrace();
		return this;
	    }
	}
	public Bomb makeCopyDeep() {
		try {
			Bomb copyBomb =  (Bomb) this.clone();
			copyBomb.setLocation(this.l.makeCopy());
			copyBomb.setPlayer(this.p.makeCopy());
			return copyBomb;
		}catch(CloneNotSupportedException ex){
		ex.printStackTrace();
		return this;
	    }
	}
}
