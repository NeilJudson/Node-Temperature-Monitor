package net.neiljudson.nodemodel;

import static net.neiljudson.business.NodeTemperaMonitor.NODE_NUM;

public class Net {
	public Node[] node = null;
	
	public Net() {
		node = new Node[NODE_NUM];
		for (int i = 0; i < NODE_NUM; i++) {
			node[i] = new Node();
		}
	}
}