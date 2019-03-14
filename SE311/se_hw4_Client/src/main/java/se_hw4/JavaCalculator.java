package se_hw4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class JavaCalculator extends JFrame {

    private JTextField jtfDisplayWindow;
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
    private JButton jbAdd;
    private JButton jbSubtract;
    private JButton jbMultiply;
    private JButton jbDivide;
    private JButton jbClear;
    
    private double TEMP;
    private double SolveTEMP;

    Boolean addBool = false;
    Boolean subBool = false;
    Boolean divBool = false;
    Boolean mulBool = false;

    String display = "";

    public JavaCalculator() {

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
        displayPanel.add(jtfDisplayWindow = new JTextField(20));
        jtfDisplayWindow.setHorizontalAlignment(JTextField.RIGHT);
        jtfDisplayWindow.setEditable(false);

        JPanel mainPanel = new JPanel();
        
        mainPanel.setLayout(new GridLayout(2,1));
        mainPanel.add(displayPanel);
        mainPanel.add(numberPanel);

        add(mainPanel);

//        jbNum1.addActionListener(new ListenToOne());
//        jbNum2.addActionListener(new ListenToTwo());
//        jbNum3.addActionListener(new ListenToThree());
//        jbNum4.addActionListener(new ListenToFour());
//        jbNum5.addActionListener(new ListenToFive());
//        jbNum6.addActionListener(new ListenToSix());
//        jbNum7.addActionListener(new ListenToSeven());
//        jbNum8.addActionListener(new ListenToEight());
//        jbNum9.addActionListener(new ListenToNine());
//        jbNum0.addActionListener(new ListenToZero());
//
//        jbAdd.addActionListener(new ListenToAdd());
//        jbSubtract.addActionListener(new ListenToSubtract());
//        jbMultiply.addActionListener(new ListenToMultiply());
//        jbDivide.addActionListener(new ListenToDivide());
//        jbClear.addActionListener(new ListenToClear());
    }

    class ListenToClear implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //display = jtfResult.getText();
            jtfDisplayWindow.setText("");
            addBool = false;
            subBool = false;
            mulBool = false;
            divBool = false;

            TEMP = 0;
            SolveTEMP = 0;
        }
    }

    class ListenToOne implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            display = jtfDisplayWindow.getText();
            jtfDisplayWindow.setText(display + "1");
        }
    }

    class ListenToTwo implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            display = jtfDisplayWindow.getText();
            jtfDisplayWindow.setText(display + "2");
        }
    }

    class ListenToThree implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            display = jtfDisplayWindow.getText();
            jtfDisplayWindow.setText(display + "3");
        }
    }

    class ListenToFour implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            display = jtfDisplayWindow.getText();
            jtfDisplayWindow.setText(display + "4");
        }
    }

    class ListenToFive implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            display = jtfDisplayWindow.getText();
            jtfDisplayWindow.setText(display + "5");
        }
    }

    class ListenToSix implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            display = jtfDisplayWindow.getText();
            jtfDisplayWindow.setText(display + "6");
        }
    }

    class ListenToSeven implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            display = jtfDisplayWindow.getText();
            jtfDisplayWindow.setText(display + "7");
        }
    }

    class ListenToEight implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            display = jtfDisplayWindow.getText();
            jtfDisplayWindow.setText(display + "8");
        }
    }

    class ListenToNine implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            display = jtfDisplayWindow.getText();
            jtfDisplayWindow.setText(display + "9");
        }
    }

    class ListenToZero implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            display = jtfDisplayWindow.getText();
            jtfDisplayWindow.setText(display + "0");
        }
    }

    class ListenToAdd implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            TEMP = Double.parseDouble(jtfDisplayWindow.getText());
            jtfDisplayWindow.setText("");
            addBool = true;
        }
    }

    class ListenToSubtract implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            TEMP = Double.parseDouble(jtfDisplayWindow.getText());
            jtfDisplayWindow.setText("");
            subBool = true;
        }
    }

    class ListenToMultiply implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            TEMP = Double.parseDouble(jtfDisplayWindow.getText());
            jtfDisplayWindow.setText("");
            mulBool = true;
        }
    }

    class ListenToDivide implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            TEMP = Double.parseDouble(jtfDisplayWindow.getText());
            jtfDisplayWindow.setText("");
            divBool = true;
        }
    }

    class ListenToSolve implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            SolveTEMP = Double.parseDouble(jtfDisplayWindow.getText());
            if (addBool == true)
                SolveTEMP = SolveTEMP + TEMP;
            else if ( subBool == true)
                SolveTEMP = SolveTEMP - TEMP;
            else if ( mulBool == true)
                SolveTEMP = SolveTEMP * TEMP;
            else if ( divBool == true)
                            SolveTEMP = SolveTEMP / TEMP;
            jtfDisplayWindow.setText(  Double.toString(SolveTEMP));

            addBool = false;
            subBool = false;
            mulBool = false;
            divBool = false;
        }
    }

}