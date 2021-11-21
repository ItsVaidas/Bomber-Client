package OSP.ClientSide.Objects;
import java.awt.Color;
import OSP.ClientSide.*;
import OSP.ClientSide.Screen.PlayerTemplate;

public abstract class ObjectColor extends PlayerTemplate {
	public ObjectColor() {}
	abstract public Color getColor();
	abstract public ColorsAbstractFactory getColorFactory();

}
