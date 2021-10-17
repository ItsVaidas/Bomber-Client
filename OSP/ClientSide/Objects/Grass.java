package OSP.ClientSide.Objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Grass extends WallObject{
	public Grass()
	{
		try {
			super.setImage(ImageIO.read(this.getClass().getResource("/img/grass.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
