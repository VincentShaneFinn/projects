package Model.State;

import Controller.CalculatorController;
import Model.Composite.*;
import Model.Visitor.SolveVisitor;

public class NextOperandState implements ICalculatorState {

	private String precedingOperator;
	private IArithmaticComponent precedingAC;
	private String nextValue;
	
	public NextOperandState(IArithmaticComponent _precedingAC, String _operator, String _nextValue) {
		precedingOperator = _operator;
		precedingAC = _precedingAC;
		nextValue = _nextValue;
	}

	public void digitEntered(CalculatorController context, String digit) {
		nextValue += digit;
		context.setDisplayText(nextValue);
	}

	public void operatorEntered(CalculatorController context, String operator) {
		DigitComponent dc = new DigitComponent(Integer.parseInt(nextValue));
		EquationComponent equation = new EquationComponent(precedingAC, precedingOperator, dc);
		
		calculate(equation, context);
		
		context.setState(new WaitingForNextOpState(operator, equation));
	}

	public void equalsEntered(CalculatorController context) {
		DigitComponent dc = new DigitComponent(Integer.parseInt(nextValue));
		EquationComponent equation = new EquationComponent(precedingAC, precedingOperator, dc);
		
		try {
			calculate(equation, context);
			
		} catch(Exception e) {
			context.errorMessage();
			return;
		}
		context.setState(new CalculateState());
	}

	public void clearEntered(CalculatorController context) {
		context.clear();
	}
	
	private void calculate(EquationComponent equation, CalculatorController context) {
		SolveVisitor visitor = new SolveVisitor();
	 	equation.accept(visitor);
		context.setDisplayText(Integer.toString(visitor.getResult()));
	}

	
}
