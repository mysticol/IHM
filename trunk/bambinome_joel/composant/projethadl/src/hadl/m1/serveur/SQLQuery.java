package hadl.m1.serveur;

import hadl.m2.Connector;

public class SQLQuery extends Connector {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SQLQuery() {
		super("SQLQuery");
	}
	
	public String SQLMethod(String mess){
		return mess;
	}

}
