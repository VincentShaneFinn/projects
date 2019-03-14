package se_hw2.Output;

import se_hw2.LineStorage;

public interface OutputStrategy {
	
	void writeLine(String _line);
	void writeLines(LineStorage _lines);
	
}
