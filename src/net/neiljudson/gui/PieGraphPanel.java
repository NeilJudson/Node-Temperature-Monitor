package net.neiljudson.gui;

import static net.neiljudson.gui.NodeInfoFrame.PANEL_GRAPH_HEIGHT;
import static net.neiljudson.gui.NodeInfoFrame.PANEL_GRAPH_WIDTH;
import static net.neiljudson.lang.Math.cosAngle;
import static net.neiljudson.lang.Math.sinAngle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import net.neiljudson.business.NodeTemperaMonitor;

public class PieGraphPanel extends JPanel {
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

		Font f = new Font("ËÎÌå", Font.PLAIN, 12);
		g.setFont(f);

		double[] arriCount = count();
		Color[] arrColor = { Color.decode("#fec64e"), Color.decode("#fe9c34"),
				Color.decode("#fe661a"), Color.decode("#ff3c19"),
				Color.decode("#fe0501") };
		int startAngle = 0;
		int angle = 0;
		String[] arrstrLegend = { "  < 30 ¡æ", "  30 ~ 40 ¡æ", "  40 ~ 50 ¡æ", "  50 ~ 60 ¡æ", "  > 60 ¡æ" };
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