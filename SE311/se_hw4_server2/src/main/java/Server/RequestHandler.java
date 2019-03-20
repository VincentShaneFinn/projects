package Server;
import java.io.*;
import java.net.*;
import java.util.*; 
import java.util.Date;

public class RequestHandler extends Thread {
	
	private Socket clientSocket = null; 
	private ServerMain main;
	
	public RequestHandler(Socket _clientSocket) {
		clientSocket = _clientSocket;
	}
	
	public RequestHandler(Socket client, ServerMain main) {
		this.clientSocket = client;
		this.main = main;
	}

	public void run() {
		try {
			BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			String clientInputLine = "";
			while ((clientInputLine = inputFromClient.readLine()) != null) {
				main.storage.addEquation(clientInputLine);
			}
			
			System.out.println("Client Disconnected");
			
			inputFromClient.close();
			clientSocket.close();
		
		} catch (IOException e ) { 
			System.out.println("Client Disconnected");
			
			try {
				clientSocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
