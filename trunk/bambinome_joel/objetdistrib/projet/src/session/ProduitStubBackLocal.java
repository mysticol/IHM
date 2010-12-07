package session;


import javax.ejb.Local;


import entity.ProduitStub;

@Local
public interface ProduitStubBackLocal extends SessionEntityManager<ProduitStub>{

	public int createStubProduit(String modele, String marque, String fournisseur);
	
}
