package session;
import javax.ejb.Local;
import javax.ejb.Remote;

import entity.Commande;

@Remote
public interface PanierLocal {

	
	public boolean ajoutProduitPanier( int id, Long num);
	
	public boolean passerCommande();
	public Commande voirEtatCommande();
	
	public void getCategorieProduit();
	public void getListeProduit();
	
	
	
	
	
	
}
