package catalogue;

import java.util.List;

import javax.ejb.Remote;

import entity.ECategorie;
import entity.ECommande;
import entity.EProduit;

@Remote
public interface CatalogueManagerRemote {
	
	Long addProduct(String description, Double price, String nomCateg, String marque, String modele, Integer quantity);

	void deleteProduct(Long id);
	
	Long addCategorie(String name);
	
	void deleteCategorie(Long id);
	
	List<EProduit> getAllProduits();
	
	List<ECategorie> getAllCategories();
	
	List<ECommande> getAllCommandes();

}
