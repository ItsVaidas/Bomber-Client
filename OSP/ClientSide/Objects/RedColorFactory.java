package OSP.ClientSide.Objects;

import OSP.ClientSide.Objects.PlayerVisual.RedBomb;
import OSP.ClientSide.Objects.PlayerVisual.RedPlayer; 


public class RedColorFactory extends ColorsAbstractFactory{

	int bombStrength = 3;
	
	@Override
	public Bomb bombCreation(Player p, Location l, long explodeIn)
	{
		return new RedBomb(p, l, explodeIn);
	}
	
	@Override
	public Player playerCreation(String ID, Location l) {
		return new RedPlayer(ID, l);
	}

	@Override
	public int getBombStrength() {
		 return bombStrength;
	 }

}
