package se_hw3.Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import se_hw3.LineStorage;
import se_hw3.Observer.PassLinesObserver;

public class InteractiveInputStrategy extends InputStrategy {
	
	public InteractiveInputStrategy(PassLinesObserver obs) {
		super(obs);
	}

	public String readLine() {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		 
		String line = null;
		
		while (line == null) {
			try {
				line = reader.readLine();
			} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		return line;
	}
	
	public void readLines() {
		LineStorage lines = new LineStorage();
		
		while (true) {
			System.out.print("Add (a), Delete (d), Print (p), Quit (q): ");
			String line = "";
			
			line = readLine();
			if(line.equals("a")) {
				System.out.print("> ");
				line = readLine();
				lines.addLine(line);
			}
			else if(line.equals("p")) {		
				this.callFinishedReadingEvent(lines);
			}
			else if(line.equals("d")) {
				System.out.print("> ");
				line = readLine();
				lines.deleteLine(line);
			}
			else if(line.equals("q")) {
				System.exit(0);
			}
		}
	}
	
}
