package OSP.ClientSide.Objects;

import java.util.List;

import OSP.ClientSide.Objects.PlayerVisual.Red;

import java.awt.*;

public class Player implements Cloneable{

	Location l;
	String ID;
	boolean isDead;
	ObjectColor color;
	int health = 1;
	int power = 1;
	int speed = 1;
	int damage = 1;
	int level = 1;
	
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
	
	public Player makeCopy() {
		try {
			return (Player) super.clone();
		}catch(CloneNotSupportedException ex){
		ex.printStackTrace();
		return this;
	    }
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

	public boolean removeBomb(List<Bomb> bombs) {
		int y = this.l.Y();
		int x = this.l.X();
		if (isThereABomb(x,y, bombs)) {
				bombs.clear();
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
	
	public int getHealth() {
		return this.health;
	}
	
	public int getPower() {
		return this.power;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public void setHealth(int i) {
		this.health = i;
	}
	
	public void setPower(int i) {
		this.power = i;
	}
	
	public void setSpeed(int i) {
		this.speed = i;
	}
	
	public void setDamage(int i) {
		this.damage = i;
	}
	
}
