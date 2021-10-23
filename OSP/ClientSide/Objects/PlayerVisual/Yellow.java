package OSP.ClientSide.Objects.PlayerVisual;

import java.awt.Color;

import OSP.ClientSide.Objects.ObjectColor;
import OSP.ClientSide.Objects.ColorsAbstractFactory;
import OSP.ClientSide.Objects.YellowColorFactory;

public class Yellow extends ObjectColor {

	@Override
	public ColorsAbstractFactory getColorFactory()
	{
		return new YellowColorFactory();
	}
	
	@Override
	public Color getColor()
	{
		return new Color(1f, 1f, 0f);
	}
}
