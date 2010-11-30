package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CLIENT")
public class Client {
	
	
	private int id;
	private String prenom;
	private String nom;
	private Adresse add;
	public Client(int id, String prenom, String nom, Adresse add) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.add = add;
	}
	public Client() {
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Adresse getAdd() {
		return add;
	}
	public void setAdd(Adresse add) {
		this.add = add;
	}
	
	

}
