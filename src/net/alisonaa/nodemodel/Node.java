package net.alisonaa.nodemodel;

import java.util.ArrayList;

public class Node {
	public ArrayList<String> alstrDate = new ArrayList<String>();
	public ArrayList<String> alstrTime = new ArrayList<String>();
	public ArrayList<Integer> aliTempera = new ArrayList<Integer>();
	public int iNum = 0;
	public int iMaxTempera = 0;
	public int iMaxTemperaNum = 0;
	public int iVerID = 0;

	public void addData(String strDate, String strTime, int iTempera) {
		alstrDate.add(strDate);
		alstrTime.add(strTime);
		aliTempera.add(iTempera);
		maxTempera(iTempera);
		iNum++;
		updateVerID();
	}
	
	public void addData(String[] strArrData) {
		alstrDate.add(strArrData[0]);
		alstrTime.add(strArrData[1]);
		aliTempera.add(Integer.valueOf(strArrData[2]));
		maxTempera(Integer.valueOf(strArrData[2]));
		iNum++;
		updateVerID();
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

	private void updateVerID() {
		if (iVerID < 1000)
			iVerID++;
		else
			iVerID = 0;
	}
}