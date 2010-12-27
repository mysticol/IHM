package catalogue;
import javax.ejb.Local;

@Local
public interface CatalogueServiceLocal {
	
	Long addProduct(String description, Double price, String nomCateg, String marque, String modele, Integer quantity);

	void deleteProduct(Long id);
	
	Long addCategorie(String name);
	
	void deleteCategorie(Long id);
	
}
