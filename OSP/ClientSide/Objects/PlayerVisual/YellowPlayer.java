package OSP.ClientSide.Objects.PlayerVisual;

import java.awt.Color;

import OSP.ClientSide.Objects.Location;
import OSP.ClientSide.Objects.Player;
import OSP.ClientSide.Objects.PlayersAbstractFactory;
import OSP.ClientSide.Objects.RedPlayerFactory;
import OSP.ClientSide.Objects.YellowPlayerFactory;

public class YellowPlayer extends Player {

	public YellowPlayer(String ID, Location l) {
		super(ID, l);
		// TODO Auto-generated constructor stub
	}

	@Override
	public PlayersAbstractFactory getPlayerFactory()
	{
		return new YellowPlayerFactory();
	}
	
	@Override
	public Color getPlayerColour()
	{
		return new Color(1f, 1f, 0f);
	}
}
