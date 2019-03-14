package se_hw1;

import se_hw1.IO.IOMode;
import se_hw1.Input.*;
import se_hw1.Output.*;

public class MasterControl {
	
	private InputContext inputContext = new InputContext();
	private OutputContext outputContext = new OutputContext();
	private LineStorage centralLineStorage;

	//MARK: Constructor

	private void initialize() {
		centralLineStorage = new LineStorage();
	}
	
	//MARK: Program Loop
	
	public void programLoop() {
		initialize();
		preProcessing();
		processing();
		postProcessing();
	}
	
	public void programLoop(String configFile) {
		Config.InputFile = configFile;
		centralLineStorage = new FileInputStrategy().readLines();
		processing();
		postProcessing();
	}
	
	
	//MARK: PreProcessing
	
	private void preProcessing() {
		chooseInputMode();
		readLines();
	}
	
	private void chooseInputMode() {
		print("Begin Reading Input...");
		inputContext.setContext(getModeFromConsole());
		printBreak();
	}
	
	private void readLines() {
		centralLineStorage = inputContext.readLines();
		printBreak();
	}
	
	//MARK: Processing
	
	private void processing() {
		// Circular Shift Complete
		
		// Alphabetize?
		
	}
	
	//MARK: PostProcessing
	
	private void postProcessing() {
		chooseOutputMode();
		writeLines();
	}
	
	private void chooseOutputMode() {
		print("Printing Output...");
		outputContext.setContext(getModeFromConsole());
		printBreak();
	}
	
	private void writeLines() {
		outputContext.writeLines(centralLineStorage);
	}
	
	//MARK: Helper Methods
	
	private IOMode.Mode getModeFromConsole(){
		
		IOMode.Mode modeToUse = null;
		while(modeToUse == null) {
			print("Type $Console or $File to set mode");
			modeToUse = IOMode.toMode(new ConsoleInputStrategy().readLine());
		}
		
		return modeToUse;
	}	
	
	private void print(String output) {
		System.out.println(output);
	}
	
	private void printBreak(){
		System.out.println("");
	}
	
}
