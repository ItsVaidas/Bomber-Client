package OSP.ClientSide.Screen;

import OSP.ClientSide.Enum.Colors;
import OSP.ClientSide.Objects.Location;

public abstract class PlayerTemplate{
	public final void paintProcess(String[] l, String[] w) {
		if(isRed()) {
			loadRed();
		}
		else {
			loadYellow();
		}
		addPlayer(l, w);
	}
	 protected void loadRed() {
		 GameScreenVariables.objectColor = GameScreenVariables.colorCreator.colorCretion(Colors.RED);
	 }
	 
	 protected void loadYellow() {
		 GameScreenVariables.objectColor = GameScreenVariables.colorCreator.colorCretion(Colors.YELLOW);
	 }
	 
	 protected void addPlayer(String[] l, String[] w) {
		 GameScreenVariables.players.add(GameScreenVariables.objectColor.getColorFactory().playerCreation(w[0], new Location(Integer.valueOf(l[0]), Integer.valueOf(l[1]))));
	 }
	 
	public abstract boolean isRed();
}
