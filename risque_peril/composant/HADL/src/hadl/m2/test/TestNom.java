package hadl.m2.test;

import hadl.m2.composant.Composant;

public class TestNom extends Composant {

	public TestNom() {
		super();
		this.setPort(1, "nom");
	}

	public void nom(String val){
		Object[] data = new Object[2];
		data[0] = 2;
		data[1] = val + "ta mere !!!";
		this.setChanged();
		this.notifyObservers(data);
	}
	
}
