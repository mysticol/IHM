package catalogue;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Init {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws NamingException{
		
		Context context = new InitialContext();
        // Nom de la classe d'implémentation + /local ou /remote
        CatalogueServiceRemote catalogueLocal = (CatalogueServiceRemote) context.lookup("CatalogueService/remote");
        
        // On s'enregistre aupres du server central
//      CatalogueRegisteringRemote centralServerRegistering = (CatalogueRegisteringRemote) context.lookup("CatalogueRegistering/remote");
//	
//      if(!centralServerRegistering.senregistrer("", "dejean-pottier", "CatalogueService")){
//      	System.out.println("Probleme : impossible de s'enregistrer aupres du server central");
//      }          
        
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
