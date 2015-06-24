package net.alisonaa.gui;

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
import javax.swing.JTextField;

import net.alisonaa.business.NodeTemperaMonitor;

public class ConfPeriodFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField textPeriod = new JTextField(5);
	Choice choPeriodUnit = new Choice();
	
	public ConfPeriodFrame() {
		setSize(250, 150);
		setTitle("配置刷新周期");
		setLayout(null);
		setLocation(0, 450);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(7, 6, 220, 100);

		JLabel label4 = new JLabel("温度报告周期");
		label4.setBounds(0, 0, 90, 50);
		
		textPeriod.setBounds(90, 13, 50, 24);
		textPeriod.setText("1000");

		choPeriodUnit.setBounds(150, 15, 50, 50);
		choPeriodUnit.add("ms");
		choPeriodUnit.add("s");
		choPeriodUnit.add("min");
		choPeriodUnit.add("h");

		panel.add(label4);
		panel.add(textPeriod);
		panel.add(choPeriodUnit);
		
		JButton jb=new JButton("确定");
		jb.setBounds(75, 60, 60, 30);
		jb.addActionListener(this);
		
		panel.add(jb);
		
		add(panel);
		
		setVisible(true);
	}

	void config()
	{
		try
		{
			RandomAccessFile raf = new RandomAccessFile("config.dat", "rw");
			raf.readLine();
			raf.readLine();
			raf.readLine();
			String str = new String(textPeriod.getText() + "\r"
					+ choPeriodUnit.getSelectedItem() + "\r"
					+ choPeriodUnit.getSelectedIndex() + "\r"
					+ raf.readLine() + "\r");
			raf.close();
			FileWriter fw = new FileWriter("config.dat");
			fw.write(str);
			fw.close();
		}
		catch(IOException e)
		{
			System.out.print("Read File Error"+e);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("确定")) {
			config();
			dispose();
			NodeTemperaMonitor.iPeriodChanged = 1;
		}
	}
}
