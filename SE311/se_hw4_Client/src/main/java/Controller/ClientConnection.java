package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Model.Composite.EquationComponent;
import Model.Visitor.DisplayVisitor;
import Model.Visitor.SolveVisitor;

public class ClientConnection {
	
	private Socket socket;
	
	PrintStream outputToServer;
	
	String consoleInputLine = "";
	
	public ClientConnection() throws UnknownHostException, IOException {
		socket = new Socket("127.0.0.1", 1247);
		System.out.println("Connected To Server");
	
		outputToServer = new PrintStream(socket.getOutputStream());
	}
	
	public void sendEquation(EquationComponent equation) {
		
		//Send a string to the server for now
		DisplayVisitor dv = new DisplayVisitor();
		equation.accept(dv);
		String equationString = dv.getString();
		
		SolveVisitor sv = new SolveVisitor();
		equation.accept(sv);
		int result = sv.getResult();
		
		outputToServer.println(equationString + " = " + result);
	}
	
	public void closeConnection() throws IOException {
		System.out.println("Client Socket Closed");
		socket.close();
		outputToServer.close();
	}
	
}
