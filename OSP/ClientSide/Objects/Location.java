package OSP.ClientSide.Objects;

public class Location {
	
	private int x;
	private int y;

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int X() {
		return this.x;
	}
	
	public int Y() {
		return this.y;
	}
	
	public void relocate(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
