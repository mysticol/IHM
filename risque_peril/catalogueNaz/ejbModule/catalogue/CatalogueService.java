package catalogue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.ECategorie;
import entity.ECommande;
import entity.EProduit;
import fr.alma.dto.catalogue.Categorie;
import fr.alma.dto.catalogue.Produit;
import fr.alma.interfaces.CatalogueRemote;


/**
 * Session Bean implementation class Catalogue
 */
@Stateless
public class CatalogueService implements CatalogueRemote {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public CatalogueService() {

    }
	
	// Methodes distantes
	public List<Categorie> findAllCategories() {
		
		TypedQuery<ECategorie> query = em.createQuery("From ECategorie", ECategorie.class);
				
		return toListCategorie(query.getResultList());
	}

	public List<Produit> findByMarque(String marque) {
		
		TypedQuery<EProduit> query = em.createQuery("From EProduit Where marque=:marque", EProduit.class);
		query.setParameter("marque", marque);
		
		return toListProduit(query.getResultList());
	}

	public List<Produit> findByCategorie(String nomCateg) {
		
		TypedQuery<ECategorie> query = em.createQuery("From ECategorie Where name=:nomCateg", ECategorie.class);
		query.setParameter("nomCateg", nomCateg);
		
		return toListProduit(query.getSingleResult().getProduits());
	}

	public List<Produit> findByCategorieAndPriceRange(String nomCateg,
			Double lowPrice, Double highPrice) {
		
		TypedQuery<EProduit> query = em.createQuery("From EProduit " +
													"Where categorie.name=:nomCateg " +
													"and price>=:lowPrice " +
													"and price<=:highPrice", EProduit.class);
		query.setParameter("nomCateg", nomCateg);
		query.setParameter("lowPrice", lowPrice);
		query.setParameter("highPrice", highPrice);
		
		return toListProduit(query.getResultList());
	}

	public List<Produit> findByCategorieAndMarque(String nomCateg, String marque) {
		
		TypedQuery<EProduit> query = em.createQuery("From EProduit " +
													"Where categorie.name=:nomCateg " +
													"and marque=:marque", EProduit.class);
		query.setParameter("nomCateg", nomCateg);
		query.setParameter("marque", marque);		
		
		return toListProduit(query.getResultList());
	}

	public List<Produit> findByCategorieAndMarqueAndPriceRange(String nomCateg,
			String marque, Double lowPrice, Double highPrice) {
		
		TypedQuery<EProduit> query = em.createQuery("From EProduit " +
													"Where categorie.name=:nomCateg " +
													"and price>=:lowPrice " +
													"and price<=:highPrice " +
													"and marque=:marque ", EProduit.class);
		query.setParameter("nomCateg", nomCateg);
		query.setParameter("lowPrice", lowPrice);
		query.setParameter("highPrice", highPrice);		
		query.setParameter("marque", marque);
		
		return toListProduit(query.getResultList());
	}

	public Boolean order(String marque, String model, Integer quantity,
			String clientName, String clientAddress) {

		Boolean transactionValide = true;
		// On verifie si le produit existe
		
		TypedQuery<EProduit> query = em.createQuery("From EProduit " +
													"Where modele=:model " +
													"and marque=:marque", EProduit.class);
		query.setParameter("model", model);
		query.setParameter("marque", marque);
		
		EProduit p = query.getSingleResult();
		
		if(p!=null){
			
			// On verifie qu'il y en a assez en stock
			
			if(p.getQuantity()>=quantity){
				
				// On soustrait la quantite voulue
				
				p.setQuantity(p.getQuantity()-quantity);
				
				// On enregistre la commande
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				//get current date time with Date()
				Date date = new Date();
				System.out.println(dateFormat.format(date));

				//get current date time with Calendar()
				Calendar cal = Calendar.getInstance();
				
				ECommande ec = new ECommande(clientName, clientAddress, p, quantity, dateFormat.format(cal.getTime()));
				
				em.persist(ec);
				
			} else {
				
				transactionValide = false;
				
				System.out.println("Il n'y a pas assez du produit demande en stock");				
				
			}
			
			
		} else {
			
			transactionValide = false;
			
			System.out.println("Le produit n'existe pas");
			
		}
			
		return transactionValide;
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
