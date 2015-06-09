package net.alisonaa.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainInterface extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int MAIN_INTER_WIDTH = 530;
	private static final int MAIN_INTER_HEIGHT = 450;

	private static final int PANEL_BUTTON_WIDTH = 500;
	private static final int PANEL_BUTTON_HEIGHT = 50;

	private static final int BUTTON_WIDTH = 90;
	private static final int BUTTON_HEIGHT = 30;

	static final int PANEL_GRAPH_WIDTH = 500;
	static final int PANEL_GRAPH_HEIGHT = 350;

	private JPanel panelButton;
	private JButton butLineGraph;
	private JButton butPieGraph;

	private JPanel panelGraph;

	public MainInterface() {
		setSize(MAIN_INTER_WIDTH, MAIN_INTER_HEIGHT);
		setTitle("JavaÓïÑÔ³ÌÐòÉè¼Æ_×÷Òµ3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		/* panelButton */
		panelButton = new JPanel();
		panelButton.setBounds(7, 6, PANEL_BUTTON_WIDTH, PANEL_BUTTON_HEIGHT);
		panelButton.setLayout(null);

		butLineGraph = new JButton("ÕÛÏßÍ¼");
		butLineGraph.setBounds(0, 10, BUTTON_WIDTH, BUTTON_HEIGHT);
		butLineGraph.addActionListener(this);

		butPieGraph = new JButton("±ý×´Í¼");
		butPieGraph.setBounds(100, 10, BUTTON_WIDTH, BUTTON_HEIGHT);
		butPieGraph.addActionListener(this);

		panelButton.add(butLineGraph);
		panelButton.add(butPieGraph);

		add(panelButton);

		/* panelGraph */
		panelGraph = new JPanel();
		panelGraph.setBounds(7, 56, PANEL_GRAPH_WIDTH, PANEL_GRAPH_HEIGHT);
		panelGraph.setLayout(null);

		add(panelGraph);

		setVisible(true);
	}
	
	void drawLineGraph() {
		LineGraph lineGraph = new LineGraph();
		lineGraph.setBounds(0, 0, PANEL_GRAPH_WIDTH, PANEL_GRAPH_HEIGHT);
		lineGraph.setLayout(null);
		panelGraph.removeAll();
		panelGraph.add(lineGraph);
		panelGraph.repaint();
	}

	void drawPieGraph() {
		PieGraph pieGraph = new PieGraph();
		pieGraph.setBounds(0, 0, PANEL_GRAPH_WIDTH, PANEL_GRAPH_HEIGHT);
		pieGraph.setLayout(null);
		panelGraph.removeAll();
		panelGraph.add(pieGraph);
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
