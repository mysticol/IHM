package session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Adresse;
import entity.Client;

/**
 * Session Bean implementation class ClientBack
 */
@Stateless
public class ClientBack implements ClientBackLocal {

	@PersistenceContext
	private EntityManager em;

	public ClientBack() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getList() {
		Query query = em.createQuery("from Client ");

		return query.getResultList();
	}

	@Override
	public Client getById(int id) {

		return em.find(Client.class, id);
	}

	@Override
	public void delete(int id) {
		em.remove(em.find(Client.class, id));

	}

	@Override
	public int createClient(String prenom, String nom, Adresse add) {
		Client cl = new Client();
		cl.setAdd(add);
		cl.setNom(nom);
		cl.setPrenom(prenom);
		em.persist(cl);
		return cl.getId();
	}

	@Override
	public int createEntity(Client enti) {
		em.persist(enti);
		return enti.getId();
	}

}
