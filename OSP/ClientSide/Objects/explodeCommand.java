package OSP.ClientSide.Objects;

import java.util.List;

import OSP.ClientSide.Screen.GameScreen;

public class explodeCommand implements ICommand {

private Bomb bomb;
	
	public explodeCommand(Bomb bomb) {
		this.bomb = bomb;
	}
	
	public void execute(List<Bomb> bombs, GameScreen frame, List<Player> players, String map) {
		bomb.explode(frame, players, map);
	}
	
	public void undo(List<Bomb> bombs) {
		//
	}

	@Override
	public void execute(List<Bomb> bombs) {
		// TODO Auto-generated method stub
		
	}
}
