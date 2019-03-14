package se_hw1.Output;

import se_hw1.IO.IOMode;

public class OutputStrategyFactory {
	
	public static OutputStrategy getStrategy(IOMode.Mode mode) {
		switch(mode) {
		case Console:
			return new ConsoleOutputStrategy();
		case File:
			return new FileOutputStrategy();
		default:
			return null;
		}
	}
	
}
