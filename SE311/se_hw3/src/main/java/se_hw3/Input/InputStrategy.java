package se_hw3.Input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import se_hw3.CircularShift;
import se_hw3.LineStorage;
import se_hw3.Observer.PassLinesObserver;
import se_hw3.Observer.PassLinesSubject;

public abstract class InputStrategy extends PassLinesSubject {

	public InputStrategy(PassLinesObserver obs) {
		this.attach(obs);
	}
	
	protected abstract String readLine();
	protected abstract void readLines();
	
}
