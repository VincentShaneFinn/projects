package se_hw3;

import java.util.*;

public class LineStorage {

	private List<String> lines;
	
	public LineStorage() { 
		clearStorage();
	}
	
	public LineStorage(List<String> _lines) {
		lines = _lines;
	}
	
	public void clearStorage() {
		lines = new ArrayList<String>();
	}
	
	public int getSize() {
		return lines.size();
	}
	
	public String getLineAsString(int _index) {
		return lines.get(_index);
	}
	
	public List<String> getLineAsList(int _index) {
		return Arrays.asList(lines.get(_index).split(" "));
	}

	public void addLine(String _line) {
		lines.add(_line);
	}

	public void deleteLine(String _line) {
		lines.remove(_line);
	}
}
