package OSP.ClientSide.Objects;

import java.util.List;

import OSP.ClientSide.Screen.GameScreen;

public class PlaceBombCommand implements ICommand {
	
	private Player player;
	
	public PlaceBombCommand(Player player) {
		this.player = player;
	}
	
	public void execute(List<Bomb> bombs) {
		player.placeBomb(bombs);
	}
	
	public void undo(List<Bomb> bombs) {
		player.removeBomb(bombs);
	}
	
	public void execute(List<Bomb> bombs, GameScreen frame, List<Player> players, String map)
	{
		//
	}

	
}
