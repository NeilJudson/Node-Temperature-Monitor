package net.alisonaa.business;

import net.alisonaa.dao.Runa;
import net.alisonaa.gui.MainInterfaceFrame;
import net.alisonaa.nodemodel.Net;

public class NodeTemperaMonitor {
	public static final int NODE_NUM = 32;

	public static Net net = new Net();
	public static int iPeriodChanged = 0;
	public static int iThresholdChanged = 0;

	public static void main(String[] args) {
		Runnable r = new Runa();
		Thread t = new Thread(r);
		t.start();
		MainInterfaceFrame mif = new MainInterfaceFrame();
		int p = mif.timedRefresh();
		mif.timer.schedule(mif.task, p, p);
		while (true) {
			if (iPeriodChanged == 1) {
				p = mif.timedRefresh();
				mif.task.setPeriod(p);
				mif.updatePeriodPanel();
				iPeriodChanged = 0;
			}
			if (iThresholdChanged == 1) {
				mif.updateThresholdPanel();
				iThresholdChanged = 0;
			}
		}
	}
}
