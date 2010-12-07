package session;


import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import entity.Commande;
import entity.ProduitKey;
import fr.alma.interfaces.CentralRemote;
import fr.alma.interfaces.IAProduit;
import fr.alma.interfaces.ICategorie;
import fr.alma.interfaces.IFrontCategorie;
import fr.alma.interfaces.IItem;

/**
 * Session Bean implementation class Panier
 */
@Stateful

public class Panier implements PanierLocal {

	@EJB
	CentralRemote central;
	
	@EJB
	CommandeLocal command;
	
	
	
	private HashMap<IItem, Long > panier;
	
	
	
	
    /**
     * Default constructor. 
     */
    public Panier() {
        // TODO Auto-generated constructor stub
    }


	public void ajoutProduitPanier(IItem produit, Long num) {
		
		panier.put(produit, num);
	}

	public void retirerProduitPanier(ProduitKey key) {
		
		IItem iitem= new IItem();
		iitem.setFournisseur(key.getFournisseur());
		iitem.setMarque(key.getMarque());
		iitem.setModel(key.getMarque());
		
		panier.remove(iitem);
	}
	
	
	public boolean passerCommande() {

		
		
		
		Boolean result=central.order(panier);
		if(result){
			
		}
		
		
		return result;
	}

	
	public Commande voirEtatCommande() {

		
		
		
		return null;
	}
	
	public List<IAProduit> findProduitsByCategorie(IFrontCategorie arg0){
		return central.findProduitsByCategorie(arg0);
		
	}
	

	
	
	public List<IAProduit> findByCategorieAndMarqueAndPriceRange(IFrontCategorie arg0,String arg1 , double arg2, double arg3) {
			return central.findByCategorieAndMarqueAndPriceRange(arg0, arg1, arg2, arg3);
			
			
		
	}

	
	public List<IAProduit> findByCategorieAndPriceRange( IFrontCategorie arg0, double arg1, double arg2) {
		
		return central.findByCategorieAndPriceRange(arg0, arg1, arg2);
		
	}

	
	public List<IFrontCategorie> getCategories() {

		
		
		return central.findAllCategories();
	}
	
	
	
	
	

}
