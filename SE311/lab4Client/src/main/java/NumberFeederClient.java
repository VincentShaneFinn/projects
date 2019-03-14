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

import java.io.*;
import java.net.*;

public class NumberFeederClient {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		Socket socket = new Socket("127.0.0.1", 1247);
		System.out.println("Connected To Server");
		
		BufferedReader inputFromConsole = new BufferedReader(new InputStreamReader(System.in));
		ObjectInputStream inputFromServer = new ObjectInputStream(socket.getInputStream());
		PrintStream outputToServer = new PrintStream(socket.getOutputStream());
		
		String consoleInputLine = "";
		
		System.out.println("Type quit to stop");
		
		//Run until the user types quit
		while(true) {
			System.out.println("Enter Integers, separated by spaces");
			consoleInputLine = inputFromConsole.readLine();
			
			if(consoleInputLine.equals("quit")) {
				break;
			}
			if(!isValidNumberArray(consoleInputLine)) {
				System.out.println("Invalid Input");
				continue;
			}
			
			//Send a string to the server for now
			outputToServer.println(consoleInputLine);
			
			//we do receive the Integer[] object from the server
			Integer[] oddNumbers = (Integer[]) inputFromServer.readObject();
			String numberLine = "";
			for (int i = 0; i < oddNumbers.length; i++) {
				numberLine += oddNumbers[i] + " ";
			}
			
			System.out.println("Count of odd numbers: " + numberLine.length());
			System.out.println("Odd Numbers: " + numberLine);
		}
		
		outputToServer.println("logout");
		System.out.println("Client Socket Closed");
		
		inputFromConsole.close();
		inputFromServer.close();
		socket.close();
    }
	
	public static boolean isValidNumberArray(String line) {
		String[] array = line.split(" ");
		for (int i = 0; i < array.length; i++) {
		    try { 
		        Integer.parseInt(array[i]); 
		    } catch(Exception e) { 
		        return false; 
		    }
		}
		return true;
	}
	
}
