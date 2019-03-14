package se_hw3.Input;

import se_hw3.IOMode;
import se_hw3.LineStorage;
import se_hw3.Observer.PassLinesObserver;
import se_hw3.Output.FileOutputStrategy;

public class InputContext {

	private InputStrategy strategy;
	private PassLinesObserver observer;
	
	public InputContext(PassLinesObserver obs) {
		observer = obs;
	}
	
	public String readLine() {
		return strategy.readLine();
	}
	
	public void readLines() {
		strategy.readLines();
	}
	
	public void setContext(IOMode mode, String[] args) {
		
		switch (mode) {
			case Console:
				strategy = new ConsoleInputStrategy(args, observer);
				break;
			case File:
				strategy = new FileInputStrategy(args,observer);
				break;
			case Interactive:
				strategy = new InteractiveInputStrategy(observer);
				break;
		}
	
	}
	
}
