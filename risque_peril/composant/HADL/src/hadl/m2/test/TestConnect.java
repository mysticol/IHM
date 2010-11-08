package hadl.m2.test;

import hadl.m2.connecteur.Connecteur;

public class TestConnect extends Connecteur {

	public TestConnect() {
		super();
		this.setGlue(1, "article", 2);

	}

	public void article(String val){
		val = val + " venant de ";
		this.notifier("article",val);
	}
}
