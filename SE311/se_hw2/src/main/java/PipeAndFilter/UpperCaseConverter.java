package PipeAndFilter;

import se_hw2.LineStorage;
import se_hw2.Output.OutputContext;

public class UpperCaseConverter extends Filter {

	private String FileName;
	
	public UpperCaseConverter(Pipe input_, Pipe output_, String fileName) {
		super(input_, output_);
		// TODO Auto-generated constructor stub
		FileName = fileName;
	}
	
	public void transform() {
		LineStorage lines;
		try {
			lines = (LineStorage) input_.get();
			LineStorage newLines = new LineStorage();
			for (int i = 0; i < lines.getSize(); i++) {
				newLines.addLine((lines.getLineAsString(i).toUpperCase()));
			}
			
			output_.put(newLines);
		     
		    OutputContext outputContext = new OutputContext(output_, null, FileName);
		    Thread outputContextFilter = new Thread(outputContext);
		    outputContextFilter.start();
		    
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
