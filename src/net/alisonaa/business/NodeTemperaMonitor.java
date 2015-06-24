package net.alisonaa.business;

import net.alisonaa.dao.Runa;
import net.alisonaa.gui.MainInterfaceFrame;
import net.alisonaa.nodemodel.Net;

public class NodeTemperaMonitor {
	public static final int NODE_NUM = 32;

	public static Net net = new Net();
	public static int iPeriodChanged = 0;

	public static void main(String[] args) {
		Runnable r = new Runa();
		Thread t = new Thread(r);
		t.start();
		MainInterfaceFrame mif = new MainInterfaceFrame();
		mif.timedRefresh();
		while (true) {
			if (iPeriodChanged == 1) {
				mif.task.cancel();
				mif.task.run();
				mif.timedRefresh();
				iPeriodChanged = 0;
			}
		}
	}
}
