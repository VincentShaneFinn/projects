package Model.Composite;

import Model.Visitor.ACVisitor;

public class DigitComponent implements ArithmaticComponent {

	private int digit;
	
	public DigitComponent(int _digit) {
		digit = _digit;
	}
	
	public int getDigit() {
		return digit;
	}

	public void accept(ACVisitor visitor) {
		visitor.visit(this);
	}

}
