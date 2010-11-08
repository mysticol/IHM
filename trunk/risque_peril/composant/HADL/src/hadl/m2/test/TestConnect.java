package hadl.m2.test;

import hadl.m2.connecteur.Connecteur;

public class TestConnect extends Connecteur {

	public TestConnect() {
		super();
		
		this.setGlue(1, "article");
		
	}

	public void article(String val){
		Object[] data = new Object[2];
		data[0] = 2;
		data[1] = val + " venant de ";
		this.setChanged();
		this.notifyObservers(data);
	}
	
	
}
