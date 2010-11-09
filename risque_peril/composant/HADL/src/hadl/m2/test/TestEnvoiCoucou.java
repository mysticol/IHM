package hadl.m2.test;

import hadl.m2.composant.Composant;

public class TestEnvoiCoucou extends Composant {

	public TestEnvoiCoucou(String nom) {
		super(nom);
		this.setPortIn("1", "coucou");
		this.setPortOut("coucou", "2");
	}

	public void coucou(){
		String val = "coucou";
		this.notifier("coucou",val);
	}
}
