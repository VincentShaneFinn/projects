package Model.State;

import javax.swing.JOptionPane;

import Controller.CalculatorController;

public class FirstOperandState implements ICalculatorState{
	
	private String number;
	
	public FirstOperandState(String _digit) {
		number = _digit;
	}

	public void digitEntered(CalculatorController context, String digit) {
		number += digit;
		context.setDisplayText(number);
	}

	public void operatorEntered(CalculatorController context, String operator) {
		context.setDisplayText(operator);
		context.setState(new WaitingForNextOpState(operator,  Integer.parseInt(number)));
	}

	public void equalsEntered(CalculatorController context) {
		context.errorMessage();
	}

	public void clearEntered(CalculatorController context) {
		context.clear();
	}

}
