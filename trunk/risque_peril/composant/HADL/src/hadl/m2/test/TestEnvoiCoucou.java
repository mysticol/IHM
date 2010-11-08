package hadl.m2.test;

import hadl.m2.composant.Composant;

public class TestEnvoiCoucou extends Composant {

	public TestEnvoiCoucou() {
		super();
		this.setPort(1, "coucou");
	}

	public void coucou(){
		Object[] data = new Object[2];
		data[0] = 2;
		data[1] = "coucou";
		this.setChanged();
		this.notifyObservers(data);
	}
}
