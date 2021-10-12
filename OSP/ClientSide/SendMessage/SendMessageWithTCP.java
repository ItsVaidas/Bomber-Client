package OSP.ClientSide.SendMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.Timer;

/*
 * Sending a message with 
 */
public class SendMessageWithTCP {
	
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String ip;
    private int port;
    private int ID;

    public void setup(String ip, int port) {
    	this.ip = ip;
    	this.port = port;
    }

    public String[] sendMessage(int channel, String... args) {
		try {
			clientSocket = new Socket(ip, port);
	        out = new PrintWriter(clientSocket.getOutputStream(), true);
	        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	        
	        out.println(channel);
	        
        	out.println(ID+"");
	        for (String arg : args)
	        	out.println(arg);
	        
	        String response = in.readLine();
	        
	        //Joining the server
	        if (channel == 0) {
	        	if (response == null) {
	    	        in.close();
	    	        out.close();
	    	        clientSocket.close();
	        		return new String[] {null};
	        	}
	        	ID = Integer.parseInt(response);
	    		System.out.println("Serverio sugeneruotas ID: " + ID);
	    		continueConnection();
	        }
	        //Updating information from server
	        if (channel == 2) {
	        	int status = Integer.parseInt(response);
	        	if (status == 0) {
	    	        in.close();
	    	        out.close();
	    	        clientSocket.close();
	        		return new String[] {"0"};
	        	}
	        	if (status == 1) {
	        		String timeLeft = in.readLine();
	        		String playerAmount = in.readLine();
	    	        in.close();
	    	        out.close();
	    	        clientSocket.close();
	        		return new String[] {"1", timeLeft, playerAmount};
	        	}
	        }
	        if (channel == 3) {
	        	int status = Integer.parseInt(response);
        		String map = in.readLine();
        		String players = in.readLine();
    	        in.close();
    	        out.close();
    	        clientSocket.close();
        		return new String[] {String.valueOf(status), map, players};
	        }

	        in.close();
	        out.close();
	        clientSocket.close();
	        return new String[] {response};
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
	
	private void continueConnection() {
		new Timer(50, e -> {
			sendMessage(1, ID+"");
        }).start();
	}
}
