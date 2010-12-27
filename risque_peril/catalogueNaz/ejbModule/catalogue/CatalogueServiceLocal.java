package catalogue;
import java.util.List;

import javax.ejb.Local;

import entity.ECategorie;
import entity.EProduit;

@Local
public interface CatalogueServiceLocal {
	
	Long addProduct(String description, Double price, ECategorie categorie, String marque, String modele, Long quantity);

	void deleteProduct(Long id);
	
	Long addCategorie(String name, List<EProduit> listProduit);
	
	void deleteCategorie(Long id);
	
}
