package net.alisonaa.dao;

import java.io.IOException;
import java.io.RandomAccessFile;

import net.alisonaa.nodemodel.Node;

public class DataFileAccess {
	private Node node = new Node();

	public Node getNode() {
		return node;
	}

	public void run(int iNodeID) {
		String strTemp = new String();
		try {
			RandomAccessFile raf = new RandomAccessFile("data/" + iNodeID + ".dat", "r");
			while ((strTemp = raf.readLine()) != null) {
				node.addData(strTemp.substring(0, 10),
						strTemp.substring(11, 19),
						Integer.valueOf(strTemp.substring(20)));
			}
			raf.close();
		} catch (IOException e) {
			System.out.print("Read File Error" + e);
		}
		strTemp = null;
	}
}