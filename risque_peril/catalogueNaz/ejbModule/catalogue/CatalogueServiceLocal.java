package catalogue;
import java.util.List;

import javax.ejb.Local;

import lib.ICategorie;
import lib.IProduit;


@Local
public interface CatalogueServiceLocal {
	
	Long addProduct(String description, Double price, ICategorie categorie, String marque, String modele, Long quantity);

	void deleteProduct(Long id);
	
	Long addCategorie(String name, List<IProduit> listProduit);
	
	void deleteCategorie(Long id);
	
}
