package se_hw1.Output;

import se_hw1.LineStorage;
import se_hw1.IO.IOMode;
import se_hw1.Input.InputStrategyFactory;

public class OutputContext {

	private OutputStrategy strategy;
	private IOMode.Mode currentOutputMode;
	
	public OutputContext() {
		strategy = new ConsoleOutputStrategy();
		currentOutputMode = IOMode.getDefaultMode();
	}
	
	public void writeLine(String _line) {
		strategy.writeLine(_line);
	}
	
	public void writeLines(LineStorage _lines) {
		strategy.writeLines(_lines);
	}
	
	// InputStrategyFactory can return null if not provided a correct mode
	// we protect against this in Master Control, but we could protect against coder error
	// by checking for null here and returning a bool that it failed or was successful
	public void setContext(IOMode.Mode _mode) {
		currentOutputMode = _mode;
		setContext(OutputStrategyFactory.getStrategy(currentOutputMode));
	}
	
	private void setContext(OutputStrategy _outputStrategy){
		strategy = _outputStrategy;
	}
}
