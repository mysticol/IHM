package hadl.m1.serveur;

import hadl.m2.composant.Composant;

public class SecurityDB extends Composant {

	public SecurityDB() {
		super();
		
		this.setPortIn(1, "testSecu");
		this.setPortOut("testSecu", 2);
	}

	public void testSecu(String cmd){
		this.notifier("testSecu", cmd);
	}

}
