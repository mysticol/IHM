package catalogue;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lib.ICategorie;
import lib.IProduit;

@Entity
@Table(name="CATEGORY")
public class Category implements ICategorie{

	private Long id;
	private String name;
	private List<IProduit> produits;
	
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

	@OneToMany(fetch=FetchType.EAGER)
	public List<IProduit> getProduits() {
		return produits;
	}

	public void setProduits(List<IProduit> produits) {
		this.produits = produits;
	}

		
	public Category() {
		super();
	}

	public Category(String name, List<IProduit> produits) {
		super();
		this.name = name;
		this.produits = produits;
	}
	
	
}
