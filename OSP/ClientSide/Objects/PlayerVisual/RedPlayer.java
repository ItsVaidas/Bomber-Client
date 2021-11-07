package OSP.ClientSide.Objects.PlayerVisual;


import OSP.ClientSide.Objects.Location;
import OSP.ClientSide.Objects.ObjectColor;
import OSP.ClientSide.Objects.Player;

public class RedPlayer extends Player{
	static ObjectColor color = new Red();
	public RedPlayer(String ID, Location l) {
		super(ID, l, color);
	}

}
