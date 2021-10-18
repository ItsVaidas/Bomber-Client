package OSP.ClientSide.Objects;

public abstract class PlayersAbstractFactory {
	abstract public Bomb bombCreation(Player p, Location l, long explodeIn);
	public abstract int getBombStrength();
}
