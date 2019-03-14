package Model.Visitor;

import Model.Composite.DigitComponent;
import Model.Composite.EquationComponent;
import Model.Operator;

public class SolveVisitor implements IACVisitor {
	
	private int currentEvaluation = 0;
	private int currentDigit = 0;
	
	public int getResult() {
		return currentEvaluation;
	}

	public void visit(DigitComponent digit) {
		currentDigit = digit.getDigit();
	}

	public void visit(EquationComponent equation) {
		equation.getLeft().accept(this);
		int left = currentDigit;
		equation.getRight().accept(this);
		int right = currentDigit;
		
		switch (equation.getOperator()) {
		case Plus:
			currentEvaluation = left + right;
			break;
		case Minus:
			currentEvaluation = left - right;
			break;
		case Multiply:
			currentEvaluation = left * right;
			break;
		case Divide:
			currentEvaluation = left / right;
			break;
			default:
				break;
		}
	}

}
