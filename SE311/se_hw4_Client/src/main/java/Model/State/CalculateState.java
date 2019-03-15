package Model.State;

import Controller.CalculatorController;

public class CalculateState implements ICalculatorState {

	public void digitEntered(CalculatorController context, String digit) {
		context.setDisplayText(digit);
		context.setState(new FirstOperandState(digit));
	}

	public void operatorEntered(CalculatorController context, String operator) {
		context.clear();
	}

	public void equalsEntered(CalculatorController context) {
		context.clear();
	}

	public void clearEntered(CalculatorController context) {
		context.clear();
	}

}
