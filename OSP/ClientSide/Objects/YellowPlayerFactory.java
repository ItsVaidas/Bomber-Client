package OSP.ClientSide.Objects;

public class YellowPlayerFactory extends PlayersAbstractFactory {
	int bombStrength = 4;
	@Override
	public Bomb bombCreation(Player p, Location l, long explodeIn)
	{
		return new Bomb(p, l, explodeIn);
	}
	
	public int getBombStrength() {
		 return bombStrength;
	 }
}
