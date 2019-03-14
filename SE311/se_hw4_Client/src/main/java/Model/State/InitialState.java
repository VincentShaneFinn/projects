package Model.State;

import Controller.CalculatorController;

public class InitialState implements ICalculatorState {

	public void digitEntered(CalculatorController context, String digit) {
		context.setDisplayText(digit);
		context.setState(new FirstOperandState(digit));
	}

	public void operatorEntered(CalculatorController context, String operator) {
		//nothing happens if operator pressed in the initial state
	}

	public void equalsEntered(CalculatorController context) {
		//nothing happens if equals pressed in the initial state		
	}

	public void clearEntered(CalculatorController context) {
		//nothing happens if clear pressed in the initial state
	}
	
}
