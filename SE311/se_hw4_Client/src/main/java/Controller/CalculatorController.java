package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Model.Operator;
import View.CalculatorView;

public class CalculatorController {

	private CalculatorView view;
	
	public void presentView() {
        view = new CalculatorView();
        view.pack();
        view.setLocationRelativeTo(null);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setVisible(true);
        
        setupListeners();
	}
	
	public void setInput(int digit) {
		view.setJtfTextField(Integer.toString(digit));
	}
	
	public void setInput(Operator operator) {
		view.setJtfTextField(operator.toString());
	}
	
	private void setupListeners() {
		view.jbNum1.addActionListener(new DigitListener(1));
		view.jbNum2.addActionListener(new DigitListener(2));
		view.jbNum3.addActionListener(new DigitListener(3));
		view.jbNum4.addActionListener(new DigitListener(4));
		view.jbNum5.addActionListener(new DigitListener(5));
		view.jbNum6.addActionListener(new DigitListener(6));
		view.jbNum7.addActionListener(new DigitListener(7));
        view.jbNum8.addActionListener(new DigitListener(8));
        view.jbNum9.addActionListener(new DigitListener(9));
        view.jbNum0.addActionListener(new DigitListener(0));

        view.jbEqual.addActionListener(new OperatorListener(Operator.Equals));
        view.jbClear.addActionListener(new OperatorListener(Operator.Clear));
        
        view.jbAdd.addActionListener(new OperatorListener(Operator.Plus));
        view.jbSubtract.addActionListener(new OperatorListener(Operator.Minus));
        view.jbMultiply.addActionListener(new OperatorListener(Operator.Multiply));
        view.jbDivide.addActionListener(new OperatorListener(Operator.Divide));
	}
	
	class OperatorListener implements ActionListener {
		
		private Operator operator;
		
		public OperatorListener(Operator _operator) {
			operator = _operator;
		}

		public void actionPerformed(ActionEvent e) {
			setInput(operator);
		}
		
	}	
	
	class DigitListener implements ActionListener {
		
		private int digit;
		
		public DigitListener(int _digit) {
			digit = _digit;
		}

		public void actionPerformed(ActionEvent e) {
			setInput(digit);
		}
		
	}

}
