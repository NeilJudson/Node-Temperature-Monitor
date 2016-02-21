package net.neiljudson.business;

import net.neiljudson.dao.Runa;
import net.neiljudson.gui.MainInterfaceFrame;
import net.neiljudson.nodemodel.Net;

public class NodeTemperaMonitor {
	public static final int NODE_NUM = 32;

	public static Net net = new Net();
	public static boolean iPeriodChanged = false;
	public static boolean iThresholdChanged = false;

	public static void main(String[] args) {
		new Thread(new Runa()).start();
		MainInterfaceFrame mif = new MainInterfaceFrame();
		int p = mif.timedRefresh();
		mif.timer.schedule(mif.task, p, p);
		while (true) {
			if (iPeriodChanged) {
				p = mif.timedRefresh();
				mif.task.setPeriod(p);
				mif.updatePeriodPanel();
				iPeriodChanged = false;
			}
			if (iThresholdChanged) {
				mif.updateThresholdPanel();
				iThresholdChanged = false;
			}
			System.out.print("");
		}
	}
}
