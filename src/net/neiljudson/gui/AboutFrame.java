package net.neiljudson.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AboutFrame() {
		setSize(350, 200);
		setTitle("关于节点温度监测器");
		setLayout(null);
		setLocation(0, 450);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(7, 6, 300, 300);

		JLabel label1 = new JLabel("Copyright 2015  贾玮玮");
		label1.setBounds(10, 0, 300, 50);
		JLabel label2 = new JLabel("Revision: 1");
		label2.setBounds(10, 50, 300, 50);
		JLabel label3 = new JLabel("Date: 2015/06/27 00:24:00");
		label3.setBounds(10, 100, 300, 50);
		
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		
		add(panel);
		
		setVisible(true);
	}
}