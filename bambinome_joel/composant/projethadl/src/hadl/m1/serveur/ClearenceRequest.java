package hadl.m1.serveur;

import hadl.m2.Connector;

public class ClearenceRequest extends Connector {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClearenceRequest() {
		super("ClearenceRequest");
	}

	public String crMethod(String mess){
		return mess;
	}
}
