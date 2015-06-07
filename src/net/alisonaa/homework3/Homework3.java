package net.alisonaa.homework3;
import net.alisonaa.dao.DataFileAccessThread;
import net.alisonaa.gui.MainInterface;

public class Homework3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataFileAccessThread dfat = new DataFileAccessThread();
		dfat.start();
		MainInterface mi = new MainInterface();
	}
}
