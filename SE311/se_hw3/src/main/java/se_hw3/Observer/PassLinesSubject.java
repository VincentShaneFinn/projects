package se_hw3.Observer;

import java.util.ArrayList;
import java.util.List;

import se_hw3.LineStorage;

public class PassLinesSubject {

	private List<PassLinesObserver> observers = new ArrayList<PassLinesObserver>();
	
	public void attach(PassLinesObserver obs) {
		observers.add(obs);
	}
	
	public void detach(PassLinesObserver obs) {
		observers.remove(obs);
	}
	
	public void callFinishedReadingEvent(LineStorage lines) {
		for (PassLinesObserver observer: observers) {
			observer.processLinesEvent(lines);
		}
	}
	
}
