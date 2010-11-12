package hadl.m2.testm2;

import hadl.m2.Connector;

public class ConnecteurTest extends Connector{

	public ConnecteurTest() {
		super("TestConnect");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public String rien(String rien){
		System.out.println("passage par le connecteur :" + this.getName());
		return rien;
	}
}
