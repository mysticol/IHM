package session;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Commande;


@Stateless
public class CommandeLocal implements CommandeBackLocal{

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Commande> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Commande getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
}
