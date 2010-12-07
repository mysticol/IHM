package session;


import javax.ejb.Stateful;

import entity.Commande;

/**
 * Session Bean implementation class Panier
 */
@Stateful

public class Panier implements PanierLocal {

    /**
     * Default constructor. 
     */
    public Panier() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean ajoutProduitPanier(int id, Long num) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean passerCommande() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Commande voirEtatCommande() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getCategorieProduit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getListeProduit() {
		// TODO Auto-generated method stub
		
	}

}
