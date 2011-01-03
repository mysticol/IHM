package interfaceTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import fr.alma.dto.catalogue.Categorie;
import fr.alma.dto.catalogue.Produit;
import fr.alma.interfaces.CatalogueRemote;

public class ClientTest {

	/**
	 * @param args
	 * @throws NamingException 
	 */
	public static void main(String[] args) throws NamingException {
		
		Boolean fin = false;
		String choix = "";
		
		BufferedReader myInput = new BufferedReader(new InputStreamReader(System.in));

		String categorie;
		String marque;
		String modele;
		Double prixMin;
		Double prixMax;
		String client;
		String adresse;
		Integer quantite;
		
		Context context = new InitialContext();
        // Nom de la classe d'implémentation + /local ou /remote
        CatalogueRemote catalogueService = (CatalogueRemote) context.lookup("CatalogueService/remote");
				
		do{
			try {
				System.out.println(" -------------- MENU -------------- ");
				System.out.println(" 1 - Récupérer toutes les Catégories de produit");
				System.out.println(" 2 - Récupérer les produits d'une marque");
				System.out.println(" 3 - Récupérer les produits d'une Catégorie");
				System.out.println(" 4 - Récupérer les produits d'une Catégorie dans une fourchette de prix");
				System.out.println(" 5 - Récupérer les produits d'une marques et d'une Catégorie");
				System.out.println(" 6 - Récupérer les produits d'une Catégorie dans une fourchette de prix selon une marque");
				System.out.println(" 7 - Acheter un produit");
				System.out.println(" ---------------------------------- ");
				System.out.println(" 0 - Quitter");
				System.out.println(" ---------------------------------- ");
				System.out.print(  "------> Choix : "); choix = myInput.readLine();
				
				switch(Integer.valueOf(choix)){
				case 1 : // Récupérer toutes les Catégories de produit
						 for( Categorie cat : catalogueService.findAllCategories()){
							printCategorie(cat);
						 }
					break;
				case 2 : // Récupérer les produits d'une marque
					 	 System.out.print("--------> marque : "); marque = myInput.readLine();
						 for( Produit p : catalogueService.findByMarque(marque)){
							 printProduit(p);
						 }
					break;
				case 3 : // Récupérer les produits d'une Catégorie
					     System.out.print("--------> Categorie : "); categorie = myInput.readLine();
						 for( Produit p : catalogueService.findByCategorie(categorie)){
							 printProduit(p);
						 }
					break;
				case 4 : // Récupérer les produits d'une Catégorie dans une fourchette de prix
						 System.out.print("--------> Categorie : "); categorie = myInput.readLine();
						 System.out.print("--------> prix minimum : "); prixMin = Double.valueOf(myInput.readLine());
						 System.out.print("--------> prix maximum : "); prixMax = Double.valueOf(myInput.readLine());
						 for( Produit p : catalogueService.findByCategorieAndPriceRange(categorie, prixMin, prixMax)){
							 printProduit(p);
						 }
					break;
				case 5 : // Récupérer les marques d'une Catégorie
						 System.out.print("--------> marque : ");  marque = myInput.readLine();
						 System.out.print("--------> Categorie : "); categorie = myInput.readLine();
						 for( Produit p : catalogueService.findByCategorieAndMarque(categorie, marque)){
							 printProduit(p);
						 }
					break;
				case 6 : System.out.print("--------> Categorie : "); categorie = myInput.readLine();
						 System.out.print("--------> prix minimum : "); prixMin = Double.valueOf(myInput.readLine());
						 System.out.print("--------> prix maximum : "); prixMax = Double.valueOf(myInput.readLine());
						 System.out.print("--------> marque : "); marque = myInput.readLine();
						 for( Produit p : catalogueService.findByCategorieAndMarqueAndPriceRange(categorie, marque, prixMin, prixMax)){
							 printProduit(p);
						 }
					break;
				case 7 : System.out.print("--------> marque : "); marque = myInput.readLine();
				 		 System.out.print("--------> modele : "); modele = myInput.readLine();
				 		 System.out.print("--------> quantité : "); quantite = Integer.valueOf(myInput.readLine());
				 		 System.out.print("--------> nom du client : "); client = myInput.readLine();
				 		 System.out.print("--------> adresse du client : "); adresse = myInput.readLine();
				 		 if(catalogueService.order(marque, modele, quantite, client, adresse)){
				 			 System.out.println("----> Achat effectué"); 
				 		 }else{
				 			 System.out.println("----> Achat annulé"); 
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
	
	private static void printCategorie(Categorie cat){
		System.out.println("--> nom : "+ cat.getName());
		System.out.println("-----------------------------");
	}
	
	private static void printProduit(Produit prod){
		System.out.println("--> description : "+ prod.getDescription());
		System.out.println("--> marque : "+ prod.getMarque());
		System.out.println("--> modele : "+ prod.getModele());
		System.out.println("--> price : "+ prod.getPrix());
		System.out.println("--> quantity : "+ prod.getQuantite());
		System.out.println("--------------------------------");
	}
	
	
}
