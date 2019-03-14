package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	public void setInput(int digit) {
		view.setJtfTextField(Integer.toString(digit));
	}
	
	public void setInput(Operator operator) {
		view.setJtfTextField(operator.toString());
	}
	

}
