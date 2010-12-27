package catalogue;

import javax.ejb.Remote;

import lib.CatalogueRemote;

@Remote
public interface CatalogueServiceRemote extends CatalogueRemote{
	
	Long addProduct(String description, Double price, String nomCateg, String marque, String modele, Integer quantity);

	void deleteProduct(Long id);
	
	Long addCategorie(String name);
	
	void deleteCategorie(Long id);

}
