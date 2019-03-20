package Server;
/**
 * SE 311 Lab 
 * 
 * <p>
 * This program allows information on cities to be entered through a GUI window and
 * adds it to a list, and displays the city list in a second GUI window.
 * </p>
 * @version 1.0
 *
 */

import java.net.*;
import java.io.*;
import java.util.Date;

public class ServerMain {
	
    public static void main(String args[]) throws Exception {
    	
    	ServerSocket server = new ServerSocket(1247);
    	ServerMain main = new ServerMain();
    	
    	System.out.println("Server Started");
    	
    	InputThread it = new InputThread(main);
		it.start();
    	
    	while(true) {
    		Socket client = server.accept();
        	System.out.println("Client Connected");
    		
    		RequestHandler rh = new RequestHandler(client, main);
    		rh.start();
    	}
    	
    }	
    
    public EquationStorage storage = new EquationStorage();
	
}
