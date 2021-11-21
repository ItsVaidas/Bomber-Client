package OSP.ClientSide.Objects.PlayerVisual;

import java.awt.Color;

import OSP.ClientSide.Objects.ColorsAbstractFactory;
import OSP.ClientSide.Objects.ObjectColor;
import OSP.ClientSide.Objects.RedColorFactory;

public class Red extends ObjectColor {
	public Red() {}
	
	@Override
	public ColorsAbstractFactory getColorFactory()
	{
		return new RedColorFactory();
	}
	
	@Override
	public Color getColor()
	{
		return new Color(1f, 0f, 0f);
	}
	
	public boolean isRed() {
		return true;
	}
}
