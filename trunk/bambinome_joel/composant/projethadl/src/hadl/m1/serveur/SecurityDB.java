package hadl.m1.serveur;

import hadl.m2.Composant;


public class SecurityDB extends Composant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecurityDB() {
		super("SecurityDB");
		
	
	}
	
	public String secu(String mess){
		System.out.println("Securit� de merde");
		return mess;
	}

}
