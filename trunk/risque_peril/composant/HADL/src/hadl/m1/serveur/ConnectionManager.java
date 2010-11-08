package hadl.m1.serveur;

import hadl.m2.composant.Composant;

public class ConnectionManager extends Composant {

	public ConnectionManager() {
		super();
		
		this.setPortIn(1, "arriver");
		this.setPortOut("arriver", 2);
		
		this.setPortIn(3, "sortir");
		this.setPortOut("sortir", 4);		
	}

	public void arriver(String cmd){
		this.notifier("arriver", cmd);		
	}
	
	public void sortir(String rep){
		this.notifier("sortir", rep);		
	}
	
}
