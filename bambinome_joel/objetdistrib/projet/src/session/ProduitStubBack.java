package session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.ProduitKey;
import entity.ProduitStub;

@Stateless
public class ProduitStubBack implements ProduitStubLocal, ProduitStubRemote {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProduitStub> getList() {
		Query query = em.createQuery("from ProduitStub ");

		return query.getResultList();

	}

	@Override
	public ProduitStub getById(ProduitKey id) {

		return em.find(ProduitStub.class, id);
	}

	@Override
	public void delete(ProduitKey id) {
		em.remove(em.find(ProduitStub.class, id));

	}

	@Override
	public int createEntity(ProduitStub enti) {
		try{
			em.persist(enti);
		}catch (Exception e) {
			return 0;
		}
			
		return 1;
	}

	@Override
	public int createStubProduit(String modele, String marque,
			String fournisseur) {
		ProduitStub prd= new ProduitStub();
		
		prd.setModele(modele);
		prd.setMarque(marque);
		prd.setFournisseur(fournisseur);
		try{
		em.persist(prd);
		}catch (Exception e) {
			return 0;
		}
		
		
		return 1;
	}



}
