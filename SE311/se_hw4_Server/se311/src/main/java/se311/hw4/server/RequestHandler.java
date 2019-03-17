package se311.hw4.server;
import java.io.*;
import java.net.*;
import java.util.*; 
import java.util.Date;

public class RequestHandler extends Thread {
	
	private Socket clientSocket = null; 
	
	public RequestHandler(Socket _clientSocket) {
		clientSocket = _clientSocket;
	}
	
	public void run() {
		try {
			BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			String clientInputLine = "";
			while ((clientInputLine = inputFromClient.readLine()) != null) {
				System.out.println("inputFromClient: " + clientInputLine);
			}
			
			System.out.println("Client Disconnected");
			
			inputFromClient.close();
			clientSocket.close();
		
		} catch (IOException e ) { e.printStackTrace();}
	}

}
