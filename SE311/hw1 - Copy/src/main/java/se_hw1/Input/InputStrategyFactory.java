package se_hw1.Input;

import se_hw1.IO.IOMode;

public class InputStrategyFactory {

	public static InputStrategy getStrategy(IOMode.Mode mode) {
		switch(mode) {
		case Console:
			return new ConsoleInputStrategy();
		case File:
			return new FileInputStrategy();
		default:
			return null;
		}
	}
	
}
