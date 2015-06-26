package net.neiljudson.gui;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.neiljudson.business.NodeTemperaMonitor;

public class ConfThresholdFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Choice choThreshold = new Choice();

	public ConfThresholdFrame() {
		setSize(250, 150);
		setTitle("配置报警温度阀值");
		setLayout(null);
		setLocation(250, 450);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(7, 6, 220, 100);

		JLabel label = new JLabel("报警温度阀值");
		label.setBounds(10, 0, 90, 50);

		choThreshold.setBounds(100, 15, 40, 50);
		for (int i = 60; i < 121; i++) {
			choThreshold.add(String.valueOf(i));
		}
		
		JButton jb = new JButton("确定");
		jb.setBounds(75, 60, 60, 30);
		jb.addActionListener(this);

		panel.add(label);
		panel.add(choThreshold);
		panel.add(jb);

		add(panel);

		setVisible(true);
	}

	private void config() {
		try {
			RandomAccessFile raf = new RandomAccessFile("config.dat", "rw");
			String str = new String(raf.readLine() + "\r" + raf.readLine()
					+ "\r" + raf.readLine() + "\r"
					+ choThreshold.getSelectedItem() + "\r");
			raf.close();
			FileWriter fw = new FileWriter("config.dat");
			fw.write(str);
			fw.close();
		} catch (IOException e) {
			System.out.print("Read File Error" + e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("确定")) {
			config();
			dispose();
			NodeTemperaMonitor.iThresholdChanged = 1;
		}
	}
}
