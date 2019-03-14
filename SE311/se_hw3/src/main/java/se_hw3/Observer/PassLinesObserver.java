package se_hw3.Observer;

import java.util.ArrayList;
import java.util.List;

import se_hw3.LineStorage;

public interface PassLinesObserver {

	void processLinesEvent(LineStorage lines);
	
}