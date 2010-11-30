package entity;

import java.io.Serializable;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="COMMANDE")
public class Commande implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2855108245678776787L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	
	private Client cl;
	
	
	private HashMap<ProduitStub, Long> contenu;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	public Client getCl() {
		return cl;
	}
	public void setCl(Client cl) {
		this.cl = cl;
	}
	
	@ManyToMany
	public HashMap<ProduitStub, Long> getContenu() {
		return contenu;
	}
	public void setContenu(HashMap<ProduitStub, Long> contenu) {
		this.contenu = contenu;
	}
	public Commande(int id, Client cl, HashMap<ProduitStub, Long> contenu) {
		this.id = id;
		this.cl = cl;
		this.contenu = contenu;
	}
	public Commande() {

	}
	
	
	
	
	
}
