package OSP.ClientSide.Objects;

import OSP.ClientSide.Objects.PlayerVisual.YellowBomb;
//import OSP.ClientSide.Objects.PlayerVisual.YellowPlayer;   --- nera tokio?? 
import OSP.ClientSide.Objects.Player;
import OSP.ClientSide.Objects.PlayerVisual.Yellow;

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
		Yellow y = new Yellow();
		return new Player(ID, l, y);   //yellowPlayer
	}
	
	@Override
	public int getBombStrength() {
		 return bombStrength;
	 }
}
