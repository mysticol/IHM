package entity;

public class ProduitKey {

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
