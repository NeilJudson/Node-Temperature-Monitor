package net.alisonaa.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NodeInfoFrame extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int NODE_INFO_WIDTH = 530;
	private static final int NODE_INFO_HEIGHT = 450;

	private static final int PANEL_BUTTON_WIDTH = 500;
	private static final int PANEL_BUTTON_HEIGHT = 50;

	private static final int BUTTON_WIDTH = 90;
	private static final int BUTTON_HEIGHT = 30;

	static final int PANEL_GRAPH_WIDTH = 500;
	static final int PANEL_GRAPH_HEIGHT = 350;

	private JPanel panelGraph = new JPanel();

	public NodeInfoFrame() {
		setSize(NODE_INFO_WIDTH, NODE_INFO_HEIGHT);
		setTitle("JavaÓïÑÔ³ÌÐòÉè¼Æ_×÷Òµ3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		/* panelButton */
		JPanel panelButton = new JPanel();
		JButton butLineGraph = new JButton("ÕÛÏßÍ¼");
		JButton butPieGraph = new JButton("±ý×´Í¼");

		panelButton.setBounds(7, 6, PANEL_BUTTON_WIDTH, PANEL_BUTTON_HEIGHT);
		panelButton.setLayout(null);
		
		butLineGraph.setBounds(0, 10, BUTTON_WIDTH, BUTTON_HEIGHT);
		butLineGraph.addActionListener(this);
		
		butPieGraph.setBounds(100, 10, BUTTON_WIDTH, BUTTON_HEIGHT);
		butPieGraph.addActionListener(this);

		panelButton.add(butLineGraph);
		panelButton.add(butPieGraph);

		add(panelButton);

		/* panelGraph */
		panelGraph.setBounds(7, 56, PANEL_GRAPH_WIDTH, PANEL_GRAPH_HEIGHT);
		panelGraph.setLayout(null);

		add(panelGraph);

		setVisible(true);
	}
	
	void drawLineGraph() {
		LineGraphPanel panelLineGraph = new LineGraphPanel();
		panelLineGraph.setBounds(0, 0, PANEL_GRAPH_WIDTH, PANEL_GRAPH_HEIGHT);
		panelLineGraph.setLayout(null);
		panelGraph.removeAll();
		panelGraph.add(panelLineGraph);
		panelGraph.repaint();
	}

	void drawPieGraph() {
		PieGraphPanel panelPieGraph = new PieGraphPanel();
		panelPieGraph.setBounds(0, 0, PANEL_GRAPH_WIDTH, PANEL_GRAPH_HEIGHT);
		panelPieGraph.setLayout(null);
		panelGraph.removeAll();
		panelGraph.add(panelPieGraph);
		panelGraph.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("ÕÛÏßÍ¼"))
			drawLineGraph();
		else if (e.getActionCommand().equals("±ý×´Í¼"))
			drawPieGraph();
	}
}
