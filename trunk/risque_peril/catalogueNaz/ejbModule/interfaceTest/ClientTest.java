package interfaceTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import lib.CatalogueRemote;
import lib.ICategorie;
import lib.IProduit;

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
		String description;
		Long id;
		
		Context context = new InitialContext();
        // Nom de la classe d'implémentation + /local ou /remote
        CatalogueRemote catalogueService = (CatalogueRemote) context.lookup("CatalogueService/remote");
				
		do{
			try {
				System.out.println(" -------------- MENU -------------- ");
				System.out.println(" 1 - Récupérer toutes les Catégories de produit");
				System.out.println(" 2 - Récupérer des produits par marque et modèle");
				System.out.println(" 3 - Récupérer des produits par marque");
				System.out.println(" 4 - Récupérer les produits d'une Catégorie");
				System.out.println(" 5 - Récupérer les produits d'une Catégorie dans une fourchette de prix");
				System.out.println(" 6 - Récupérer les marques d'une Catégorie");
				System.out.println(" 7 - Récupérer les produits d'une Catégorie dans une fourchette de prix selon une marque");
				System.out.println(" 8 - Acheter un produit");
				System.out.println(" ---------------------------------- ");
				System.out.println(" 9 - Créer un produit");
				System.out.println(" 10- Effacer un produit");
				System.out.println(" 11- Créer une Catégorie");
				System.out.println(" 12- Effacer une Catégorie");
				System.out.println(" ---------------------------------- ");
				System.out.println(" 0 - Quitter");
				System.out.println(" ---------------------------------- ");
				System.out.print(  "------> Choix : "); choix = myInput.readLine();
				
				switch(Integer.valueOf(choix)){
				case 1 : for( ICategorie cat : catalogueService.findAllCategories()){
							System.out.println("--> "+ cat.getId());
							System.out.println("--> "+ cat.getName());
							System.out.println("-----------------------------");
						 }
					break;
				case 2 : System.out.print("--------> marque : "); marque = myInput.readLine();
						 System.out.print("--------> modele : "); modele = myInput.readLine();
						 IProduit prod =  catalogueService.findByMarqueAndModele(marque, modele);
						 System.out.println("--> id : "+ prod.getId());
						 System.out.println("--> description : "+ prod.getDescription());
						 System.out.println("--> marque : "+ prod.getMarque());
						 System.out.println("--> modele : "+ prod.getModele());
						 System.out.println("--> price : "+ prod.getPrice());
						 System.out.println("--> quantity : "+ prod.getQuantity());
						 System.out.println("--------------------------------");
					break;
				case 3 : System.out.print("--------> marque : "); marque = myInput.readLine();
						 marque = myInput.readLine();
						 System.out.println(">>>>>>>>>>>> fonction non implémenté <<<<<<<<<<<<"); // appel
					break;
				case 4 : System.out.print("--------> Categorie : "); categorie = myInput.readLine();
				 		 System.out.println(">>>>>>>>>>>> fonction non implémenté <<<<<<<<<<<<"); // appel
					break;
				case 5 : System.out.print("--------> Categorie : "); categorie = myInput.readLine();
						 System.out.print("--------> prix minimum : "); prixMin = Double.valueOf(myInput.readLine());
						 System.out.print("--------> prix maximum : "); prixMax = Double.valueOf(myInput.readLine());
						 System.out.println(">>>>>>>>>>>> fonction non implémenté <<<<<<<<<<<<"); // appel
					break;
				case 6 : System.out.print("--------> marque : ");  marque = myInput.readLine();
				 		 System.out.println(">>>>>>>>>>>> fonction non implémenté <<<<<<<<<<<<"); // appel
					break;
				case 7 : System.out.print("--------> Categorie : "); categorie = myInput.readLine();
						 System.out.print("--------> prix minimum : "); prixMin = Double.valueOf(myInput.readLine());
						 System.out.print("--------> prix maximum : "); prixMax = Double.valueOf(myInput.readLine());
						 System.out.print("--------> marque : "); marque = myInput.readLine();
						 System.out.println(">>>>>>>>>>>> fonction non implémenté <<<<<<<<<<<<"); // appel
					break;
				case 8 : System.out.print("--------> marque : "); marque = myInput.readLine();
				 		 System.out.print("--------> modele : "); modele = myInput.readLine();
				 		 System.out.print("--------> quantité : "); quantite = Integer.valueOf(myInput.readLine());
				 		 System.out.print("--------> nom du client : "); client = myInput.readLine();
				 		 System.out.print("--------> adresse du client : "); adresse = myInput.readLine();
				 		 System.out.println(">>>>>>>>>>>> fonction non implémenté <<<<<<<<<<<<"); // appel
					break;
				case 9 : System.out.print("--------> description : "); description = myInput.readLine();
						 System.out.print("--------> Categorie : "); categorie = myInput.readLine();
						 System.out.print("--------> marque : "); marque = myInput.readLine();
		 		 		 System.out.print("--------> modele : "); modele = myInput.readLine();
		 		 		 System.out.print("--------> quantité : "); quantite = Integer.valueOf(myInput.readLine());
		 		 		 System.out.print("--------> prix : "); prixMin = Double.valueOf(myInput.readLine());
		 		 		 System.out.println(">>>>>>>>>>>> fonction non implémenté <<<<<<<<<<<<"); // appel
					break;
				case 10: System.out.print("--------> id produit : "); id = Long.valueOf(myInput.readLine()); 
						 System.out.println(">>>>>>>>>>>> fonction non implémenté <<<<<<<<<<<<"); // appel
					break;
				case 11: System.out.print("--------> Categorie : "); categorie = myInput.readLine();
				 		 System.out.println(">>>>>>>>>>>> fonction non implémenté <<<<<<<<<<<<"); // appel
					break;
				case 12: System.out.print("--------> id Categorie : "); id = Long.valueOf(myInput.readLine());
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
