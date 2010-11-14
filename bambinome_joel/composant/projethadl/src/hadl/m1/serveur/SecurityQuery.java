package hadl.m1.serveur;

import hadl.m2.Connector;

public class SecurityQuery extends Connector {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecurityQuery() {
		super("SecurityQuery");
	}
	
	public String secuQMethod(String mess){
		return mess;
	}

}
