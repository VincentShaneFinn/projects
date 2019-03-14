package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Model.Operator;
import Model.Composite.DigitComponent;
import Model.State.*;
import Model.Visitor.DisplayVisitor;
import View.CalculatorView;

public class CalculatorController {

	private CalculatorView view;
	private ICalculatorState state = new InitialState();
	
	public static void main(String[] args) {		
//		DisplayVisitor dv = new DisplayVisitor();
//		dv.visit(new Model.Composite.EquationComponent(new DigitComponent(2), Operator.Plus, new DigitComponent(3)));
//		dv.print();
		
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

        view.jbEqual.addActionListener(new OperatorListener(Operator.Equals));
        view.jbClear.addActionListener(new OperatorListener(Operator.Clear));
        
        view.jbAdd.addActionListener(new OperatorListener(Operator.Plus));
        view.jbSubtract.addActionListener(new OperatorListener(Operator.Minus));
        view.jbMultiply.addActionListener(new OperatorListener(Operator.Multiply));
        view.jbDivide.addActionListener(new OperatorListener(Operator.Divide));
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
	
	class OperatorListener implements ActionListener {
		
		private Operator operator;
		
		public OperatorListener(Operator _operator) {
			operator = _operator;
		}

		public void actionPerformed(ActionEvent e) {
			switch(operator) {
			case Equals:
				state.equalsEntered(CalculatorController.this);
				break;
			case Clear:
				state.clearEntered(CalculatorController.this);
				break;
			default:
				state.operatorEntered(CalculatorController.this, operator);
				break;
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
