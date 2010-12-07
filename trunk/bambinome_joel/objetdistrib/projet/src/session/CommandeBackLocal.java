package session;

import java.util.HashMap;

import javax.ejb.Local;

import entity.Client;
import entity.Commande;
import entity.ProduitStub;

@Local
public interface CommandeBackLocal extends SessionEntityManager<Commande>{
	
	public int createCommande(Client cl, HashMap<ProduitStub, Long> contenu);
	
	

}
