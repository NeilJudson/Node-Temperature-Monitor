package net.neiljudson.gui;

import static net.neiljudson.awt.Graphics.drawPoint;
import static net.neiljudson.awt.Graphics.drawSquarePoint;
import static net.neiljudson.gui.NodeInfoFrame.PANEL_GRAPH_HEIGHT;
import static net.neiljudson.gui.NodeInfoFrame.PANEL_GRAPH_WIDTH;
import static net.neiljudson.lang.Math.cosAngle;
import static net.neiljudson.lang.Math.sinAngle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.neiljudson.business.NodeTemperaMonitor;

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
	private JPanel panelGraph = null;
	
	public NodeInfoFrame(int iNodeID) {
		this.iNodeID = iNodeID;

		setSize(NODE_INFO_WIDTH, NODE_INFO_HEIGHT);
		setTitle("节点" + MainInterfaceFrame.getNetID() + "-" + iNodeID + "温度详情");
		setLayout(null);
		setLocation(530, 0);

		/* panelButton */
		JPanel panelButton = new JPanel();
		panelButton.setBounds(7, 6, PANEL_BUTTON_WIDTH, PANEL_BUTTON_HEIGHT);
		panelButton.setLayout(null);

		JButton butLineGraph = new JButton("折线图");
		butLineGraph.setBounds(0, 10, BUTTON_WIDTH, BUTTON_HEIGHT);
		butLineGraph.addActionListener(this);

		JButton butPieGraph = new JButton("饼状图");
		butPieGraph.setBounds(100, 10, BUTTON_WIDTH, BUTTON_HEIGHT);
		butPieGraph.addActionListener(this);

		panelButton.add(butLineGraph);
		panelButton.add(butPieGraph);
		butLineGraph = null;
		butPieGraph = null;

		add(panelButton);
		panelButton = null;

		/* panelGraph */
		panelGraph = new JPanel();
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
		if (e.getActionCommand().equals("折线图"))
			drawLineGraph();
		else if (e.getActionCommand().equals("饼状图"))
			drawPieGraph();
	}
}

class LineGraphPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int ORIGIN_POINT_X = 25;
	private static final int ORIGIN_POINT_Y = PANEL_GRAPH_HEIGHT - 1 - 25;
	private static final int X_AXIS_LENGTH = 435;
	private static final int Y_AXIS_LENGTH = 285;
	private static final int X_AXIS_END_X = ORIGIN_POINT_X + X_AXIS_LENGTH - 1;
	private static final int Y_AXIS_END_Y = ORIGIN_POINT_Y - Y_AXIS_LENGTH + 1;
	
	private int iNodeID = 0;
	
	private String[] arrow = {
			"*........",
			".**......",
			"..***....",
			"...****..",
			"*********",
			"...****..",
			"..***....",
			".**......",
			"*........" };
	
	public LineGraphPanel(int iNodeID) {
		this.iNodeID = iNodeID;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(Color.WHITE);

		Font f = new Font("宋体", Font.PLAIN, 12);
		g.setFont(f);
		g.setColor(Color.BLACK);

		/* draw x axis */
		// draw arrow
		g.drawLine(ORIGIN_POINT_X, ORIGIN_POINT_Y, X_AXIS_END_X, ORIGIN_POINT_Y);
		int row = 0;
		int col = 0;
		for (String str : arrow) {
			col = 0;
			for (char c : str.toCharArray()) {
				if (c == '*')
					drawPoint(g, X_AXIS_END_X + 1 + col, ORIGIN_POINT_Y - 4 + row);
				col++;
			}
			row++;
		}
		// draw scale
		for (int i = 0; i < 24; i++) {
			g.drawLine(ORIGIN_POINT_X + 10 + i * 18, ORIGIN_POINT_Y,
					ORIGIN_POINT_X + 10 + i * 18, ORIGIN_POINT_Y + 2);
			if (i > 9)
				g.drawString(Integer.toString(i), ORIGIN_POINT_X + 5 + i * 18,
						ORIGIN_POINT_Y + 15);
			else
				g.drawString(Integer.toString(i), ORIGIN_POINT_X + 8 + i * 18,
						ORIGIN_POINT_Y + 15);
		}
		g.drawString("时", X_AXIS_END_X + 15, ORIGIN_POINT_Y + 4);

		/* draw y axis */
		// draw arrow
		g.drawLine(ORIGIN_POINT_X, ORIGIN_POINT_Y, ORIGIN_POINT_X, Y_AXIS_END_Y);
		row = 0;
		for (String str : arrow) {
			col = 0;
			for (char c : str.toCharArray()) {
				if (c == '*')
					drawPoint(g, ORIGIN_POINT_X - 4 + row, Y_AXIS_END_Y - 1 - col);
				col++;
			}
			row++;
		}
		// draw scale
		for (int i = 0; i < 10; i++) {
			g.drawLine(ORIGIN_POINT_X, ORIGIN_POINT_Y - i * 30,
					ORIGIN_POINT_X - 2, ORIGIN_POINT_Y - i * 30);
			if (i > 0)
				g.drawString(Integer.toString(i * 10), ORIGIN_POINT_X - 16,
						ORIGIN_POINT_Y + 5 - i * 30);
			else
				g.drawString(" " + Integer.toString(i * 10),
						ORIGIN_POINT_X - 16, ORIGIN_POINT_Y + 5 - i * 30);
		}
		g.drawString("温度/℃", ORIGIN_POINT_X - 20, Y_AXIS_END_Y - 15);
		
		/* draw line */
		int k = NodeTemperaMonitor.net.node[iNodeID - 1].iNum - 1;
		for (int i = 0; i < k; i++) {
			g.drawLine(ORIGIN_POINT_X + 10 + i * 18, ORIGIN_POINT_Y - NodeTemperaMonitor.net.node[iNodeID - 1].aliTempera.get(i) * 3,
					ORIGIN_POINT_X + 10 + (i + 1) * 18, ORIGIN_POINT_Y - NodeTemperaMonitor.net.node[iNodeID - 1].aliTempera.get(i + 1) * 3);
			drawSquarePoint(g, ORIGIN_POINT_X + 10 + i * 18, ORIGIN_POINT_Y - NodeTemperaMonitor.net.node[iNodeID - 1].aliTempera.get(i) * 3, 3);
		}
		drawSquarePoint(g, ORIGIN_POINT_X + 10 + k * 18, ORIGIN_POINT_Y - NodeTemperaMonitor.net.node[iNodeID - 1].aliTempera.get(k) * 3, 3);
	}
}

class PieGraphPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int RADIUS = 120;
	private static final int DIAMETER = RADIUS * 2;
	private static final int CENTRAL_POINT_X = PANEL_GRAPH_WIDTH / 2 - 80;
	private static final int CENTRAL_POINT_Y = PANEL_GRAPH_HEIGHT / 2 + 30;
	private static final int ORIGIN_POINT_X = CENTRAL_POINT_X - RADIUS;
	private static final int ORIGIN_POINT_Y = CENTRAL_POINT_Y - RADIUS;
	private static final int LEGEND_X = PANEL_GRAPH_WIDTH - 120;
	private static final int LEGEND_Y = 100;
	private static final int LEGEND_WIDTH = 36;
	private static final int LEGEND_HEIGHT = 14;
	
	private int iNodeID = 0;
	
	public PieGraphPanel(int iNodeID) {
		this.iNodeID = iNodeID;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(Color.WHITE);

		Font f = new Font("宋体", Font.PLAIN, 12);
		g.setFont(f);

		double[] arriCount = count();
		Color[] arrColor = { Color.decode("#fec64e"), Color.decode("#fe9c34"),
				Color.decode("#fe661a"), Color.decode("#ff3c19"),
				Color.decode("#fe0501") };
		int startAngle = 0;
		int angle = 0;
		String[] arrstrLegend = { "  < 30 ℃", "  30 ~ 40 ℃", "  40 ~ 50 ℃", "  50 ~ 60 ℃", "  > 60 ℃" };
		for (int i = 0; i < 5; i++) {
			if (arriCount[i] == 0)
				break;
			g.setColor(arrColor[i]);
			angle = (int) (arriCount[i] * 360);
			g.fillArc(ORIGIN_POINT_X, ORIGIN_POINT_Y, DIAMETER, DIAMETER,
					startAngle, angle);
			startAngle = startAngle + angle;
			g.fillRect(LEGEND_X, LEGEND_Y + i * 40, LEGEND_WIDTH, LEGEND_HEIGHT);
			g.setColor(Color.BLACK);
			g.drawString(arrstrLegend[i], LEGEND_X + LEGEND_WIDTH, LEGEND_Y + i * 40 + LEGEND_HEIGHT);
		}
		
		startAngle = 0;
		for (int i = 0; i < 5; i++) {
			if (arriCount[i] == 0)
				break;
			g.setColor(Color.WHITE);
			angle = (int) (arriCount[i] * 360);
			g.drawLine(CENTRAL_POINT_X, CENTRAL_POINT_Y,
					CENTRAL_POINT_X + (int) Math.floor((RADIUS * cosAngle(startAngle))),
					CENTRAL_POINT_Y - (int) Math.floor((RADIUS * sinAngle(startAngle))));
			g.setColor(Color.BLACK);
			g.drawString(
					Math.floor(arriCount[i] * 10000) / 100 + "%",
					CENTRAL_POINT_X + (int) Math.floor(((RADIUS + 20) * cosAngle(startAngle + angle / 2))),
					CENTRAL_POINT_Y - (int) Math.floor(((RADIUS + 20) * sinAngle(startAngle + angle / 2))));
			startAngle = startAngle + angle;
		}
	}

	private double[] count() {
		double[] arr = { 0, 0, 0, 0, 0 };
		int k = NodeTemperaMonitor.net.node[iNodeID - 1].iNum;
		for (int i = 0; i < k; i++) {
			int t = NodeTemperaMonitor.net.node[iNodeID - 1].aliTempera.get(i);
			if (t < 30)
				arr[0]++;
			else if (t >= 60)
				arr[4]++;
			else {
				t = t / 10;
				arr[t - 2]++;
			}
		}
		for (int j = 0; j < 5; j++) {
			arr[j] = arr[j] / k;
		}
		return arr;
	}
}