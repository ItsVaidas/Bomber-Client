package OSP.ClientSide.Objects;

import OSP.ClientSide.Objects.PlayerVisual.YellowBomb;
import OSP.ClientSide.Objects.PlayerVisual.YellowPlayer;

public class YellowColorFactory extends ColorsAbstractFactory {
	
	int bombStrength = 4;
	@Override
	public Bomb bombCreation(Player p, Location l, long explodeIn)
	{
		return new YellowBomb(p, l, explodeIn);
	}
	
	@Override
	public Player playerCreation(String ID, Location l)
	{
		return new YellowPlayer(ID, l);
	}
	
	@Override
	public int getBombStrength() {
		 return bombStrength;
	 }
}
