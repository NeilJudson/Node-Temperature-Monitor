package net.alisonaa.gui;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainInterfaceFrame extends JFrame implements ActionListener,
		ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainInterfaceFrame() {
		setSize(530, 450);
		setTitle("�ڵ��¶ȼ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		/* menuBar */
		JMenuBar menuBar = new JMenuBar();
		JMenu menuConf = new JMenu("����");
		JMenu menuHelp = new JMenu("����");
		JMenu menuQuery = new JMenu("��ѯ");
		JMenuItem query_Query = new JMenuItem("��ѯ");
		JMenuItem conf_Period = new JMenuItem("����ˢ������");
		JMenuItem conf_Threshold = new JMenuItem("���ñ����¶ȷ�ֵ");
		JMenuItem help_About = new JMenuItem("���ڽڵ��¶ȼ����");

		query_Query.addActionListener(this);
		conf_Period.addActionListener(this);
		conf_Threshold.addActionListener(this);
		help_About.addActionListener(this);

		menuQuery.add(query_Query);
		menuConf.add(conf_Period);
		menuConf.add(conf_Threshold);
		menuHelp.add(help_About);

		menuBar.add(menuQuery);
		menuBar.add(menuConf);
		menuBar.add(menuHelp);

		setJMenuBar(menuBar);

		/* panel1 */
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(7, 6, 500, 50);

		JLabel labNet = new JLabel("��ǰ����ѡ��");
		labNet.setBounds(0, 0, 80, 50);

		Choice choNet = new Choice();
		choNet.setBounds(100, 15, 50, 50);
		choNet.add("1");
		choNet.addItemListener(this);

		JButton butRefresh = new JButton("ˢ��");
		butRefresh.setBounds(410, 10, 60, 30);
		butRefresh.addActionListener(this);

		panel1.add(labNet);
		panel1.add(choNet);
		panel1.add(butRefresh);

		add(panel1);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("��ѯ")) {
			System.out.println("��ѯ");
		}
		if (e.getActionCommand().equals("����ˢ������")) {
			System.out.println("����ˢ������");
		}
		if (e.getActionCommand().equals("���ñ����¶ȷ�ֵ")) {
			System.out.println("���ñ����¶ȷ�ֵ");
		}
		if (e.getActionCommand().equals("���ڽڵ��¶ȼ����")) {
			System.out.println("���ڽڵ��¶ȼ����");
		}
		if (e.getActionCommand().equals("ˢ��")) {
			System.out.println("ˢ��");
		}
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
