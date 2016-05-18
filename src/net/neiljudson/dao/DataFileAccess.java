package net.neiljudson.dao;

import java.io.IOException;
import java.io.RandomAccessFile;

import net.neiljudson.nodemodel.Node;

public class DataFileAccess {
	private Node node = null;

	public Node getNode() {
		return node;
	}

	public void run(int iNodeID) {
		RandomAccessFile raf = null;
		String strTemp = null;
		node = new Node();
		try {
			raf = new RandomAccessFile("data/" + iNodeID + ".dat", "r");
			while ((strTemp = raf.readLine()) != null) {
				node.addData(strTemp.split(" "));
				// node.addData(strTemp.substring(0, 10),
				// strTemp.substring(11, 19),
				// Integer.valueOf(strTemp.substring(20)));
			}
		} catch (IOException e) {
			System.out.print("Read File Error" + e);
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}