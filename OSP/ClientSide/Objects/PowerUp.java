package OSP.ClientSide.Objects;

public class PowerUp {
	Location location;
	int type;
	
	public PowerUp(Location location, int type) {
		this.location = location;
		this.type = type;
	}
	
	public int getType() {
		return type;
	}
	
	public Location getLocation() {
		return location;
	}
	
}
