package entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PRODUITSTUB")
public class ProduitStub implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	

	@EmbeddedId
	private ProduitKey key;

	
	
	
	public ProduitKey getKey() {
		return key;
	}
	public void setKey(ProduitKey key) {
		this.key = key;
	}
	public ProduitStub() {

	}
	public String getModele() {
		return key.getModele();
	}
	public void setModele(String modele) {
		this.key.setModele(modele);
	}
	public String getMarque() {
		return key.getMarque();
	}
	public void setMarque(String marque) {
		this.key.setMarque(marque);
	}
	public String getFournisseur() {
		return key.getFournisseur();
	}
	public void setFournisseur(String fournisseur) {
		this.key.setFournisseur(fournisseur);
	}
	public ProduitStub( String modele, String marque, String fournisseur) {
	
		key = new ProduitKey(modele, marque, fournisseur);
	
	}
	
	
	
}
