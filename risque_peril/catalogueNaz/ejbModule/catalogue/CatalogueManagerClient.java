package catalogue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entity.ECategorie;
import entity.ECommande;
import entity.EProduit;
import fr.alma.interfaces.CatalogueRegisteringRemote;

public class CatalogueManagerClient {

	private final static Hashtable<String, String> conf;
	
	static {
		conf = new Hashtable<String, String>();
		conf.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
	    conf.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
	}
	
	public static void init() throws NamingException {
		
		System.out.println(">>>>>>>>>>>>>>> postConstruct .....");
		
		Context context = new InitialContext();
        // Nom de la classe d'implémentation + /local ou /remote
        CatalogueManagerRemote catalogueLocal = (CatalogueManagerRemote) context.lookup("CatalogueManagerService/remote");

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
        
        // fin de la creation du catalogue
	}

	
	/**
	 * @param args
	 * @throws NamingException 
	 */
	public static void main(String[] args) throws NamingException {
		
		Boolean fin = false;
		String choix = "";		
		
		BufferedReader myInput = new BufferedReader(new InputStreamReader(System.in));
		
		Context context = new InitialContext();
        // Nom de la classe d'implémentation + /local ou /remote
        CatalogueManagerRemote catalogueLocal = (CatalogueManagerRemote) context.lookup("CatalogueManagerService/remote"); 
        
    	String categorie;
		String marque;
		String modele;
		Double prix;
		Integer quantite;
		String description;
		Long id;
        
        // Menu admin
        do{
			try {
				System.out.println(" -------------- MENU -------------- ");
				System.out.println(" 1 - S'enregistrer sur un serveur");
				System.out.println(" 2 - Liste des Produits");
				System.out.println(" 3 - Créer un Produit");
				System.out.println(" 4 - Effacer un Produit");
				System.out.println(" 5 - Liste des Catégories");
				System.out.println(" 6 - Créer une Catégorie");
				System.out.println(" 7 - Effacer une Catégorie");
				System.out.println(" ---------------------------------- ");
				System.out.println(" 8 - Afficher toute les commandes");
				System.out.println(" ---------------------------------- ");
				System.out.println(" 0 - Quitter");
				System.out.println(" ---------------------------------- ");
				System.out.print(  "------> Choix : "); choix = myInput.readLine();
				
				switch(Integer.valueOf(choix)){
				
				case 1 : if(catalogueLocal.getAllCategories().size()==0){
							//La base est vide, on a appelle la methode d'initialisation
							init();
						 }

						 System.out.print("--> votre id : "); String idUtil = myInput.readLine();
						 System.out.print("--> ip local : "); String ipLocal = myInput.readLine();
						 System.out.print("--> ip Serveur : "); String ipServeur = myInput.readLine();
						 System.out.print("--> port Serveur : "); String portServeur = myInput.readLine();
						 // On s'enregistre aupres du server central
				         Context contextServeur = new InitialContext(conf);
				         contextServeur.addToEnvironment("java.naming.provider.url", ipServeur+":"+portServeur);
		
				         CatalogueRegisteringRemote centralServerRegistering = (CatalogueRegisteringRemote) contextServeur.lookup("CatalogueRegistering/remote");
						
					     if(!centralServerRegistering.senregistrer(ipLocal, idUtil, "CatalogueService")){
					    	 System.out.println("Probleme : impossible de s'enregistrer aupres du server central");
					     } else {
					    	 System.out.println("Enregistrement reussi");
					     }
						 break;
				case 2 : for(EProduit p : catalogueLocal.getAllProduits()){
							 System.out.println("--> marque, modele : "+ p.getMarque() + ", " + p.getModele());
							 System.out.println("--> id : "+ p.getId());
							 System.out.println("--------------------------------");
						 }
						 break;
				case 3 : System.out.print("--------> description : "); description = myInput.readLine();
						 System.out.print("--------> Categorie : "); categorie = myInput.readLine();
						 System.out.print("--------> marque : "); marque = myInput.readLine();
				 		 System.out.print("--------> modele : "); modele = myInput.readLine();
				 		 System.out.print("--------> quantité : "); quantite = Integer.valueOf(myInput.readLine());
				 		 System.out.print("--------> prix : "); prix = Double.valueOf(myInput.readLine());
				 		 catalogueLocal.addProduct(description, prix, categorie, marque, modele, quantite);
				 		 break;
				case 4 : System.out.print("--------> id produit : "); id = Long.valueOf(myInput.readLine()); 
						 catalogueLocal.deleteProduct(id);
						 break;
				case 5 : for(ECategorie c : catalogueLocal.getAllCategories()){
							 System.out.println("--> nom : "+ c.getName());
							 System.out.println("--> id : "+ c.getId());
							 System.out.println("--------------------------------");
						 }
						 break;
				case 6 : System.out.print("--------> Categorie : "); categorie = myInput.readLine();
				 		 catalogueLocal.addCategorie(categorie);
				 		 break;
				case 7 : System.out.print("--------> id Categorie : "); id = Long.valueOf(myInput.readLine());
						 catalogueLocal.deleteCategorie(id);
						 break;
				case 8 : for(ECommande c : catalogueLocal.getAllCommandes()){
							 System.out.println("--> id : "+ c.getId());
							 System.out.println("--> nom du client : "+ c.getNomClient());
							 System.out.println("--> adresse du client : "+ c.getAdresse());
							 System.out.println("--> produit : "+ c.getProduit().getId());
							 System.out.println("--> quantité : "+ c.getQuantite());
							 System.out.println("--> date de la commande : "+ c.getDate());
							 System.out.println("--------------------------------");
						 }
						 break;
				case 0 : System.out.println(" Au revoir !");
						 fin = true;			
						 break;
				default: System.out.println(" !!! Mauvaise saisie !!!"); 
						 break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}		
        }while(!fin);      
	}
}
