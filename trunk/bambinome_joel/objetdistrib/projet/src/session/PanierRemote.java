package session;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import entity.Commande;
import entity.ProduitKey;
import fr.alma.interfaces.IAProduit;
import fr.alma.interfaces.ICategorie;
import fr.alma.interfaces.IFrontCategorie;
import fr.alma.interfaces.IItem;

@Remote
public interface PanierRemote {

	public abstract boolean identificationClient(int idClient);

	public abstract void ajoutProduitPanier(IItem produit, Long num);

	public abstract void retirerProduitPanier(ProduitKey key);

	public abstract boolean passerCommande();

	public abstract Commande voirEtatCommande();

	public abstract List<IAProduit> findProduitsByCategorie(IFrontCategorie arg0);

	public abstract List<IAProduit> findByCategorieAndMarqueAndPriceRange(
			IFrontCategorie arg0, String arg1, double arg2, double arg3);

	public abstract List<IAProduit> findByCategorieAndPriceRange(
			IFrontCategorie arg0, double arg1, double arg2);

	public abstract List<IFrontCategorie> getCategories();
	
	
	
	
}
