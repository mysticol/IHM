package session;
import javax.ejb.Local;
import javax.ejb.Remote;

import entity.Adresse;
import entity.Client;

@Remote
public interface ClientBackLocal extends SessionEntityManager<Client>{
	
	public int createClient( 	 String prenom,	 String nom,	 Adresse add);
	
	
	

}
