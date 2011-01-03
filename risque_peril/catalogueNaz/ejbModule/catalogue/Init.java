package catalogue;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import fr.alma.interfaces.CatalogueRegisteringRemote;

public class Init {
	
	private final static Hashtable<String, String> conf;
	
	static {
		conf = new Hashtable<String, String>();
		conf.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
	    conf.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws NamingException{
		
		Context context = new InitialContext();
        // Nom de la classe d'implémentation + /local ou /remote
        CatalogueManagerRemote catalogueLocal = (CatalogueManagerRemote) context.lookup("CatalogueManagerService/remote");
        
        // On s'enregistre aupres du server central
        Context contextServeur = new InitialContext(conf);
        contextServeur.addToEnvironment("java.naming.provider.url", "172.16.134.152:1099");

        CatalogueRegisteringRemote centralServerRegistering = (CatalogueRegisteringRemote) contextServeur.lookup("CatalogueRegistering/remote");
		
	      if(!centralServerRegistering.senregistrer("172.16.134.150", "dejean-pottier", "CatalogueService")){
	      	System.out.println("Probleme : impossible de s'enregistrer aupres du server central");
	      }          
	        
     // création automatique du catalogue pour les tests !!
		catalogueLocal.addCategorie("Livres");
		catalogueLocal.addProduct(" le classique avec un ballon ", 10.0, "Livres", "Jules Verne", "Le tour du monde en 80 jours", 500);
        catalogueLocal.addProduct(" le classique sous l'eau ", 15.0, "Livres", "Jules Verne", "Vingt mille lieues sous les mers", 100);
        catalogueLocal.addProduct(" un grand tintin ", 12.0, "Livres", "Hergé", "Tintin au Congo", 100);
        catalogueLocal.addProduct(" un titin qui existe pas ", 30.0, "Livres", "Hergé", "Tintin chez les Helvètes", 100);
        catalogueLocal.addProduct(" je prefere celui sur le canibalisme ", 20.0, "Livres", "Gilbert Delahaye", "Martine fait du poney", 900);
        
		
        catalogueLocal.addCategorie("Films"); 
        catalogueLocal.addProduct(" horreur ", 20.0, "Films", "Tom Six", "The human centipede", 200);
        catalogueLocal.addProduct(" classique ", 40.0, "Films", "Quentin Tarantino", "Pulp fiction", 300);
        catalogueLocal.addProduct(" exelent ", 40.0, "Films", "David Fincher", "Fight Club", 200);
        catalogueLocal.addProduct(" incontournable ", 50.0, "Films", "Darren Aronofsky", "Requiem for a dream", 400);
        catalogueLocal.addProduct(" genial ", 30.0, "Films", "Robert Rodriguez", "Machete", 100);
        
        
        catalogueLocal.addCategorie("Jeux"); 
        catalogueLocal.addProduct(" fps ", 20.0, "Jeux", "Valve", "Counter-Strike Source", 400);
        catalogueLocal.addProduct(" fps durant la 2nd guerre mondiale ", 20.0, "Jeux", "Valve", "Day of Defeat Source", 400);
        catalogueLocal.addProduct(" flash ", 2.0, "Jeux", "Adult Swim Games", "Robot Unicorn Attack", 2000);
        catalogueLocal.addProduct(" classique ", 40.0, "Jeux", "Nintendo", "Pokémon", 10);
        catalogueLocal.addProduct(" fps massif ", 70.0, "Jeux", "EA games", "Battlefield Bad Company 2", 500);

	}

}
