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
		setTitle("节点温度检测器");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		/* menuBar */
		JMenuBar menuBar = new JMenuBar();
		JMenu menuConf = new JMenu("配置");
		JMenu menuHelp = new JMenu("帮助");
		JMenu menuQuery = new JMenu("查询");
		JMenuItem query_Query = new JMenuItem("查询");
		JMenuItem conf_Period = new JMenuItem("配置刷新周期");
		JMenuItem conf_Threshold = new JMenuItem("配置报警温度阀值");
		JMenuItem help_About = new JMenuItem("关于节点温度监测器");

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

		JLabel labNet = new JLabel("当前网络选择");
		labNet.setBounds(0, 0, 80, 50);

		Choice choNet = new Choice();
		choNet.setBounds(100, 15, 50, 50);
		choNet.add("1");
		choNet.addItemListener(this);

		JButton butRefresh = new JButton("刷新");
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
		if (e.getActionCommand().equals("查询")) {
			System.out.println("查询");
		}
		if (e.getActionCommand().equals("配置刷新周期")) {
			System.out.println("配置刷新周期");
		}
		if (e.getActionCommand().equals("配置报警温度阀值")) {
			System.out.println("配置报警温度阀值");
		}
		if (e.getActionCommand().equals("关于节点温度监测器")) {
			System.out.println("关于节点温度监测器");
		}
		if (e.getActionCommand().equals("刷新")) {
			System.out.println("刷新");
		}
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
