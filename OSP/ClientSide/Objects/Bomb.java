package OSP.ClientSide.Objects;

import java.util.List;

import OSP.ClientSide.Screen.Explosion;
import OSP.ClientSide.Screen.GameScreen;

public class Bomb {
	
	Player p;
	Location l;
	long explodeIn;

	public Bomb(Player p, Location l) {
		this.p = p;
		this.l = l;
		explodeIn = System.currentTimeMillis() + 1000 * 4;
	}
	
	public Location getLocation() {
		return this.l;
	}
	
	public boolean isExploded() {
		return explodeIn < System.currentTimeMillis();
	}

	public void explode(GameScreen frame, List<Player> players, String map) {
		int x = l.X();
		int y = l.Y();
		frame.add(new Explosion(frame, x, y, p));
		
		//Handle explosion, kill players, remove wall, spawn other stuff, etc
	}
}
