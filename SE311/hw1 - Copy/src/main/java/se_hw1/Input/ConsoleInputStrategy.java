package se_hw1.Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import se_hw1.LineStorage;

public class ConsoleInputStrategy implements InputStrategy {

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
	
	public LineStorage readLines() {
		LineStorage lines = new LineStorage();
		
		System.out.println("Type your sentences hit enter to start a new sentence. Type $end to stop");
		String line = "";
		
		while (true) {
			line = readLine();
			if(!line.equals("$end")) {			
				lines.addLine(line);
			}
			else {
				break;
			}
		}
		
		return lines;
	}

}
