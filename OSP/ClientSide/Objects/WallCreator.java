package OSP.ClientSide.Objects;


public class WallCreator extends Creator {
	
	@Override
	public WallObject wallPaintFactory(char c)
	{
		switch (c) {
		case '0':
			return new Grass();
		case '1':
			return new DestructiveWall();
		case '2':
			return new IndistructableWall();
		default:
			return null;
		}
	}

}
