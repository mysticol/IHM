package bean;

import java.io.Serializable;

public class Equipement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3908927498158709027L;
	
	private String nom;
	private String description;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Equipement(String nom, String description) {
		this.nom = nom;
		this.description = description;
	}
	public Equipement() {
	
	}
	
}
