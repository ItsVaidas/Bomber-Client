package OSP.ClientSide.Objects;
import java.util.ArrayList;
import java.util.List;

import OSP.ClientSide.Screen.GameScreen;


public class Invoker {
	
	private ArrayList<ICommand> commands = new ArrayList<>();
	/*List<Bomb> bombs;*/

	public boolean run(ICommand command, List<Bomb> bombs)
	{
		commands.add(command);
		command.execute(bombs);
		return true;
	}

	public boolean undo(List<Bomb> bombs) {
	    ICommand lastCommand = commands.get(commands.size() - 1);
        lastCommand.undo(bombs);
	    commands.remove(commands.size() - 1);
	    return true;
    }
	
	public boolean run(ICommand command, List<Bomb> bombs, GameScreen frame, List<Player> players, String map)
	{
		commands.add(command);
		command.execute(bombs, frame, players, map);
		return true;
	}
	
}
