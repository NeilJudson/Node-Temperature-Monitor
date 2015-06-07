package net.alisonaa.nodemodel;

import java.util.ArrayList;

public class Node {
	public ArrayList<String> alstrDate = new ArrayList<String>();
	public ArrayList<String> alstrTime = new ArrayList<String>();
	public ArrayList<Integer> aliTempera = new ArrayList<Integer>();
	public int iNum = 0;
	public int iMaxTempera = 0;
	public int iMaxTemperaNum = 0;

	public void addData(String strDate, String strTime, int iTempera) {
		alstrDate.add(strDate);
		alstrTime.add(strTime);
		aliTempera.add(iTempera);
		maxTempera(iTempera);
		iNum++;
	}

	private void maxTempera(int tempera) {
		if (tempera > iMaxTempera) {
			iMaxTempera = tempera;
			iMaxTemperaNum = 1;
		} else if (tempera == iMaxTempera) {
			iMaxTemperaNum++;
		} else {
			// 最高温度不变
		}
	}
}