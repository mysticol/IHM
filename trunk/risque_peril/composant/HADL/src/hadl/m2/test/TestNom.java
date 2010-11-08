package hadl.m2.test;

import hadl.m2.composant.Composant;

public class TestNom extends Composant {

	public TestNom() {
		super();
		this.setPortIn(1, "nom");
		this.setPortOut("nom", 2);
	}

	public void nom(String val){
		val = val + "ta mere !!!";
		this.notifier("nom", val);
	}
	
}
