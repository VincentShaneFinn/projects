package se_hw2.Output;

import PipeAndFilter.Filter;
import PipeAndFilter.Pipe;
import se_hw2.LineStorage;
import se_hw2.Input.ConsoleInputStrategy;
import se_hw2.Input.FileInputStrategy;

public class OutputContext extends Filter {

	private OutputStrategy strategy;
	
	public OutputContext(Pipe input_, Pipe output_, String fileName) {
		super(input_, output_);
		
		setContext(fileName);

		try {
			LineStorage lines = (LineStorage) input_.get();
			writeLines(lines);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	protected void transform() {
		// TODO Auto-generated method stub
		
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
	public void setContext(String fileName) {
		if(fileName == "") {
			setContext(new ConsoleOutputStrategy());
		}
		else {
			FileOutputStrategy fileOS = new FileOutputStrategy();
			fileOS.fileName = fileName;
			setContext(fileOS);
		}
	}
	
	private void setContext(OutputStrategy _outputStrategy){
		strategy = _outputStrategy;
	}
	
}
