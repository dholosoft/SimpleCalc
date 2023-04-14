package com.holosoft.simpleCalc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class SimpleCalc extends JFrame implements ActionListener {
	JLabel result;
	long NUM1, NUM2;
	
	public SimpleCalc() {
		super("Simple Calculator by Choi GeumGyu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		NUM1 = Long.MIN_VALUE;
		NUM2 = Long.MIN_VALUE;
		
		result = new JLabel("0");
		result.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		
		setResult(this);
		setBody(this);
		
		pack();
		setSize(400, 250);
		setLocationRelativeTo(null);
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
		Map<Integer, JButton> buttons = new HashMap<Integer, JButton>();
		
		for(int i = 1; i <= 16; i++) {
			buttons.put(Integer.valueOf(i), new JButton());
			buttons.get(Integer.valueOf(i)).setFont(new Font("맑은 고딕", Font.BOLD, 16));
			buttons.get(Integer.valueOf(i)).addActionListener(this);
			
			switch(i) {
				case 1 : buttons.get(Integer.valueOf(i)).setText("1"); break;
				case 2 : buttons.get(Integer.valueOf(i)).setText("2"); break;
				case 3 : buttons.get(Integer.valueOf(i)).setText("3"); break;
				case 4 : buttons.get(Integer.valueOf(i)).setText("+"); break;
				case 5 : buttons.get(Integer.valueOf(i)).setText("4"); break;
				case 6 : buttons.get(Integer.valueOf(i)).setText("5"); break;
				case 7 : buttons.get(Integer.valueOf(i)).setText("6"); break;
				case 8 : buttons.get(Integer.valueOf(i)).setText("-"); break;
				case 9 : buttons.get(Integer.valueOf(i)).setText("7"); break;
				case 10 : buttons.get(Integer.valueOf(i)).setText("8"); break;
				case 11 : buttons.get(Integer.valueOf(i)).setText("9"); break;
				case 12 : buttons.get(Integer.valueOf(i)).setText("×"); break;
				case 13 : buttons.get(Integer.valueOf(i)).setText("÷"); break;
				case 14 : buttons.get(Integer.valueOf(i)).setText("0"); break;
				case 15 : buttons.get(Integer.valueOf(i)).setText("C"); break;
				case 16 : buttons.get(Integer.valueOf(i)).setText("＝"); break;
			}
			
			body.add(buttons.get(Integer.valueOf(i)));
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		String op = new String("");
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
				if (old.indexOf("+") <= -1) {
					NUM1 = Long.parseLong(old);
					result.setText(old + "+");
					op = "add";
				}
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
			NUM2 = Long.parseLong(old.substring(old.indexOf("+") + 1));
			
			try {
				Evaluate(op);
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "예외가 발생했습니다.", "예외 발생!!", JOptionPane.CANCEL_OPTION);
				ex.printStackTrace();
			}
		}
	}
	
	public void Evaluate(String operation) throws Exception {
		Long ans = 0L;
		
		switch(operation) {
			case "add" : ans = NUM1 + NUM2; break;
			case "minus" : ans = NUM1 - NUM2; break;
			case "multiply" : ans = NUM1 * NUM2; break;
			case "divide" : 
				if (NUM2 == 0L) {
					JOptionPane.showMessageDialog(null, "수학적으로 불능입니다.", "불능", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				ans = NUM1 / NUM2; break;
		}

		result.setText(result.getText() + "=" + ans.toString());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("SimpleCalc를 실행합니다...");
		
		new SimpleCalc();
	}
}
