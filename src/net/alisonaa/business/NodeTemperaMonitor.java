package net.alisonaa.business;

import net.alisonaa.dao.DataFileAccess;
import net.alisonaa.gui.MainInterface;
import net.alisonaa.nodemodel.Node;

public class NodeTemperaMonitor {
	public static Node node = new Node();
	public static int iDataUpdateFlag = 0;
	static class R implements Runnable {
		public synchronized void run() {
			DataFileAccess dfa = new DataFileAccess();
			dfa.run();
			if(node.iVerID != dfa.getNode().iVerID) {
				node = dfa.getNode();
				iDataUpdateFlag = 1;
			}
		}
	}

	public static void main(String[] args) {
		Runnable r = new R();
		Thread t = new Thread(r);
		t.start();
		new MainInterface();
	}

}
