package hadl.m1.serveur;

import hadl.m2.Composant;


public class ConnectionManager extends Composant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConnectionManager() {
		super("ConnectionManager");
		
		
	}
	
	public String entre(String mess){
		System.out.println("Entre dans le ConnectionManager");
		return mess;
	}
	
	public String sort(String mess){
		System.out.println("Sort du ConnectionManager");
		return mess;
	}

}
