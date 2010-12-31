package bean;

import java.io.Serializable;

public class Categorie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1082611814071358880L;
	
	private String nom;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Categorie(String nom) {
		this.nom = nom;
	}

	public Categorie() {

	}
	
}
