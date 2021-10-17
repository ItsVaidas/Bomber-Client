package OSP.ClientSide.Objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class IndistructableWall extends WallObject {

	public IndistructableWall()
	{
		try {
			super.setImage(ImageIO.read(this.getClass().getResource("/img/indistructableWall.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
