package catalogue;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.ECategorie;
import entity.ECommande;
import entity.EProduit;

@Stateless
public class CatalogueManagerService implements CatalogueManagerRemote {
	
		
	@PersistenceContext
	private EntityManager em;
	
	// Methodes locales
	public Long addProduct(String description, Double price,
			String nomCateg, String marque, String modele, Integer quantity) {
		
		// On recupere la categorie de nom nomCateg
		TypedQuery<ECategorie> query = em.createQuery("From ECategorie Where name=:nomCateg", ECategorie.class);
		query.setParameter("nomCateg", nomCateg);
		
		ECategorie ec = query.getSingleResult();

		EProduit ep = new EProduit(description, price, ec, marque, modele, quantity);
		
		ec.getProduits().add(ep);
		
		em.persist(ep);
		em.merge(ec);
		
		return ep.getId();
	}

	public void deleteProduct(Long id) {
		TypedQuery<EProduit> query = em.createQuery("From EProduit Where id=:id", EProduit.class);
		query.setParameter("id", id);
		
		EProduit ep = query.getSingleResult();
		
		if(ep!=null){
			em.remove(ep);
		}
	}
	
	public List<EProduit> getAllProduits() {
		TypedQuery<EProduit> query = em.createQuery("From EProduit", EProduit.class);
		
		return query.getResultList();
	}
	
	public Long addCategorie(String name) {

		ECategorie ec = new ECategorie(name);
		
		em.persist(ec);
		
		return ec.getId();
	}

	public void deleteCategorie(Long id) {
		TypedQuery<ECategorie> query = em.createQuery("From ECategorie Where id=:id", ECategorie.class);
		query.setParameter("id", id);
		
		ECategorie ec = query.getSingleResult();
		
		if((ec!=null)&&(ec.getProduits().size()==0)){
			em.remove(ec);
		}
	}
	
	public List<ECategorie> getAllCategories(){
		TypedQuery<ECategorie> query = em.createQuery("From ECategorie", ECategorie.class);		
		
		return query.getResultList();
	}
	
	public List<ECommande> getAllCommandes() {
		TypedQuery<ECommande> query = em.createQuery("From ECommande", ECommande.class);		
		
		return query.getResultList();
	}

}
