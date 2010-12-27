package catalogue;

import java.util.List;

import javax.ejb.Remote;

import entity.ECategorie;
import entity.EProduit;

import lib.CatalogueRemote;

@Remote
public interface CatalogueServiceRemote extends CatalogueRemote{
	
	Long addProduct(String description, Double price, String nomCateg, String marque, String modele, Integer quantity);

	void deleteProduct(Long id);
	
	Long addCategorie(String name);
	
	void deleteCategorie(Long id);
	
	List<EProduit> getAllProduits();
	
	List<ECategorie> getAllCategories();

}
