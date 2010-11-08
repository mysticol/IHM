package hadl.m1.serveur;

import hadl.Connector;

public class SQLQuery extends Connector {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SQLQuery(String name) {
		super(name);
	}
	
	public String SQLMethod(String mess){
		return mess;
	}

}
