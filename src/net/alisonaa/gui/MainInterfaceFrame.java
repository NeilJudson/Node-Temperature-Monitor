package net.alisonaa.gui;

import static net.alisonaa.business.NodeTemperaMonitor.NODE_NUM;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import net.alisonaa.business.NodeTemperaMonitor;
import net.alisonaa.dao.Runa;

public class MainInterfaceFrame extends JFrame implements ActionListener,
		ItemListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String strNetID = "1";

	private JButton butRefresh = new JButton("刷新");

	private JPanel panelPeriod = new JPanel();
	String strPeriod = new String();
	String strPeriodUnit = new String();
	String strPeriodUnitNum = new String();

	private JPanel panelThreshold = new JPanel();
	private String strThreshold = new String();

	private JPanel panelSysSta = new JPanel();

	private JPanel panelTempera = new JPanel();
	private JLabel[] label = new JLabel[NODE_NUM];
	
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

		butRefresh.setBounds(410, 10, 60, 30);
		butRefresh.addActionListener(this);

		panel1.add(labNet);
		panel1.add(choNet);
		panel1.add(butRefresh);

		add(panel1);
		panel1 = null;

		/* panelPeriod */
		panelPeriod.setLayout(null);
		panelPeriod.setBounds(7, 56, 250, 50);
		updatePeriodPanel();

		add(panelPeriod);

		/* panelThreshold */
		panelThreshold.setLayout(null);
		panelThreshold.setBounds(257, 56, 250, 50);
		updateThresholdPanel();

		add(panelThreshold);

		/* panelSysSta */
		panelSysSta.setLayout(null);
		panelSysSta.setBounds(7, 106, 500, 50);
		updateSysStaPanel();

		add(panelSysSta);

		/* panelTempera */
		panelTempera.setLayout(null);
		panelTempera.setBounds(7, 156, 500, 200);
		updateTemperaPanel();

		add(panelTempera);

		setVisible(true);
		
		timedRefresh();
	}
	
	public void updatePeriodPanel() {
		try {
			RandomAccessFile raf = new RandomAccessFile("config.dat", "r");
			strPeriod = raf.readLine();
			strPeriodUnit = raf.readLine();
			raf.close();
		} catch (IOException e) {
			System.out.print("Read File Error" + e);
		}
		JLabel labPeriod = new JLabel("温度报告周期" + "   " + strPeriod + " "
				+ strPeriodUnit);
		labPeriod.setBounds(0, 0, 200, 50);
		panelPeriod.removeAll();
		panelPeriod.add(labPeriod);
		panelPeriod.repaint();
	}
	
	public void updateThresholdPanel() {
		try {
			RandomAccessFile raf = new RandomAccessFile("config.dat", "r");
			raf.readLine();
			raf.readLine();
			raf.readLine();
			strThreshold = raf.readLine();
			raf.close();
		} catch (IOException e) {
			System.out.print("Read File Error" + e);
		}
		JLabel labThreshold = new JLabel("报警温度阀值" + "   " + strThreshold + " "
				+ "℃");
		labThreshold.setBounds(0, 0, 200, 50);
		panelThreshold.removeAll();
		panelThreshold.add(labThreshold);
		panelThreshold.repaint();
	}
	
	private void updateSysStaPanel() {
		int iMaxTempera = 0;
		int flag = 0;
		for (int i = 0; i < NODE_NUM; i++) {
			if (iMaxTempera < NodeTemperaMonitor.net.node[i].iMaxTempera) {
				iMaxTempera = NodeTemperaMonitor.net.node[i].iMaxTempera;
				flag = i;
			}
		}
		JLabel labMaxTem = new JLabel("最高温度   节点" + strNetID + "-" + (flag + 1) + "  " + iMaxTempera + "℃");
		labMaxTem.setBounds(0, 0, 250, 50);
		JLabel labStaSys;
		if (Integer.valueOf(strThreshold) < iMaxTempera) {
			labStaSys = new JLabel("系统状态：  不正常");
			labStaSys.setForeground(Color.RED);
		} else {
			labStaSys = new JLabel("系统状态：  正常");
		}
		labStaSys.setBounds(250, 0, 250, 50);
		panelSysSta.removeAll();
		panelSysSta.add(labMaxTem);
		panelSysSta.add(labStaSys);
		panelSysSta.repaint();
	}
	
	private void updateTemperaPanel() {
		String strNodeID = new String();
		JPanel panelTemp = new JPanel();
		panelTemp.setLayout(null);
		panelTemp.setBounds(0, 0, 500, 200);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 4; j++) {
				int n = i * 4 + j;
				if (n + 1 < 10) {
					strNodeID = String.valueOf(n + 1) + "       ";
				} else {
					strNodeID = String.valueOf(n + 1) + "     ";
				}
				int iTempera = NodeTemperaMonitor.net.node[n].aliTempera.get(NodeTemperaMonitor.net.node[n].iNum - 1);
				label[n] = new JLabel("节点" + strNetID + "-" + strNodeID + iTempera + "℃");
				if (iTempera >= Integer.valueOf(strThreshold))
					label[n].setForeground(Color.RED);
				else
					label[n].setForeground(Color.BLUE);
				label[n].setBounds(j * 125, i * 25, 125, 25);
				panelTemp.add(label[n]);
				label[n].addMouseListener(this);
			}
		}
		strNodeID = null;
		panelTempera.removeAll();
		panelTempera.add(panelTemp);
		panelTempera.repaint();
		panelTemp = null;
	}
	
	static String getNetID() {
		return strNetID;
	}
	
	@SuppressWarnings("deprecation")
	public void refresh() {
		butRefresh.enable(false);
		Runa runa = new Runa();
		runa.refreshRun();
		runa = null;
		updateSysStaPanel();
		updateTemperaPanel();
		butRefresh.enable(true);

	}
	
	public MainInterfaceFrame_TimerTask task = new MainInterfaceFrame_TimerTask() {
		public void run() {
			refresh();
		}
	};

	
	public Timer timer = new Timer();
	public int timedRefresh() {
		try {
			RandomAccessFile raf = new RandomAccessFile("config.dat", "r");
			strPeriod = raf.readLine();
			strPeriodUnit = raf.readLine();
			strPeriodUnitNum = raf.readLine();
			raf.close();
		} catch (IOException e) {
			System.out.print("Read File Error" + e);
		}
		int i = 0;
		int j = Integer.valueOf(strPeriod);
		switch (Integer.valueOf(strPeriodUnitNum)) {
		case 0:
			i = 1;
			break;
		case 1:
			i = 1000;
			break;
		case 2:
			i = 60000;
			break;
		case 3:
			i = 3600000;
			break;
		}
		return i * j;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("查询")) {
			new QueryFrame(); 
		}
		if (e.getActionCommand().equals("配置刷新周期")) {
			new ConfPeriodFrame();
		}
		if (e.getActionCommand().equals("配置报警温度阀值")) {
			new ConfThresholdFrame();
		}
		if (e.getActionCommand().equals("关于节点温度监测器")) {
			new AboutFrame();
		}
		if (e.getActionCommand().equals("刷新")) {
			this.refresh();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 32; i++) {
			if (e.getSource() == label[i]) {
				new NodeInfoFrame(i + 1);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}