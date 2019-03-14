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
			ObjectOutputStream outputToClient = new ObjectOutputStream(clientSocket.getOutputStream());
			
			String clientInputLine = "";
			while (true) {
				clientInputLine = inputFromClient.readLine();
				if (clientInputLine.equals("logout")) {
					break;
				}
				System.out.println("inputFromClient: " + clientInputLine);
				outputToClient.writeObject(getOddIntsFromString(clientInputLine));
			}
			
			System.out.println("Client Disconnected");
			
			inputFromClient.close();
			outputToClient.close();
			clientSocket.close();
		
		} catch (IOException e ) { e.printStackTrace();}
	}
	
	private Integer[] getOddIntsFromString(String line) {
		String[] stringArray = line.split(" ");
		List<Integer> intList = new ArrayList<Integer>();
		for(int i = 0;i < stringArray.length;i++)
		{
			int number = Integer.parseInt(stringArray[i]);
			if (number % 2 != 0) {
				intList.add(number);
			}
		}
		
		Integer[] intArray = intList.toArray(new Integer[intList.size()]);
		return intArray;
	}
}
