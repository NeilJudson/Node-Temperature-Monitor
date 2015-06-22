package net.alisonaa.business;

import net.alisonaa.dao.DataFileAccess;
import net.alisonaa.gui.MainInterfaceFrame;
import net.alisonaa.nodemodel.Net;

public class NodeTemperaMonitor {
	public static final int NODE_NUM = 32;
	
	public static Net net = new Net();
	public static int iDataUpdateFlag = 0;
	
	static class R implements Runnable {
		public synchronized void run() {
			DataFileAccess dfa = new DataFileAccess();
			for (int iNodeID = 1; iNodeID <= NODE_NUM; iNodeID++) {
				dfa.run(iNodeID);
				if (net.node[iNodeID - 1].iVerID != dfa.getNode().iVerID) {
					net.node[iNodeID - 1] = dfa.getNode();
					iDataUpdateFlag = 1;
				}
			}
		}
	}

	public static void main(String[] args) {
		Runnable r = new R();
		Thread t = new Thread(r);
		t.start();
		new MainInterfaceFrame();
	}
}
