package OSP.ClientSide.Objects;

import java.util.List;

import OSP.ClientSide.Objects.PlayerVisual.Red;

import java.awt.*;

public abstract class Player {

	Location l;
	String ID;
	boolean isDead;
	ObjectColor color;
	public Player(String ID, Location l, ObjectColor c) {
		this.ID = ID;
		this.l = l;
		this.isDead = false;
		this.color = c;
	
	}


	public Location getLocation() {
		return this.l;
	}
	
	public String getID() {
		return this.ID;
	}
	
	public ObjectColor getObjectColor() {
		return this.color;
	}

	public boolean isDead() {
		return isDead;
	}
	
	public void died() {
		isDead = true;
	}
	

	public boolean moveUp(String map, List<Bomb> bombs) {
		int y = this.l.Y();
		int x = this.l.X();
		char typeUp = map.charAt((y - 1) * 10 + x);
		if (typeUp == '0' && !isThereABomb(x, y-1, bombs)) {
			this.l.relocate(x, y-1);
			return true;
		}
		return false;
	}

	public boolean moveLeft(String map, List<Bomb> bombs) {
		int y = this.l.Y();
		int x = this.l.X();
		char typeUp = map.charAt(y * 10 + x - 1);
		if (typeUp == '0' && !isThereABomb(x-1, y, bombs)) {
			this.l.relocate(x-1, y);
			return true;
		}
		return false;
	}

	public boolean moveDown(String map, List<Bomb> bombs) {
		int y = this.l.Y();
		int x = this.l.X();
		char typeUp = map.charAt((y + 1) * 10 + x);
		if (typeUp == '0' && !isThereABomb(x, y+1, bombs)) {
			this.l.relocate(x, y+1);
			return true;
		}
		return false;
	}

	public boolean moveRight(String map, List<Bomb> bombs) {
		int y = this.l.Y();
		int x = this.l.X();
		char typeUp = map.charAt(y * 10 + x + 1);
		if (typeUp == '0' && !isThereABomb(x+1, y, bombs)) {
			this.l.relocate(x+1, y);
			return true;
		}
		return false;
	}

	public boolean placeBomb(List<Bomb> bombs) {
		int y = this.l.Y();
		int x = this.l.X();
		if (!isThereABomb(x,y, bombs)) {
			bombs.add(new Bomb(this, new Location(x, y)));
			return true;
		}
		return false;
	}

	private boolean isThereABomb(int x, int y, List<Bomb> bombs) {
		for (Bomb b : bombs) {
			Location l = b.getLocation();
			if (l.X() == x && l.Y() == y)
				return true;
		}
		return false;
	}
	
}
