package net.alisonaa.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;

import javax.accessibility.AccessibleContext;
import javax.swing.JPanel;
import javax.swing.plaf.PanelUI;

public class LineGraph extends JPanel {
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(Color.WHITE);
		
		// draw x axis
		g.setColor(Color.BLACK);
		g.drawLine(10, 339, 10, 10);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (arrow[i][j] == 1)
					g.drawLine(6 + i, 9 - j, 6 + i, 9 - j);
			}
		}

		// draw y axis
		g.drawLine(10, 339, 489, 339);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (arrow[i][j] == 1)
					g.drawLine(490 + j, 335 + i, 490 + j, 335 + i);
			}
		}
	}

	int[][] arrow = { 	{ 1, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 1, 1, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 1, 1, 1, 0, 0, 0, 0 },
						{ 0, 0, 0, 1, 1, 1, 1, 0, 0 },
						{ 1, 1, 1, 1, 1, 1, 1, 1, 1 },
						{ 0, 0, 0, 1, 1, 1, 1, 0, 0 },
						{ 0, 0, 1, 1, 1, 0, 0, 0, 0 },
						{ 0, 1, 1, 0, 0, 0, 0, 0, 0 },
						{ 1, 0, 0, 0, 0, 0, 0, 0, 0 } };
}
