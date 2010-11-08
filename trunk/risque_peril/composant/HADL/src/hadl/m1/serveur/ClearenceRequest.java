package hadl.m1.serveur;

import hadl.m2.connecteur.Connecteur;

public class ClearenceRequest extends Connecteur {

	public ClearenceRequest() {
		super();
		this.setGlue(1,"glue",2);
	}

	public void glue(String cmd){	
		this.notifier("glue", cmd);
	}
	
	
}
