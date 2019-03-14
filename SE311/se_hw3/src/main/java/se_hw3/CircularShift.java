package se_hw3;

import java.util.ArrayList;
import java.util.List;

import se_hw3.Observer.PassLinesObserver;
import se_hw3.Observer.PassLinesSubject;

public class CircularShift extends PassLinesSubject implements PassLinesObserver {
	
	public CircularShift(PassLinesObserver obs) {
		this.attach(obs);
	}

	public void performCircularShift(LineStorage lines) {
		LineStorage newLS = new LineStorage();
		
		for (int i = 0; i < lines.getSize(); i++) {
			List<String> lineList = new ArrayList<String>(lines.getLineAsList(i));
			for (int j = 0; j < lineList.size(); j++) {
				newLS.addLine(buildStringFromList(lineList));
				lineList = shiftArray(lineList);
			}
			
		}
		
		this.callFinishedReadingEvent(newLS);
	}
	
	private List<String> shiftArray(List<String> list) {
		String firstWord = list.get(0);
		list.remove(0);
		list.add(firstWord);
		
		return list;
	}
	
	private String buildStringFromList(List<String> list) {
		String line = "";
		
		for(int i = 0; i < list.size(); i++) {
			if(i != 0) {
				line += " ";
			}
			line += list.get(i);
		}
		
		return line;
	}

	public void processLinesEvent(LineStorage lines) {
		// TODO Auto-generated method stub
		this.performCircularShift(lines);
	}
	
}
