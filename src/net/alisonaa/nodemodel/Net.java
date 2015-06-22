package net.alisonaa.nodemodel;

import static net.alisonaa.business.NodeTemperaMonitor.NODE_NUM;

public class Net {
	public Node[] node = new Node[NODE_NUM];
	
	public Net() {
		for (int i = 0; i < NODE_NUM; i++) {
			node[i] = new Node();
		}
	}
}