package Model.Composite;

import Model.Operator;
import Model.Visitor.ACVisitor;

public class EquationComponent implements ArithmaticComponent {

	private ArithmaticComponent left;
	private ArithmaticComponent right;
	private Operator operator;
	
	public EquationComponent(ArithmaticComponent _left, Operator _operator, ArithmaticComponent _right) {
		left = _left;
		operator = _operator;
		right = _right;
	}
	
	public ArithmaticComponent getLeft() {
		return left;
	}
	
	public ArithmaticComponent getRight() {
		return right;
	}

	public Operator getOperator() {
		return operator;
	}

	public void accept(ACVisitor visitor) {
		visitor.visit(this);
	}

}
