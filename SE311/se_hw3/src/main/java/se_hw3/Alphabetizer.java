package se_hw3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import se_hw3.Observer.PassLinesObserver;
import se_hw3.Observer.PassLinesSubject;

public class Alphabetizer extends PassLinesSubject implements PassLinesObserver{
	
	public Alphabetizer(PassLinesObserver obs) {
		this.attach(obs);
	}
	
	public void sortByAlphabeticalOrder(LineStorage lines) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < lines.getSize(); i++) {
			list.add(lines.getLineAsString(i));
		}
		Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
		lines = new LineStorage(list);
		
		this.callFinishedReadingEvent(lines);
	}

	public void processLinesEvent(LineStorage lines) {
		// TODO Auto-generated method stub
		this.sortByAlphabeticalOrder(lines);
	}
	
}
