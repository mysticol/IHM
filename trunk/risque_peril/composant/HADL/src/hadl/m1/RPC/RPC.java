package hadl.m1.RPC;

import hadl.m2.connecteur.Connecteur;

public class RPC extends Connecteur {

	public RPC(String nom) {
		super(nom);
		
		this.setGlue("1", "glueAller", "2");
		this.setGlue("3", "glueRetour", "4");
	}

	public void glueAller(String cmd){
		// System.out.println(2 +" - " + cmd);
		this.notifier("glueAller", cmd);
	}
	
	public void glueRetour(String rep){
		// System.out.println(17 +" - " + rep);
		this.notifier("glueRetour", rep);
	}
}
