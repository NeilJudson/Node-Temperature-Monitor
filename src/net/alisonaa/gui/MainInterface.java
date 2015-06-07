package net.alisonaa.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import static net.alisonaa.homework3.Constants.*;

public class MainInterface extends JFrame implements ActionListener {
	private JPanel panelButton;
	private JButton butBarGraph;
	private JButton butPieGraph;

	private JPanel panelGraph;

	public MainInterface() {
		setSize(iMainInterWidth, iMainInterHeight);
		setTitle("JavaÓïÑÔ³ÌÐòÉè¼Æ_×÷Òµ3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		/* panelButton */
		panelButton = new JPanel();
		panelButton.setBounds(7, 6, iPanelButtonWidth, iPanelButtonHeight);
		panelButton.setLayout(null);

		butBarGraph = new JButton("Öù×´Í¼");
		butBarGraph.setBounds(0, 0, iButtonWidth, iButtonHeight);
		butBarGraph.addActionListener(this);

		butPieGraph = new JButton("±ý×´Í¼");
		butPieGraph.setBounds(100, 0, iButtonWidth, iButtonHeight);
		butPieGraph.addActionListener(this);

		panelButton.add(butBarGraph);
		panelButton.add(butPieGraph);

		add(panelButton);

		/* panelGraph */
		panelGraph = new LineGraph();
		panelGraph.setBounds(7, 56, iPanelGraphWidth, iPanelGraphHeight);
		panelGraph.setLayout(null);

		add(panelGraph);

		setVisible(true);
		System.out.println(panelGraph.getSize());
	}

	void drawBarGraph() {
		System.out.println("drawBarGraph");
	}

	void drawPieGraph() {
		System.out.println("drawPieGraph");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Öù×´Í¼"))
			drawBarGraph();
		else if (e.getActionCommand().equals("±ý×´Í¼"))
			drawPieGraph();
	}
}
