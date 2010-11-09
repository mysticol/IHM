package hadl.m1.serveur;

import hadl.m2.connecteur.Connecteur;

public class SQLQuery extends Connecteur {
	public SQLQuery(String nom) {
		super(nom);
		
		this.setGlue("1","glueAller","2");
		this.setGlue("3","glueRetour","4");
	}

	public void glueAller(String cmd){	
		// System.out.println(13 +" - " + cmd);
		this.notifier("glueAller", cmd);
	}
	
	public void glueRetour(String rep){	
		// System.out.println(15 +" - "+ rep);
		this.notifier("glueRetour", rep);
	}
}
