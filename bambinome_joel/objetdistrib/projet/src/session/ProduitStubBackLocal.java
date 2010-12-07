package session;


import javax.ejb.Local;
import javax.ejb.Remote;


import entity.ProduitStub;

@Remote
public interface ProduitStubBackLocal extends SessionEntityManager<ProduitStub>{

	public int createStubProduit(String modele, String marque, String fournisseur);
	
}
