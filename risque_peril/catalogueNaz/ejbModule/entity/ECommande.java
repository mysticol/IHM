package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="COMMANDE")
public class ECommande implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nomClient;
	private String adresse;
	private EProduit produit;
	private Integer quantite;
	private String date;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNomClient() {
		return nomClient;
	}
	
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	
	public String getAdresse() {
		return adresse;
	}
	
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	@ManyToOne
	public EProduit getProduit() {
		return produit;
	}
	
	public void setProduit(EProduit produit) {
		this.produit = produit;
	}
	
	public Integer getQuantite() {
		return quantite;
	}
	
	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	// Constructeurs
	public ECommande() {
		super();
	}
	
	public ECommande(String nomClient, String adresse, EProduit produit,
			Integer quantite, String date) {
		super();
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.produit = produit;
		this.quantite = quantite;
		this.date = date;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
