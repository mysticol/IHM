package entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORY")
public class ECategorie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private List<EProduit> produits;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy="categorie", fetch=FetchType.EAGER)
	public List<EProduit> getProduits() {
		return produits;
	}

	public void setProduits(List<EProduit> produits) {
		this.produits = produits;
	}

		
	public ECategorie() {
		super();
	}

	public ECategorie(String name) {
		super();
		this.name = name;
		this.produits = new LinkedList<EProduit>();
	}
	
	
}
