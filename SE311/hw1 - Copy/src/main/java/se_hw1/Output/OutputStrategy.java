package se_hw1.Output;

import se_hw1.LineStorage;

public interface OutputStrategy {
	
	void writeLine(String _line);
	void writeLines(LineStorage _lines);
	
}
