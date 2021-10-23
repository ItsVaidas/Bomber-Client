package OSP.ClientSide.Objects;

import OSP.ClientSide.Enum.Colors;
import OSP.ClientSide.Objects.PlayerVisual.Red;
import OSP.ClientSide.Objects.PlayerVisual.Yellow;

public class DefaultColorCreator extends ColorCreator {
	
	@Override
	public ObjectColor colorCretion(Colors color)
	{
		switch(color) {
			case RED: 
				return new Red();
			case YELLOW:
				return new Yellow();
		}
		return null;
				
	}
}
