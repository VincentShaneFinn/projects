package Model.State;

import Controller.CalculatorController;
import Model.Composite.DigitComponent;

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
		DigitComponent dc = new DigitComponent(Integer.parseInt(number));
		context.setState(new WaitingForNextOpState(operator,  dc));
	}

	public void equalsEntered(CalculatorController context) {
		context.errorMessage();
	}

	public void clearEntered(CalculatorController context) {
		context.clear();
	}

}
