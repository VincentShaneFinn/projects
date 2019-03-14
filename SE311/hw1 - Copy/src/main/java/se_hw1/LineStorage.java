package se_hw1;

import java.util.*;

public class LineStorage {

	private List<String> lines;
	private int currentIndex;
	
	public LineStorage() { 
		clearStorage();
	}
	
	public LineStorage(List<String> _lines) {
		lines = lines;
		currentIndex = 0;
	}
	
	public void clearStorage() {
		lines = new ArrayList<String>();
		currentIndex = 0;
	}
	
	public int getSize() {
		return lines.size();
	}
	
	public String getLine(int _index) {
		return lines.get(_index);
	}

	public void addLine(String _line) {
		lines.add(_line);
	}

}
