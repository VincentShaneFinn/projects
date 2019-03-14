package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Operator;

public class OperatorListener implements ActionListener {
	
	private Operator operator;
	private CalculatorController controller;
	
	public OperatorListener(Operator _operator, CalculatorController _controller) {
		operator = _operator;
		controller = _controller;
	}

	public void actionPerformed(ActionEvent e) {
		controller.setInput(operator);
	}
	
}	
