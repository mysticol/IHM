package bean;

import java.util.LinkedList;

public class Ligne implements Vie{
	
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
