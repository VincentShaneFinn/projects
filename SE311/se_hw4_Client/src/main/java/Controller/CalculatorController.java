package Controller;

import javax.swing.JFrame;

import Model.Operator;
import View.CalculatorView;

public class CalculatorController {

	private CalculatorView view;
	
	public void presentView() {
        view = new CalculatorView(this);
        view.pack();
        view.setLocationRelativeTo(null);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setVisible(true);
	}
	
	public void inputDigit(int digit) {
		view.setJtfTextField(Integer.toString(digit));
	}
	
	public void inputOperator(Operator operator) {
		view.setJtfTextField(operator.toString());
	}
	
}
