package hald.testm2;

import hadl.Connector;

public class Connecteur extends Connector{

	public Connecteur() {
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
