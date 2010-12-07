package session;


import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;


import entity.ProduitKey;
import entity.ProduitStub;

@Remote
public interface ProduitStubBackLocal {

	public int createStubProduit(String modele, String marque, String fournisseur);
	public List<ProduitStub> getList();
	public ProduitStub getById(ProduitKey id);
	public void delete(ProduitKey id);
	public int createEntity(ProduitStub enti);
	
}
