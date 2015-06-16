package net.alisonaa.gui;

import static net.alisonaa.awt.Graphics.drawPoint;
import static net.alisonaa.awt.Graphics.drawSquarePoint;
import static net.alisonaa.gui.NodeInfoFrame.PANEL_GRAPH_HEIGHT;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import net.alisonaa.business.NodeTemperaMonitor;

public class LineGraphPanel extends JPanel {
	
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
	
	private int[][] arrow = { 	{ 1, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 1, 1, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 1, 1, 1, 0, 0, 0, 0 },
								{ 0, 0, 0, 1, 1, 1, 1, 0, 0 },
								{ 1, 1, 1, 1, 1, 1, 1, 1, 1 },
								{ 0, 0, 0, 1, 1, 1, 1, 0, 0 },
								{ 0, 0, 1, 1, 1, 0, 0, 0, 0 },
								{ 0, 1, 1, 0, 0, 0, 0, 0, 0 },
								{ 1, 0, 0, 0, 0, 0, 0, 0, 0 } };

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(Color.WHITE);

		Font f = new Font("ËÎÌå", Font.PLAIN, 12);
		g.setFont(f);
		g.setColor(Color.BLACK);

		/* draw x axis */
		// draw arrow
		g.drawLine(ORIGIN_POINT_X, ORIGIN_POINT_Y, X_AXIS_END_X, ORIGIN_POINT_Y);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (arrow[i][j] == 1)
					drawPoint(g, X_AXIS_END_X + 1 + j, ORIGIN_POINT_Y - 4 + i);
			}
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
		g.drawString("Ê±", X_AXIS_END_X + 15, ORIGIN_POINT_Y + 4);

		/* draw y axis */
		// draw arrow
		g.drawLine(ORIGIN_POINT_X, ORIGIN_POINT_Y, ORIGIN_POINT_X, Y_AXIS_END_Y);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (arrow[i][j] == 1)
					drawPoint(g, ORIGIN_POINT_X - 4 + i, Y_AXIS_END_Y - 1 - j);
			}
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
		g.drawString("ÎÂ¶È/¡æ", ORIGIN_POINT_X - 20, Y_AXIS_END_Y - 15);
		
		/* draw line */
		int k = NodeTemperaMonitor.node.iNum - 1;
		for (int i = 0; i < k; i++) {
			g.drawLine(ORIGIN_POINT_X + 10 + i * 18, ORIGIN_POINT_Y - NodeTemperaMonitor.node.aliTempera.get(i) * 3,
					ORIGIN_POINT_X + 10 + (i + 1) * 18, ORIGIN_POINT_Y - NodeTemperaMonitor.node.aliTempera.get(i + 1) * 3);
			drawSquarePoint(g, ORIGIN_POINT_X + 10 + i * 18, ORIGIN_POINT_Y - NodeTemperaMonitor.node.aliTempera.get(i) * 3, 3);
		}
		drawSquarePoint(g, ORIGIN_POINT_X + 10 + k * 18, ORIGIN_POINT_Y - NodeTemperaMonitor.node.aliTempera.get(k) * 3, 3);
	}
}
