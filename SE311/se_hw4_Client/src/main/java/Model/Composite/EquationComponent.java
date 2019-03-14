package Model.Composite;

import Model.Operator;

public class EquationComponent implements ArithmaticComponent {

	private ArithmaticComponent left;
	private ArithmaticComponent right;
	private Operator operator;
	
	public EquationComponent(ArithmaticComponent _left, Operator _operator, ArithmaticComponent _right) {
		left = _left;
		operator = _operator;
		right = _right;
	}
	
	public int result() {
		return left.result() + right.result();
	}

}
