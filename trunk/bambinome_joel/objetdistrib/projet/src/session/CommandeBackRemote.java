package session;

import java.util.HashMap;

import javax.ejb.Local;
import javax.ejb.Remote;

import entity.Client;
import entity.Commande;
import entity.ProduitStub;

@Remote
public interface CommandeBackRemote extends SessionEntityManager<Commande>{
	
	public int createCommande(Client cl, HashMap<ProduitStub, Long> contenu);
	
	

}
