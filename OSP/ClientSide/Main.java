package OSP.ClientSide;

import OSP.ClientSide.Screen.BomberFrame;
import OSP.ClientSide.SendMessage.SendMessageWithTCP;

public class Main {

	private static SendMessageWithTCP messenger;
	
	public static void main(String[] args) {
		setupMessenger();
		
		new BomberFrame("Bomber", messenger);
	}

	private static void setupMessenger() {
		messenger = new SendMessageWithTCP();
		//messenger.setup("94.244.101.113", 11111);
		messenger.setup("127.0.0.1", 11111);
	}
}
