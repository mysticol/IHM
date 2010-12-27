package catalogue;
import java.util.List;

import javax.ejb.Local;

import entity.ECategorie;
import entity.EProduit;

@Local
public interface CatalogueServiceLocal {
	
	Long addProduct(String description, Double price, String nomCateg, String marque, String modele, Integer quantity);

	void deleteProduct(Long id);
	
	Long addCategorie(String name);
	
	void deleteCategorie(Long id);
	
}
