package OSP.ClientSide.Screen;

import java.util.List;

import OSP.ClientSide.Objects.Bomb;
import OSP.ClientSide.Objects.ColorCreator;
import OSP.ClientSide.Objects.DefaultColorCreator;
import OSP.ClientSide.Objects.DefaultWallCreator;
import OSP.ClientSide.Objects.Invoker;
import OSP.ClientSide.Objects.ObjectColor;
import OSP.ClientSide.Objects.Player;
import OSP.ClientSide.Objects.PowerUp;
import OSP.ClientSide.Objects.WallCreator;

public class GameScreenVariables {
	public static List<PowerUp> powerUps;
	public static WallCreator wallCreator = new DefaultWallCreator();
	public static ColorCreator colorCreator = new DefaultColorCreator();
	/* Generated from a response from server */
	public static String map;
	public static List<Player> players;
	public static List<Bomb> bombs;
	public static Invoker invoker = new Invoker();
	public static ObjectColor objectColor;
	
	public static final int GRID_HEIGHT = 10;
	public static final int GRID_WIDTH = 10;

}
