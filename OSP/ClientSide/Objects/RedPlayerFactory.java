package OSP.ClientSide.Objects;

public class RedPlayerFactory extends PlayersAbstractFactory{
	int bombStrength = 3;
	@Override
	public Bomb bombCreation(Player p, Location l, long explodeIn)
	{
		return new Bomb(p, l, explodeIn);
	}
	
	 public int getBombStrength() {
		 return bombStrength;
	 }

}
