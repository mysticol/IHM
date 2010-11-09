package hadl.m0.main;

import hadl.m1.CS.CS;

public class mainClientServeur {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CS cs = new CS("cs");
		cs.init();
		cs.start();
	}
}
