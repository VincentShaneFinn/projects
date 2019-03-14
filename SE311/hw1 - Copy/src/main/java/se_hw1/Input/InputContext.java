package se_hw1.Input;

import se_hw1.LineStorage;
import se_hw1.IO.IOMode;

public class InputContext {

	private InputStrategy strategy;
	private IOMode.Mode currentInputMode;
	
	public InputContext() {
		strategy = new ConsoleInputStrategy();
		currentInputMode = IOMode.getDefaultMode();
	}
	
	public String readLine() {
		return strategy.readLine();
	}
	
	public LineStorage readLines() {
		return strategy.readLines();
	}
	
	// InputStrategyFactory can return null if not provided a correct mode
	// we protect against this in Master Control, but we could protect against coder error
	// by checking for null here and returning a bool that it failed or was successful
	public void setContext(IOMode.Mode _mode) {
		currentInputMode = _mode;
		setContext(InputStrategyFactory.getStrategy(currentInputMode));
	}
	
	private void setContext(InputStrategy _inputStrategy){
		strategy = _inputStrategy;
	}
}
