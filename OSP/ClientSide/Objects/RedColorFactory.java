package OSP.ClientSide.Objects;

import OSP.ClientSide.Objects.PlayerVisual.RedBomb;
//import OSP.ClientSide.Objects.PlayerVisual.RedPlayer;   -- nera tokio ?
import OSP.ClientSide.Objects.ObjectColor;
import OSP.ClientSide.Objects.PlayerVisual.*;

public class RedColorFactory extends ColorsAbstractFactory{

	int bombStrength = 3;
	
	@Override
	public Bomb bombCreation(Player p, Location l, long explodeIn)
	{
		return new RedBomb(p, l, explodeIn);
	}
	
	@Override
	public Player playerCreation(String ID, Location l) {
		Red r = new Red();
		
		return new Player(ID, l, r);
	}

	@Override
	public int getBombStrength() {
		 return bombStrength;
	 }

		

}
