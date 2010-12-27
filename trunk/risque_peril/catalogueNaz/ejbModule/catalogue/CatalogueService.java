package catalogue;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import dto.Categorie;
import dto.Produit;
import entity.ECategorie;
import entity.EProduit;

import lib.CatalogueRemote;


/**
 * Session Bean implementation class Catalogue
 */
@Stateless
public class CatalogueService implements CatalogueRemote, CatalogueServiceLocal {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public CatalogueService() {

    }

    // Methodes locales
	public Long addProduct(String description, Double price,
			ECategorie categorie, String marque, String modele, Long quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		
	}

	public Long addCategorie(String name, List<EProduit> listProduit) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteCategorie(Long id) {
		// TODO Auto-generated method stub
		
	}

	
	// Methodes distantes
	public List<Categorie> findAllCategories() {
		
		TypedQuery<ECategorie> query = em.createQuery("From Categorie", ECategorie.class);
				
		return toListCategorie(query.getResultList());
	}

	public List<Produit> findByMarque(String marque) {
		
		TypedQuery<EProduit> query = em.createQuery("From Produit Where marque=:marque", EProduit.class);
		query.setParameter("marque", marque);
		
		return toListProduit(query.getResultList());
	}

	public List<Produit> findByCategorie(String nomCateg) {
		
		TypedQuery<ECategorie> query = em.createQuery("From Categorie Where name=:nomCateg", ECategorie.class);
		query.setParameter("nomCateg", nomCateg);
		
		return toListProduit(query.getSingleResult().getProduits());
	}

	public List<Produit> findByCategorieAndPriceRange(String nomCateg,
			Double lowPrice, Double highPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Produit> findByCategorieAndMarque(String nomCateg, String marque) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Produit> findByCategorieAndMarqueAndPriceRange(String nomCateg,
			String marque, Double lowPrice, Double highPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean order(String marque, String model, Integer quantity,
			String clientName, String clientAddress) {
		// TODO Auto-generated method stub
		return null;
	}


	// Methodes de transformation d'un EProduit, ECategorie
	// vers un Produit, Categorie, et vice-versa
	
	private Produit toProduit(EProduit ep) {
		Produit p = new Produit();
		
		p.setCategorie(toCategorie(ep.getCategorie()));
		p.setDescription(ep.getDescription());
		p.setMarque(ep.getMarque());
		p.setModele(ep.getModele());
		p.setPrix(ep.getPrice());
		p.setQuantite(ep.getQuantity());
		
		return p;
	}
	
	private List<Produit> toListProduit(List<EProduit> lep){
		List<Produit> lp = new LinkedList<Produit>();
		
		for(EProduit ep : lep){
			lp.add(toProduit(ep));
		}
			
		return lp;
	}

	private Categorie toCategorie(ECategorie ec) {
		Categorie c = new Categorie();
		
		c.setName(ec.getName());
		
		return c;
	}
	
	private List<Categorie> toListCategorie(List<ECategorie> lec){
		List<Categorie> lc = new LinkedList<Categorie>();
		
		for(ECategorie ec : lec){
			lc.add(toCategorie(ec));
		}
			
		return lc;
	}
}
