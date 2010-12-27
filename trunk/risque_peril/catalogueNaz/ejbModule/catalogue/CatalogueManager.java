package catalogue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entity.EProduit;

import lib.CatalogueRemote;

public class CatalogueManager {

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
        CatalogueServiceLocal catalogueLocal = (CatalogueServiceLocal) context.lookup("CatalogueService/local");
	
    	String categorie;
		String marque;
		String modele;
		Double prix;
		String client;
		String adresse;
		Integer quantite;
		String description;
		Long id;
        
		// création automatique du catalogue pour les tests !!
		catalogueLocal.addCategorie("Jouet", new LinkedList<EProduit>());
        catalogueLocal.addCategorie("jardin", new LinkedList<EProduit>()); 
        
        catalogueLocal.addProduct(" vrai osselet juif ", 10.0, "Jouet", "Les Restes de la fosses", "osselets", 500);
        catalogueLocal.addProduct(" vrai toupie juive ", 15.0, "Jouet", "Les Restes de la fosses", "toupie", 100);
        catalogueLocal.addProduct(" fait avec de vrai plombage ", 12.0, "Jouet", "Les Restes du four", "soldat de plomb", 100);
        
        catalogueLocal.addProduct(" un joli rateau en os ", 30.0, "Jardin", "Les Restes de la fosses", "rateau", 100);
        catalogueLocal.addProduct(" un bon angraie plein de bonne chose ", 20.0, "Jardin", "Les Restes du four", "angraie", 900);
        
        // fin de la creation du catalogue
        
        // Menu admin
        do{
			try {
				System.out.println(" -------------- MENU -------------- ");
				System.out.println(" 1 - Créer un produit");
				System.out.println(" 2 - Effacer un produit");
				System.out.println(" 3 - Créer une Catégorie");
				System.out.println(" 4 - Effacer une Catégorie");
				System.out.println(" ---------------------------------- ");
				System.out.println(" 0 - Quitter");
				System.out.println(" ---------------------------------- ");
				System.out.print(  "------> Choix : "); choix = myInput.readLine();
				
				switch(Integer.valueOf(choix)){
				case 1 : System.out.print("--------> description : "); description = myInput.readLine();
						 System.out.print("--------> Categorie : "); categorie = myInput.readLine();
						 System.out.print("--------> marque : "); marque = myInput.readLine();
				 		 System.out.print("--------> modele : "); modele = myInput.readLine();
				 		 System.out.print("--------> quantité : "); quantite = Integer.valueOf(myInput.readLine());
				 		 System.out.print("--------> prix : "); prix = Double.valueOf(myInput.readLine());
				 		 catalogueLocal.addProduct(description, prix, categorie, marque, modele, quantite);
				 		 break;
				case 2 : System.out.print("--------> id produit : "); id = Long.valueOf(myInput.readLine()); 
						 System.out.println(">>>>>>>>>>>> fonction non implémenté <<<<<<<<<<<<"); // appel
						 break;
				case 3 : System.out.print("--------> Categorie : "); categorie = myInput.readLine();
				 		 catalogueLocal.addCategorie(categorie, new LinkedList<EProduit>());
				 		 break;
				case 4 : System.out.print("--------> id Categorie : "); id = Long.valueOf(myInput.readLine());
						 System.out.println(">>>>>>>>>>>> fonction non implémenté <<<<<<<<<<<<"); // appel
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
