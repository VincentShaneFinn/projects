package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Model.State.*;
import View.CalculatorView;

public class CalculatorController {

	private CalculatorView view;
	private ICalculatorState state = new InitialState();
	
	public static void main(String[] args) {				
		new CalculatorController();
	}
	
	public CalculatorController() {
        view = new CalculatorView();
        view.pack();
        view.setLocationRelativeTo(null);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setVisible(true);
        
        setupListeners();
	}
	
	private void setupListeners() {
		view.jbNum1.addActionListener(new DigitListener("1"));
		view.jbNum2.addActionListener(new DigitListener("2"));
		view.jbNum3.addActionListener(new DigitListener("3"));
		view.jbNum4.addActionListener(new DigitListener("4"));
		view.jbNum5.addActionListener(new DigitListener("5"));
		view.jbNum6.addActionListener(new DigitListener("6"));
		view.jbNum7.addActionListener(new DigitListener("7"));
        view.jbNum8.addActionListener(new DigitListener("8"));
        view.jbNum9.addActionListener(new DigitListener("9"));
        view.jbNum0.addActionListener(new DigitListener("0"));

        view.jbEqual.addActionListener(new OperatorListener("="));
        view.jbClear.addActionListener(new OperatorListener("C"));
        
        view.jbAdd.addActionListener(new OperatorListener("+"));
        view.jbSubtract.addActionListener(new OperatorListener("-"));
        view.jbMultiply.addActionListener(new OperatorListener("*"));
        view.jbDivide.addActionListener(new OperatorListener("/"));
	}
	
	public CalculatorView getView() {
		return view;
	}
	
	public void setDisplayText(String text) {
		view.setDisplayText(text);
	}
	
	public void setState(ICalculatorState _state) {
		state = _state;
	}
	
	public ICalculatorState getState() {
		return state;
	}	
	
	public void errorMessage() {
		Object[] options = {"Reset", "Discard"};
		//Custom button text
		int n = JOptionPane.showOptionDialog(view,
			    "Would you like to reset and clear the calculator or discard your last input",
			    "Ops, something went wrong",
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,     //do not use a custom Icon
			    options,  //the titles of buttons
			    options[1]); //default button title
		
		if(n == 0) {
			clear();
		}
	}
	
	public void clear() {
		setDisplayText("");
		setState(new InitialState());
	}
	
	class OperatorListener implements ActionListener {
		
		private String operator;
		
		public OperatorListener(String _operator) {
			operator = _operator;
		}

		public void actionPerformed(ActionEvent e) {
			if(operator.equals("=")) {
				state.equalsEntered(CalculatorController.this);
			}
			else if(operator.equals("C")) {
				state.clearEntered(CalculatorController.this);
			}
			else {
				state.operatorEntered(CalculatorController.this, operator);
			}
		}
		
	}	
	
	class DigitListener implements ActionListener {
		
		private String digit;
		
		public DigitListener(String _digit) {
			digit = _digit;
		}

		public void actionPerformed(ActionEvent e) {
			state.digitEntered(CalculatorController.this, digit);
		}
		
	}

}
