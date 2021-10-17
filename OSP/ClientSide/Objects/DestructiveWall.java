package OSP.ClientSide.Objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class DestructiveWall extends WallObject{
	
	public DestructiveWall()
	{
		try {
			super.setImage(ImageIO.read(this.getClass().getResource("/img/destructableWall.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
