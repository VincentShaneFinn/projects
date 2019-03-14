package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controller.*;
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

        jbNum1.addActionListener(new DigitListener(1, controller));
        jbNum2.addActionListener(new DigitListener(2, controller));
        jbNum3.addActionListener(new DigitListener(3, controller));
        jbNum4.addActionListener(new DigitListener(4, controller));
        jbNum5.addActionListener(new DigitListener(5, controller));
        jbNum6.addActionListener(new DigitListener(6, controller));
        jbNum7.addActionListener(new DigitListener(7, controller));
        jbNum8.addActionListener(new DigitListener(8, controller));
        jbNum9.addActionListener(new DigitListener(9, controller));
        jbNum0.addActionListener(new DigitListener(0, controller));

        jbEqual.addActionListener(new OperatorListener(Operator.Equals, controller));
        jbClear.addActionListener(new OperatorListener(Operator.Clear, controller));
        
        jbAdd.addActionListener(new OperatorListener(Operator.Plus, controller));
        jbSubtract.addActionListener(new OperatorListener(Operator.Minus, controller));
        jbMultiply.addActionListener(new OperatorListener(Operator.Multiply, controller));
        jbDivide.addActionListener(new OperatorListener(Operator.Divide, controller));
    }

}