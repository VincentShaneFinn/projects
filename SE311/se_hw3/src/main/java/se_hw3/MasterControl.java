package se_hw3;

import se_hw3.Input.*;
import se_hw3.Observer.PassLinesObserver;
import se_hw3.Output.*;

import java.util.Arrays;

public class MasterControl {
	
	public OutputContext outputContext = new OutputContext();
	public InputContext inputContext = new InputContext(new CircularShift(new Alphabetizer(outputContext)));

	//MARK: Main
	
	public static void main(String[] args) {
		MasterControl masterControl = new MasterControl();

		if(args.length > 0 ) {
			if(args[0].equals("console")) {
				String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
				masterControl.inputContext.setContext(IOMode.Console, newArgs);
				masterControl.outputContext.setContext(IOMode.Console, newArgs);
			}
			else if (args[0].equals("file")) {		
				String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
				masterControl.inputContext.setContext(IOMode.File, newArgs);
				masterControl.outputContext.setContext(IOMode.File, newArgs);
			}
			else {
				String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
				masterControl.inputContext.setContext(IOMode.Interactive, newArgs);
				masterControl.outputContext.setContext(IOMode.Interactive, newArgs);
			}
		}
		else {
			masterControl.inputContext.setContext(IOMode.Interactive, null);
			masterControl.outputContext.setContext(IOMode.Interactive, null);
		}
		
		masterControl.programLoop();
		
	}
	
	//MARK: Program Loop
	
	public void programLoop() {
		
		inputContext.readLines();	
	    
	}	
	
}
