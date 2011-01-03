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
		catalogueLocal.addCategorie("Jouet");
        catalogueLocal.addCategorie("Jardin"); 
        
        catalogueLocal.addProduct(" vrai osselet juif ", 10.0, "Jouet", "Les Restes de la fosses", "osselets", 500);
        catalogueLocal.addProduct(" vrai toupie juive ", 15.0, "Jouet", "Les Restes de la fosses", "toupie", 100);
        catalogueLocal.addProduct(" fait avec de vrai plombage ", 12.0, "Jouet", "Les Restes du four", "soldat de plomb", 100);
        
        catalogueLocal.addProduct(" un joli rateau en os ", 30.0, "Jardin", "Les Restes de la fosses", "rateau", 100);
        catalogueLocal.addProduct(" un bon angraie plein de bonne chose ", 20.0, "Jardin", "Les Restes du four", "angraie", 900);
        
        // fin de la creation du catalogue

	}

}
