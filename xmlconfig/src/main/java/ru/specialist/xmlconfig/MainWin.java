package ru.specialist.xmlconfig;

public class MainWin {
	
	private MainWin() {}
	
	private static volatile MainWin instance = null;
	
	public synchronized static MainWin getInstance() {
		if (instance == null)
			instance = new MainWin();
		return instance;
	}

}
