package bean.vie;

import java.io.Serializable;
import java.util.LinkedList;

public class Ligne implements Vie, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1461466061235521162L;
	
	private LinkedList<Case> listeCase;

	public LinkedList<Case> getListeCase() {
		return listeCase;
	}

	public void setListeCase(LinkedList<Case> listeCase) {
		this.listeCase = listeCase;
	}

	public Ligne(LinkedList<Case> listeCase) {
		this.listeCase = listeCase;
	}

	public Ligne() {
	
	}
	
	
	
	

}
