package OSP.ClientSide.Objects;

import OSP.ClientSide.Enum.Colors;
import OSP.ClientSide.Objects.PlayerVisual.RedPlayer;
import OSP.ClientSide.Objects.PlayerVisual.YellowPlayer;

public class DefaultPlayerCreator extends PlayerCreator {
	
	@Override
	public Player playerCretion(String ID, Location l, Colors color)
	{
		switch(color) {
			case RED: 
				return new RedPlayer(ID, l);
			case YELLOW:
				return new YellowPlayer(ID, l);
		}
		return null;
				
	}
}
