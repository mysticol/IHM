package bean;

import java.io.Serializable;

public abstract class Numeric implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8857917249701621551L;
	
	private String nom;
	private Integer valeur;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Integer getValeur() {
		return valeur;
	}
	public void setValeur(Integer valeur) {
		this.valeur = valeur;
	}
	public Numeric(String nom, Integer valeur) {
		this.nom = nom;
		this.valeur = valeur;
	}
	public Numeric() {

	}
	
	
	
	
}
