package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controller.CalculatorController;
import Model.Operator;

import java.util.*;

public class CalculatorView extends JFrame {

    private JTextField jtfTextField;

	public void setJtfTextField(String text) {
		jtfTextField.setText(text);
	}

	private JButton jbNum1;
    private JButton jbNum2;
    private JButton jbNum3;
    private JButton jbNum4;
    private JButton jbNum5;
    private JButton jbNum6;
    private JButton jbNum7;
    private JButton jbNum8;
    private JButton jbNum9;
    private JButton jbNum0;
    
    private JButton jbEqual;
    private JButton jbClear;
    
    private JButton jbAdd;
    private JButton jbSubtract;
    private JButton jbMultiply;
    private JButton jbDivide;

    private CalculatorController controller;
    
    public CalculatorView(CalculatorController _controller) {

    	controller = _controller;
    	
        JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new GridLayout(4, 5));
        numberPanel.add(jbNum1 = new JButton("1"));
        numberPanel.add(jbNum2 = new JButton("2"));
        numberPanel.add(jbNum3 = new JButton("3"));
        numberPanel.add(new JPanel());
        numberPanel.add(jbAdd = new JButton("+"));
        
        numberPanel.add(jbNum4 = new JButton("4"));
        numberPanel.add(jbNum5 = new JButton("5"));
        numberPanel.add(jbNum6 = new JButton("6"));
        numberPanel.add(new JPanel());
        numberPanel.add(jbSubtract = new JButton("-"));
        
        numberPanel.add(jbNum7 = new JButton("7"));
        numberPanel.add(jbNum8 = new JButton("8"));
        numberPanel.add(jbNum9 = new JButton("9"));
        numberPanel.add(new JPanel());
        numberPanel.add(jbMultiply = new JButton("*"));
        
        numberPanel.add(jbNum0 = new JButton("0"));
        numberPanel.add(jbEqual = new JButton("="));
        numberPanel.add(jbClear = new JButton("C"));
        numberPanel.add(new JPanel());
        numberPanel.add(jbDivide = new JButton("/"));

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new FlowLayout());
        displayPanel.add(jtfTextField = new JTextField(20));
        jtfTextField.setHorizontalAlignment(JTextField.RIGHT);
        jtfTextField.setEditable(false);

        JPanel mainPanel = new JPanel();
        
        mainPanel.setLayout(new GridLayout(2,1));
        mainPanel.add(displayPanel);
        mainPanel.add(numberPanel);

        add(mainPanel);

        jbNum1.addActionListener(new DigitNumberListener(1));
        jbNum2.addActionListener(new DigitNumberListener(2));
        jbNum3.addActionListener(new DigitNumberListener(3));
        jbNum4.addActionListener(new DigitNumberListener(4));
        jbNum5.addActionListener(new DigitNumberListener(5));
        jbNum6.addActionListener(new DigitNumberListener(6));
        jbNum7.addActionListener(new DigitNumberListener(7));
        jbNum8.addActionListener(new DigitNumberListener(8));
        jbNum9.addActionListener(new DigitNumberListener(9));
        jbNum0.addActionListener(new DigitNumberListener(0));

        jbEqual.addActionListener(new OperatorListener(Operator.Equals));
        jbClear.addActionListener(new OperatorListener(Operator.Clear));
        
        jbAdd.addActionListener(new OperatorListener(Operator.Plus));
        jbSubtract.addActionListener(new OperatorListener(Operator.Minus));
        jbMultiply.addActionListener(new OperatorListener(Operator.Multiply));
        jbDivide.addActionListener(new OperatorListener(Operator.Divide));
    }

	class DigitNumberListener implements ActionListener {
    	
    	private int digit;
    	
    	public DigitNumberListener(int _digit) {
    		digit = _digit;
    	}

		public void actionPerformed(ActionEvent e) {
			controller.inputDigit(digit);
		}
		
    }
    
    class OperatorListener implements ActionListener {
    	
    	private Operator operator;
    	
    	public OperatorListener(Operator _operator) {
    		operator = _operator;
    	}

		public void actionPerformed(ActionEvent e) {
			controller.inputOperator(operator);
		}
		
    }	

}