package net.neiljudson.gui;

import java.lang.reflect.Field;
import java.util.TimerTask;

public abstract class MainInterfaceFrame_TimerTask extends TimerTask {
	public void setPeriod(long period) {
		setDeclaredField(TimerTask.class, this, "period", period);
	}

	// 通过反射修改字段的值
	static boolean setDeclaredField(Class<?> clazz, Object obj, String name, Object value) {
		try {
			Field field = clazz.getDeclaredField(name);
			field.setAccessible(true);
			field.set(obj, value);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}