package Model.Visitor;

import Model.Composite.DigitComponent;
import Model.Composite.EquationComponent;

public class DisplayVisitor implements IACVisitor {

	private StringBuilder sb = new StringBuilder();
	
	public void visit(DigitComponent digit) {
		sb.append(digit.getDigit());
	}

	public void visit(EquationComponent equation) {
		equation.getLeft().accept(this);
		sb.append(" ");
		sb.append(equation.getOperator());
		sb.append(" ");
		equation.getRight().accept(this);
	}
	
	public String getString() {
		return sb.toString();
	}
	
}
