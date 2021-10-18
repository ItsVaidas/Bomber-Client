package OSP.ClientSide.Objects.PlayerVisual;

import java.awt.Color;

import OSP.ClientSide.Objects.Location;
import OSP.ClientSide.Objects.Player;
import OSP.ClientSide.Objects.PlayersAbstractFactory;
import OSP.ClientSide.Objects.RedPlayerFactory;

public class RedPlayer extends Player {
	
	public RedPlayer(String ID, Location l) {
		super(ID, l);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public PlayersAbstractFactory getPlayerFactory()
	{
		return new RedPlayerFactory();
	}
	
	@Override
	public Color getPlayerColour()
	{
		return new Color(1f, 0f, 0f);
	}
	
}
