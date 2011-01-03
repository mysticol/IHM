package catalogue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entity.ECategorie;
import entity.ECommande;
import entity.EProduit;

public class CatalogueManagerClient {

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
				System.out.println(" 1 - liste des Produits");
				System.out.println(" 2 - Créer un Produit");
				System.out.println(" 3 - Effacer un Produit");
				System.out.println(" 4 - liste des Catégories");
				System.out.println(" 5 - Créer une Catégorie");
				System.out.println(" 6 - Effacer une Catégorie");
				System.out.println(" ---------------------------------- ");
				System.out.println(" 7 - Afficher toute les commandes");
				System.out.println(" ---------------------------------- ");
				System.out.println(" 0 - Quitter");
				System.out.println(" ---------------------------------- ");
				System.out.print(  "------> Choix : "); choix = myInput.readLine();
				
				switch(Integer.valueOf(choix)){
				case 1 : for(EProduit p : catalogueLocal.getAllProduits()){
							 System.out.println("--> description : "+ p.getDescription());
							 System.out.println("--> id : "+ p.getId());
							 System.out.println("--------------------------------");
						 }
						 break;
				case 2 : System.out.print("--------> description : "); description = myInput.readLine();
						 System.out.print("--------> Categorie : "); categorie = myInput.readLine();
						 System.out.print("--------> marque : "); marque = myInput.readLine();
				 		 System.out.print("--------> modele : "); modele = myInput.readLine();
				 		 System.out.print("--------> quantité : "); quantite = Integer.valueOf(myInput.readLine());
				 		 System.out.print("--------> prix : "); prix = Double.valueOf(myInput.readLine());
				 		 catalogueLocal.addProduct(description, prix, categorie, marque, modele, quantite);
				 		 break;
				case 3 : System.out.print("--------> id produit : "); id = Long.valueOf(myInput.readLine()); 
						 catalogueLocal.deleteProduct(id);
						 break;
				case 4 : for(ECategorie c : catalogueLocal.getAllCategories()){
							 System.out.println("--> nom : "+ c.getName());
							 System.out.println("--> id : "+ c.getId());
							 System.out.println("--------------------------------");
						 }
						 break;
				case 5 : System.out.print("--------> Categorie : "); categorie = myInput.readLine();
				 		 catalogueLocal.addCategorie(categorie);
				 		 break;
				case 6 : System.out.print("--------> id Categorie : "); id = Long.valueOf(myInput.readLine());
						 catalogueLocal.deleteCategorie(id);
						 break;
				case 7 : for(ECommande c : catalogueLocal.getAllCommandes()){
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
