package net.neiljudson.dao;

import net.neiljudson.business.NodeTemperaMonitor;
import static net.neiljudson.business.NodeTemperaMonitor.NODE_NUM;

public class Runa implements Runnable {
	public void run() {
		for (int iNodeID = 1; iNodeID <= NODE_NUM; iNodeID++) {
			DataFileAccess dfa = new DataFileAccess();
			dfa.run(iNodeID);
			if (NodeTemperaMonitor.net.node[iNodeID - 1].iVerID != dfa.getNode().iVerID) {
				NodeTemperaMonitor.net.node[iNodeID - 1] = dfa.getNode();
			}
		}
	}

	public void refreshRun() {
		for (int iNodeID = 1; iNodeID <= NODE_NUM; iNodeID++) {
			DataFileAccess dfa = new DataFileAccess();
			dfa.run(iNodeID);
			NodeTemperaMonitor.net.node[iNodeID - 1] = dfa.getNode();
		}
	}
}
