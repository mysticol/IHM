package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="PRODUIT",
	   uniqueConstraints = @UniqueConstraint(columnNames = { "marque","modele" }))
public class EProduit implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String fournisseur;
	private String description;
	private Double price;
	private ECategorie categorie;
	private String marque;
	private String modele;
	private Integer quantity;

	public EProduit(){
	}
	
	public EProduit(String description, Double price, ECategorie category, String marque, String modele, Integer quantity){
		this.description = description;
		this.price = price;
		this.categorie = category;
		this.marque = marque;
		this.modele = modele;
		this.quantity = quantity;
	}
	
	@ManyToOne
	public ECategorie getCategorie() {
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setCategorie(ECategorie category) {
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

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}

