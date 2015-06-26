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

	private int iNodeID = 0; 
	private JPanel panelGraph = new JPanel();
	
	public NodeInfoFrame(int iNodeID) {
		this.iNodeID = iNodeID;

		setSize(NODE_INFO_WIDTH, NODE_INFO_HEIGHT);
		setTitle("½Úµã" + MainInterfaceFrame.getNetID() + "-" + iNodeID + "ÎÂ¶ÈÏêÇé");
		setLayout(null);
		setLocation(530, 0);

		/* panelButton */
		JPanel panelButton = new JPanel();
		panelButton.setBounds(7, 6, PANEL_BUTTON_WIDTH, PANEL_BUTTON_HEIGHT);
		panelButton.setLayout(null);

		JButton butLineGraph = new JButton("ÕÛÏßÍ¼");
		butLineGraph.setBounds(0, 10, BUTTON_WIDTH, BUTTON_HEIGHT);
		butLineGraph.addActionListener(this);

		JButton butPieGraph = new JButton("±ý×´Í¼");
		butPieGraph.setBounds(100, 10, BUTTON_WIDTH, BUTTON_HEIGHT);
		butPieGraph.addActionListener(this);

		panelButton.add(butLineGraph);
		panelButton.add(butPieGraph);
		butLineGraph = null;
		butPieGraph = null;

		add(panelButton);
		panelButton = null;

		/* panelGraph */
		panelGraph.setBounds(7, 56, PANEL_GRAPH_WIDTH, PANEL_GRAPH_HEIGHT);
		panelGraph.setLayout(null);

		add(panelGraph);

		setVisible(true);
	}
	
	private void drawLineGraph() {
		LineGraphPanel panelLineGraph = new LineGraphPanel(iNodeID);
		panelLineGraph.setBounds(0, 0, PANEL_GRAPH_WIDTH, PANEL_GRAPH_HEIGHT);
		panelLineGraph.setLayout(null);
		panelGraph.removeAll();
		panelGraph.add(panelLineGraph);
		panelGraph.repaint();
		panelLineGraph = null;
	}

	private void drawPieGraph() {
		PieGraphPanel panelPieGraph = new PieGraphPanel(iNodeID);
		panelPieGraph.setBounds(0, 0, PANEL_GRAPH_WIDTH, PANEL_GRAPH_HEIGHT);
		panelPieGraph.setLayout(null);
		panelGraph.removeAll();
		panelGraph.add(panelPieGraph);
		panelGraph.repaint();
		panelPieGraph = null;
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