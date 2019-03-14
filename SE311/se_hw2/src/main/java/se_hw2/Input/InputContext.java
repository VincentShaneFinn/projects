package se_hw2.Input;

import PipeAndFilter.Filter;
import PipeAndFilter.Pipe;
import se_hw2.LineStorage;

public class InputContext extends Filter {

	private InputStrategy strategy;
	
	public InputContext(Pipe input_, Pipe output_) {
		super(input_, output_);
		strategy = new ConsoleInputStrategy();
		
		String fileName = "";
		try {
			fileName = (String) input_.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setContext(fileName);
	}
	
	public void transform() {
		output_.put(readLines());
	}
	
	public String readLine() {
		return strategy.readLine();
	}
	
	private LineStorage readLines() {
		return strategy.readLines();
	}
	
	// InputStrategyFactory can return null if not provided a correct mode
	// we protect against this in Master Control, but we could protect against coder error
	// by checking for null here and returning a bool that it failed or was successful
	public void setContext(String fileName) {
		if(fileName == null || fileName == "") {
			setContext(new ConsoleInputStrategy());
		}
		else {
			FileInputStrategy fileIS = new FileInputStrategy();
			fileIS.fileName = fileName;
			setContext(fileIS);
		}
	}
	
	private void setContext(InputStrategy _inputStrategy){
		strategy = _inputStrategy;
	}
}
