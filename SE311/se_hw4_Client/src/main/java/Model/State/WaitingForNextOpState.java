package Model.State;

import Controller.CalculatorController;
import Model.Composite.*;

public class WaitingForNextOpState implements ICalculatorState {
	
	private String operator;
	private IArithmaticComponent precedingAC;
	
	public WaitingForNextOpState(String _operator, IArithmaticComponent _precedingAC) {
		operator = _operator;
		precedingAC = _precedingAC;
	}

	public void digitEntered(CalculatorController context, String digit) {
		context.setDisplayText(digit);
		context.setState(new NextOperandState(precedingAC, operator, digit));
	}

	public void operatorEntered(CalculatorController context, String operator) {
		context.errorMessage();
	}

	public void equalsEntered(CalculatorController context) {
		context.errorMessage();
	}

	public void clearEntered(CalculatorController context) {
		context.clear();
	}

}
