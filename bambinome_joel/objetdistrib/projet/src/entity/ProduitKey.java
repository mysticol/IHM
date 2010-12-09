package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProduitKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5271661977452015500L;
	private String modele;
	private String marque; 
	
	private String fournisseur;

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}

	public ProduitKey(String modele, String marque, String fournisseur) {
		this.modele = modele;
		this.marque = marque;
		this.fournisseur = fournisseur;
	}
	
	
	
	
}
