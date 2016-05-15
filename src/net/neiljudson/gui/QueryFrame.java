package net.neiljudson.gui;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import net.neiljudson.business.NodeTemperaMonitor;

public class QueryFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static QueryParameters qp = new QueryParameters();
	private Choice choice = null;
	private JTextField textField = null;

	private JPanel panel3 = null;
	private JTable table = null;

	public QueryFrame() {
		setSize(350, 150);
		setTitle("查询");
		setLayout(null);
		setLocation(530, 450);

		/* panelQuery */
		JPanel panelQuery = new JPanel();
		panelQuery.setLayout(null);
		panelQuery.setBounds(7, 6, 320, 50);

		JLabel labNetID = new JLabel("查询  NetID");
		labNetID.setBounds(0, 0, 70, 50);

		choice = new Choice();
		choice.setBounds(70, 15, 50, 50);
		choice.add("1");

		JLabel labNodeID = new JLabel("NodeID");
		labNodeID.setBounds(125, 0, 50, 50);

		textField = new JTextField(20);
		textField.setBounds(175, 12, 80, 26);

		JButton button3 = new JButton("查询");
		button3.setBounds(260, 10, 60, 30);
		button3.addActionListener(this);

		panelQuery.add(labNetID);
		panelQuery.add(choice);
		panelQuery.add(labNodeID);
		panelQuery.add(textField);
		panelQuery.add(button3);

		add(panelQuery);

		/* panel1 */
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(57, 6, 320, 50);

		JLabel label = new JLabel("温度报告");
		label.setBounds(0, 0, 320, 50);

		panel1.add(label);

		add(panel1);

		/* panel3 */
		panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBounds(7, 56, 320, 41);

		add(panel3);

		setVisible(true);
	}

	private void panel2() {
		/* panel2 */
		String[] columnNames = new String[qp.iNodeNum + 1];
		Object[][] cellData = new Object[1][qp.iNodeNum + 1];
		columnNames[0] = "节点号";
		cellData[0][0] = "温度";
		int iNodeID = 0;
		for (int i = 0; i < qp.iNodeNum; i++) {
			iNodeID = Integer.valueOf(qp.strNodeID[i]);
			columnNames[i + 1] = qp.strNetID + "-" + qp.strNodeID[i];
			cellData[0][i + 1] = NodeTemperaMonitor.net.node[iNodeID - 1].aliTempera
					.get(NodeTemperaMonitor.net.node[iNodeID - 1].iNum - 1);
		}
		table = new JTable(cellData, columnNames);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, dtcr);
		table.setEnabled(false);

		JScrollPane panel2 = new JScrollPane(table);
		panel2.setBounds(0, 0, 320, 41);

		panel3.removeAll();
		panel3.add(panel2);
		panel3.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("查询")) {
			qp.strNetID = choice.getSelectedItem();
			String strTemp = textField.getText();
			char[] cArr = strTemp.toCharArray();
			int iNodeNum = 0;
			int iSpaceLast = -1;
			for (int i = 0; i < strTemp.length(); i++) {
				if (cArr[i] == ' ') {
					if (i != iSpaceLast + 1) {
						qp.strNodeID[iNodeNum] = strTemp.substring(iSpaceLast + 1, i);
						iNodeNum++;
					}
					iSpaceLast = i;
				} else if (i == cArr.length - 1) {
					qp.strNodeID[iNodeNum] = strTemp.substring(iSpaceLast + 1);
					iNodeNum++;
				}
			}
			qp.iNodeNum = iNodeNum;
			panel2();
		}
	}
}

class QueryParameters {
	String strNetID = "1";
	String[] strNodeID = new String[10];
	int iNodeNum = 0;
}