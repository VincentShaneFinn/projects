package Model.State;

import Controller.CalculatorController;
import Model.Operator;

public interface ICalculatorState {
	public void digitEntered(CalculatorController context, String digit);
	public void operatorEntered(CalculatorController context, Operator operator);
	public void equalsEntered(CalculatorController context);
	public void clearEntered(CalculatorController context);
}
