package catalogue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lib.ICategorie;
import lib.IProduit;

@Entity
@Table(name="PRODUIT",
	   uniqueConstraints = @UniqueConstraint(columnNames = { "marque","modele" }))
public class Produit implements IProduit {
	
	private Long id;
	private String fournisseur;
	private String description;
	private Double price;
	private ICategorie categorie;
	private String marque;
	private String modele;
	private Long quantity;

	public Produit(){
	}
	
	public Produit(String description, Double price, ICategorie category, String marque, String modele, Long quantity){
		this.description = description;
		this.price = price;
		this.categorie = category;
		this.marque = marque;
		this.modele = modele;
		this.quantity = quantity;
	}
	
	public ICategorie getCategorie() {
		return categorie;
	}

	public String getDescription() {
		return description;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public String getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}

	@Column(name="marque")
	public String getMarque() {
		return marque;
	}

	@Column(name="modele")
	public String getModele() {
		return modele;
	}

	public Double getPrice() {
		return price;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setCategorie(ICategorie category) {
		this.categorie = category;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
