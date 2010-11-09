package hadl.m1.serveur;

import hadl.m2.composant.Composant;

public class ConnectionManager extends Composant {

	private String query;
	
	public ConnectionManager(String nom) {
		super(nom);
		query = "";
		
		this.setPortIn("External-Socket", "arriver");
		this.setPortIn("Security-Check","verif");
		this.setPortIn("DB-Query", "sortir");
		
		this.setPortOut("arriver", "Security-Check");
		this.setPortOut("sortir", "External-Socket");	
		this.setPortOut("lauchQuery","DB-Query");
	}

	public void arriver(String cmd){
		// System.out.println(3 +" - "+cmd);
		query = cmd;
		this.notifier("arriver", cmd);
	}
	
	public void sortir(String rep){
		// System.out.println(16 + " - " + rep);
		this.notifier("sortir", rep);		
	}
	
	public void verif(String rep){
		// System.out.println(11 +" - "+rep);
		if(rep.equals("OK")){
			this.launchQuery();
		}else{
			this.sortir(rep);
		}
	}

	private void launchQuery() {
		// System.out.println(12 +" - "+query);
		this.notifier("lauchQuery", query);	
	}
	
}
