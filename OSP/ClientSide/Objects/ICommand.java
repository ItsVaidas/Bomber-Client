package OSP.ClientSide.Objects;

import java.util.List;

import OSP.ClientSide.Screen.GameScreen;

public interface ICommand {

	void execute( List<Bomb> bombs);
	
	void undo(List<Bomb> bombs );
	
	void execute(List<Bomb> bombs, GameScreen frame, List<Player> players, String map);
}
