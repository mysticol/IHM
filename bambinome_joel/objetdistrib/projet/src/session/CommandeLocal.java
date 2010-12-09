package session;


import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Client;
import entity.Commande;
import entity.ProduitStub;

@Stateless
public class CommandeLocal implements CommandeBackLocal {

	@PersistenceContext
	private EntityManager em;

	private ProduitStubLocal prd;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Commande> getList() {
		Query query = em.createQuery("from Commande ");

		return query.getResultList();

	}

	@Override
	public Commande getById(int id) {
		return em.find(Commande.class, id);
	}

	@Override
	public void delete(int id) {
		em.remove(em.find(Commande.class, id));

	}

	@Override
	public int createEntity(Commande enti) {
		
		HashMap<ProduitStub, Long> map=enti.getContenu();
		
		for (ProduitStub prddd: map.keySet()){
			if( prd.getById(prddd.getKey())==null){
				prd.createEntity(prddd);
			}
		}
	
		em.persist(enti);
		return enti.getId();
	}
	
	public int createCommande(Client cl, HashMap<ProduitStub, Long> contenu){
		Commande cmd= new Commande();
		cmd.setCl(cl);
		cmd.setContenu(contenu);		
		em.persist(cmd);
		return cmd.getId();
	}

}
