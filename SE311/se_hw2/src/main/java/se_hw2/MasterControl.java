package se_hw2;

import PipeAndFilter.*;
import se_hw2.Input.*;
import se_hw2.Output.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MasterControl {
	
	String inputFile = "";
	String outputFile = "";
	String location = "";
	

	//MARK: Main
	
	//You can provide these parameters when launching the program
	//<inputFile> <outputFile> <location>
	//if the string you are at does not have a '.', treat it as a file
	//else assume this is the location
	public static void main(String[] args) {
		MasterControl masterControl = new MasterControl();
//		if(args.length > 0) {
//			masterControl.programLoop(args[0]);
//		}
//		else {
//			masterControl.programLoop();
//		}
		
		for (int i = 0; i < args.length; i++) {
			if(args[i].contains(".")) {
				if (masterControl.inputFile.equals("")) {
					masterControl.inputFile = args[i];
				}
				else if (masterControl.outputFile.equals("")) {
					masterControl.outputFile = args[i];
				}
			}
			else {
				masterControl.location = args[i];
			}
		}
		
		masterControl.programLoop();
		
	}
	
	//MARK: Program Loop
	
	public void programLoop() {
		
		//Setup the Pipes 
		
		Pipe pipeInput = new PipeImpl();
		pipeInput.put(inputFile);
		
		Pipe pipeOutput = new PipeImpl();
		
		//MAIN | INPUT FILTER
		
		InputContext inputContext = new InputContext(pipeInput, pipeOutput);
		Thread inputFilter = new Thread(inputContext);
		inputFilter.start();
		
		//If Location is Input, Pipe to UpperCaseConverter
		
		if(location.toLowerCase().equals("input")) {
			pipeInput = pipeOutput;
		    pipeOutput = new PipeImpl();
		     
		    UpperCaseConverter ucc = new UpperCaseConverter(pipeInput, pipeOutput, outputFile);
		    Thread uccFilter = new Thread(ucc);
		    uccFilter.start();
			
		    return;
		}
		
		//INPUT FILTER | CIRCULAR SHIFT
   
		pipeInput = pipeOutput;
	    pipeOutput = new PipeImpl();
	    
		CircularShift circularShift = new CircularShift(pipeInput, pipeOutput);
		Thread circularShiftFilter = new Thread(circularShift);
		circularShiftFilter.start();
		
		//If Location is CircularShift, Pipe to UpperCaseConverter
		
		if(location.toLowerCase().equals("circularshift")) {
	    
			pipeInput = pipeOutput;
		    pipeOutput = new PipeImpl();
		     
		    UpperCaseConverter ucc = new UpperCaseConverter(pipeInput, pipeOutput, outputFile);
		    Thread uccFilter = new Thread(ucc);
		    uccFilter.start();
		    
		    return;
		}
		
		//CIRCULARSHIFT | ALPHABETIZER
		
		pipeInput = pipeOutput;
	    pipeOutput = new PipeImpl();
	    
		Alphabetizer alphabetizer = new Alphabetizer(pipeInput, pipeOutput);
		Thread alphabetizerFilter = new Thread(alphabetizer);
		alphabetizerFilter.start();
		
		if(location.toLowerCase().equals("alphabetizer")) {
		    
			pipeInput = pipeOutput;
		    pipeOutput = new PipeImpl();
		     
		    UpperCaseConverter ucc = new UpperCaseConverter(pipeInput, pipeOutput, outputFile);
		    Thread uccFilter = new Thread(ucc);
		    uccFilter.start();
		    
		    return;
		}
		
		//ALPHABETIZER | OUTPUTCONTEXT
		
		pipeInput = pipeOutput;
	    pipeOutput = new PipeImpl();
	     
	    OutputContext outputContext = new OutputContext(pipeInput, pipeOutput, outputFile);
	    Thread outputContextFilter = new Thread(outputContext);
	    outputContextFilter.start();
	    
	}
	
	//MARK: Helper Methods
	
	private void print(String output) {
		System.out.println(output);
	}
	
	private void printBreak(){
		System.out.println("");
	}
	
}
