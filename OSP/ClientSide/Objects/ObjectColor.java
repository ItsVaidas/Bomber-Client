package OSP.ClientSide.Objects;
import java.awt.Color;

public abstract class ObjectColor {
	public ObjectColor() {}
	abstract public Color getColor();
	abstract public ColorsAbstractFactory getColorFactory();

}
