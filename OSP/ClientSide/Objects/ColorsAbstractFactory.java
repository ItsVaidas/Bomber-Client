package OSP.ClientSide.Objects;

public abstract class ColorsAbstractFactory {
	abstract public Bomb bombCreation(Player p, Location l, long explodeIn);
	abstract public Player playerCreation(String ID, Location l);
	public abstract int getBombStrength();
}
