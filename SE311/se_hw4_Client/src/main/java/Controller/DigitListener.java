package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DigitListener implements ActionListener {
	
	private int digit;
	private CalculatorController controller;
	
	public DigitListener(int _digit, CalculatorController _controller) {
		digit = _digit;
		controller = _controller;
	}

	public void actionPerformed(ActionEvent e) {
		controller.setInput(digit);
	}
	
}