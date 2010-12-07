package catalogue;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import lib.CatalogueRemote;
import lib.ICategorie;
import lib.IProduit;



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

	public List<ICategorie> findAllCategories() {
			
		TypedQuery<ICategorie> query = em.createQuery("From Category",ICategorie.class);
				
		return query.getResultList();
	}

	public List<IProduit> findByCategorie(String catName) {

		TypedQuery<ICategorie> query = em.createQuery("From Category Where name=:name",ICategorie.class);
		query.setParameter("name", catName);
		ICategorie cat = query.getSingleResult();

		return ((Category) cat).getProduits();
	}

	public List<IProduit> findByCategorieAndPriceRange(String arg0,
			Double arg1, Double arg2) {

		TypedQuery<IProduit> query = em.createQuery("From Category" +
													  " Where name=:name" +
													  " and produits.price>=:prixMin" +
													  " and produits.price<=:prixMax",IProduit.class);
		query.setParameter("name", arg0);
		query.setParameter("prixMin", arg1);
		query.setParameter("PrixMax", arg2);

		return query.getResultList();		
		
	}

	public List<IProduit> findByMarque(String arg0) {

		return null;
	}

	public IProduit findByMarqueAndModele(String arg0, String arg1) {

		return null;
	}

	public ICategorie findCategorie(Long arg0) {

		TypedQuery<ICategorie> query = em.createQuery("From Category" +
				  " Where id=:id",ICategorie.class);
		query.setParameter("id", arg0);
		
		return query.getSingleResult();

	}

	public ICategorie findCategorieByName(String arg0) {

		TypedQuery<ICategorie> query = em.createQuery("From Category" +
				  " Where name=:name",ICategorie.class);
		query.setParameter("name", arg0);
		
		return query.getSingleResult();
	}

	public Long addProduct(String description, Double price,
			ICategorie categorie, String marque, String modele, Long quantity) {

		// On créé le produit
		Produit newProduit = new Produit(description, price, categorie, marque, modele, quantity);
		
		em.persist(newProduit);
		
		// On l'ajoute à sa Catégorie si elle existe.
		// Sinon, on créé la Catégorie et on y ajoute le Produit
		
		ICategorie cat = findCategorieByName(categorie.getName());
		
		if(cat == null){
			//La Catégorie n'existe pas, on la créé donc
			Category newCategorie = new Category();
			
			newCategorie.setName(categorie.getName());
			
			List<IProduit> prodTmp = new LinkedList<IProduit>();
			prodTmp.add(newProduit);
			
			newCategorie.setProduits(prodTmp);
			
			em.persist(newCategorie);
			
		} else {
			//La Catégorie existe : on y ajoute le Produit
			((Category) cat).getProduits().add(newProduit);
		}
			
		return newProduit.getId();
	}

	public void deleteProduct(Long id) {

		
	}

	public List<IProduit> findByCategorieAndMarque(String categorie,
			String marque) {

		return null;
	}

	public List<IProduit> findByCategorieAndMarqueAndPriceRange(
			String categorie, String marque, Double lowPrice, Double highPrice) {

		return null;
	}

	public Boolean order(String marque, String model, Integer quantity,
			String clientName, String clientAddress) {

		return null;
	}

	/* Nos méthodes non-présentes dans l'interface */
	public Long addCategorie(String name, List<IProduit> listProduit) {
		
		Category newCategorie = new Category(name, listProduit);
		
		em.persist(newCategorie);
		
		return newCategorie.getId();
	}

	public void deleteCategorie(Long id) {
		
		em.detach(findCategorie(id));
		
	}
	
	
	
	

}
