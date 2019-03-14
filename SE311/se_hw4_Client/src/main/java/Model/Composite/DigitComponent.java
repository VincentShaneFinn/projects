package Model.Composite;

public class DigitComponent implements ArithmaticComponent {

	private int digit;
	
	public DigitComponent(int _digit) {
		digit = _digit;
	}

	public int result() {
		return digit;
	}

}
