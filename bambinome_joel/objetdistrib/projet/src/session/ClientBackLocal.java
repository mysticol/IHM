package session;
import javax.ejb.Local;

import entity.Adresse;
import entity.Client;

@Local
public interface ClientBackLocal extends SessionEntityManager<Client>{
	
	public int createClient( 	 String prenom,	 String nom,	 Adresse add);
	
	
	

}
