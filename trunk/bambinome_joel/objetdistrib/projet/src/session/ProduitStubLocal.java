package session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.ProduitStub;

@Stateless
public class ProduitStubLocal implements ProduitStubBackLocal {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProduitStub> getList() {
		Query query = em.createQuery("from produitstub ");

		return query.getResultList();

	}

	@Override
	public ProduitStub getById(int id) {

		return em.find(ProduitStub.class, id);
	}

	@Override
	public void delete(int id) {
		em.remove(em.find(ProduitStub.class, id));

	}

	@Override
	public int createEntity(ProduitStub enti) {
			em.persist(enti);
		return enti.getId();
	}

	@Override
	public int createStubProduit(String modele, String marque,
			String fournisseur) {
		ProduitStub prd= new ProduitStub();
		
		prd.setModele(modele);
		prd.setMarque(marque);
		prd.setFournisseur(fournisseur);
		em.persist(prd);
		
		return prd.getId();
	}

}
