package com.holosoft.simpleCalc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class SimpleCalc extends JFrame implements ActionListener {
	JLabel result;
	
	public SimpleCalc() {
		super("Simple Calculator by Choi GeumGyu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		result = new JLabel("0");
		result.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		
		setResult(this);
		setBody(this);
		
		pack();
		setSize(400, 250);
		setVisible(true);
	}
	
	void setResult(JFrame main) {
		JPanel pTop = new JPanel();
		pTop.setBackground(Color.white);
		pTop.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		pTop.setLayout(new BorderLayout());
		pTop.add(result, BorderLayout.CENTER);
		main.add(pTop, BorderLayout.NORTH);
	}
	
	void setBody(JFrame main) {
		JPanel pBody = new JPanel();
		
		pBody.setLayout(new GridLayout(4, 4));
		displayButtons(pBody);
		main.add(pBody, BorderLayout.CENTER);
	}
	
	void displayButtons(JPanel body) {
		JButton b1 = new JButton("1"); b1.setFont(new Font("맑은 고딕", Font.BOLD, 16)); b1.addActionListener(this); body.add(b1); 
		JButton b2 = new JButton("2"); b2.setFont(new Font("맑은 고딕", Font.BOLD, 16)); b2.addActionListener(this); body.add(b2);
		JButton b3 = new JButton("3"); b3.setFont(new Font("맑은 고딕", Font.BOLD, 16)); b3.addActionListener(this); body.add(b3);
		JButton b_add = new JButton("+"); b_add.setFont(new Font("맑은 고딕", Font.BOLD, 16)); b_add.addActionListener(this); body.add(b_add);
		JButton b4 = new JButton("4"); b4.setFont(new Font("맑은 고딕", Font.BOLD, 16)); b4.addActionListener(this); body.add(b4);
		JButton b5 = new JButton("5"); b5.setFont(new Font("맑은 고딕", Font.BOLD, 16)); b5.addActionListener(this); body.add(b5);
		JButton b6 = new JButton("6"); b6.setFont(new Font("맑은 고딕", Font.BOLD, 16)); b6.addActionListener(this); body.add(b6);
		JButton b_minus = new JButton("-"); b_minus.setFont(new Font("맑은 고딕", Font.BOLD, 16)); b_minus.addActionListener(this); body.add(b_minus);
		JButton b7 = new JButton("7"); b7.setFont(new Font("맑은 고딕", Font.BOLD, 16)); b7.addActionListener(this); body.add(b7);
		JButton b8 = new JButton("8"); b8.setFont(new Font("맑은 고딕", Font.BOLD, 16)); b8.addActionListener(this); body.add(b8);
		JButton b9 = new JButton("9"); b9.setFont(new Font("맑은 고딕", Font.BOLD, 16)); b9.addActionListener(this); body.add(b9);
		JButton b_mul = new JButton("×"); b_mul.setFont(new Font("맑은 고딕", Font.BOLD, 16)); b_mul.addActionListener(this); body.add(b_mul);
		JButton b_div = new JButton("÷"); b_div.setFont(new Font("맑은 고딕", Font.BOLD, 16)); b_div.addActionListener(this); body.add(b_div);
		JButton b0 = new JButton("0"); b0.setFont(new Font("맑은 고딕", Font.BOLD, 16)); b0.addActionListener(this); body.add(b0);
		JButton b_mod = new JButton("C"); b_mod.setFont(new Font("맑은 고딕", Font.BOLD, 16)); b_mod.addActionListener(this); body.add(b_mod);
		JButton b_result = new JButton("＝"); b_result.setFont(new Font("맑은 고딕", Font.BOLD, 16)); b_result.addActionListener(this); body.add(b_result);
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		String old = result.getText();
		
		if (old == "0") {
			old = "";
		}
		
		if (command == "1" || command == "2" || command == "3" || command == "4" || command == "5" || command == "6" || command == "7" || command == "8" || command == "9" || command == "0") {
			result.setText(old + command);
		}
		else if (command == "C") {
			result.setText("0");
		}
		else if (command == "+") {
			if (old.length() >= 1 && old.substring(old.length() - 1) != "+") {
				result.setText(old + "+");
			}
		}
		else if (command == "-") {
			if (old.length() >= 1 && old.substring(old.length() - 1) != "-") {
				result.setText(old + "-");
			}
		}
		else if (command == "×") {
			if (old.length() >= 1 && old.substring(old.length() - 1) != "×") {
				result.setText(old + "×");
			}
		}
		else if (command == "÷") {
			if (old.length() >= 1 && old.substring(old.length() - 1) != "÷") {
				result.setText(old + "÷");
			}
		}
		else if (command == "＝" && old.length() >= 3) {
			try {
				Evaluate();
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "예외가 발생했습니다.", "예외 발생!!", JOptionPane.CANCEL_OPTION);
				ex.printStackTrace();
			}
		}
	}
	
	public void Evaluate() throws ScriptException {
		String old = result.getText();
		result.setText(old + "=");
		
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("nashorn");
		int res = (int)engine.eval(old);

		result.setText(result.getText() + res);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SimpleCalc();
	}
}
