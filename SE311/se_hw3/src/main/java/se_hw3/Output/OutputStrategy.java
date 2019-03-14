package se_hw3.Output;

import se_hw3.LineStorage;

public interface OutputStrategy {
	
	void writeLine(String _line);
	void writeLines(LineStorage _lines);
	
}
