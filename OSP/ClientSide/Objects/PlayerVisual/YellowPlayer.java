package OSP.ClientSide.Objects.PlayerVisual;

import java.awt.Color;

import OSP.ClientSide.Objects.Location;
import OSP.ClientSide.Objects.ObjectColor;
import OSP.ClientSide.Objects.Player;

public class YellowPlayer extends Player{
	static ObjectColor color = new Yellow();
	public YellowPlayer(String ID, Location l) {
		super(ID, l, color);
	}

}
