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

	public JButton jbNum1;
    public JButton jbNum2;
    public JButton jbNum3;
    public JButton jbNum4;
    public JButton jbNum5;
    public JButton jbNum6;
    public JButton jbNum7;
    public JButton jbNum8;
    public JButton jbNum9;
    public JButton jbNum0;
    
    public JButton jbEqual;
    public JButton jbClear;
    
    public JButton jbAdd;
    public JButton jbSubtract;
    public JButton jbMultiply;
    public JButton jbDivide;
    
    public CalculatorView() {
        buildUI();
    }

	private void buildUI() {
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
	}

}