package session;


import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.ejb.Stateful;
import javax.naming.Context;
import javax.naming.InitialContext;

import entity.Client;
import entity.Commande;
import entity.ProduitKey;
import entity.ProduitStub;
import fr.alma.interfaces.CentralRemote;
import fr.alma.interfaces.IAProduit;
import fr.alma.interfaces.ICategorie;
import fr.alma.interfaces.IFrontCategorie;
import fr.alma.interfaces.IItem;

/**
 * Session Bean implementation class Panier
 */
@Stateful

public class Panier implements PanierLocal, PanierRemote {

	
	CentralRemote central;
	
	
	@EJB
	CommandeBackLocal commandBack;
	
	@EJB
	ClientBackLocal clientBack;
	
	private Client client;
	
	
	
	private HashMap<IItem, Long > panier;
	
	
	
	/* (non-Javadoc)
	 * @see session.h#identificationClient(int)
	 */
	@Override
	public boolean identificationClient(int idClient){
		client= clientBack.getById(idClient);
		return client!=null;
	}
	
	
    /**
     * Default constructor. 
     */
    public Panier() {
    	
    }


    @PostConstruct
    public void initRemote() throws Exception{
    	Properties props = new Properties();

    	props.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
    	props.setProperty("java.naming.factory.url.pkgs", "jboss.naming.org:jnp.interfaces");
    	props.setProperty("java.naming.provider.url", "172.16.134.152:1099");

    	Context context = new InitialContext(props);
    	central =
		    (CentralRemote) context.lookup("CentralService/remote");
    }
    
	/* (non-Javadoc)
	 * @see session.h#ajoutProduitPanier(fr.alma.interfaces.IItem, java.lang.Long)
	 */
	@Override
	public void ajoutProduitPanier(IItem produit, Long num) {
		
	
		
		panier.put(produit, num);
	}

	/* (non-Javadoc)
	 * @see session.h#retirerProduitPanier(entity.ProduitKey)
	 */
	@Override
	public void retirerProduitPanier(ProduitKey key) {
		
		IItem iitem= new IItem();
		iitem.setFournisseur(key.getFournisseur());
		iitem.setMarque(key.getMarque());
		iitem.setModel(key.getMarque());
		
		panier.remove(iitem);
	}
	
	
	/* (non-Javadoc)
	 * @see session.h#passerCommande()
	 */
	@Override
	public boolean passerCommande() {

		
		
		
		Boolean result=central.order(panier);
		if(result){
			commandBack.createEntity(this.voirEtatCommande());
		}
		
		
		return result;
	}

	
	/* (non-Javadoc)
	 * @see session.h#voirEtatCommande()
	 */
	@Override
	public Commande voirEtatCommande() {
		Commande cmd= new Commande();
		HashMap<ProduitStub, Long> mapcommande= new HashMap<ProduitStub, Long>();
		
		for(IItem tem: panier.keySet()){
			mapcommande.put(new ProduitStub(tem.getModel(), tem.getMarque(), tem.getFournisseur()), panier.get(tem));	
		}
		cmd.setCl(client);
		cmd.setContenu(mapcommande);
		return cmd;
	}
	
	/* (non-Javadoc)
	 * @see session.h#findProduitsByCategorie(fr.alma.interfaces.IFrontCategorie)
	 */
	@Override
	public List<IAProduit> findProduitsByCategorie(IFrontCategorie arg0){
		return central.findProduitsByCategorie(arg0);
	}
	

	
	
	/* (non-Javadoc)
	 * @see session.h#findByCategorieAndMarqueAndPriceRange(fr.alma.interfaces.IFrontCategorie, java.lang.String, double, double)
	 */
	@Override
	public List<IAProduit> findByCategorieAndMarqueAndPriceRange(IFrontCategorie arg0,String arg1 , double arg2, double arg3) {
			return central.findByCategorieAndMarqueAndPriceRange(arg0, arg1, arg2, arg3);
	}

	
	/* (non-Javadoc)
	 * @see session.h#findByCategorieAndPriceRange(fr.alma.interfaces.IFrontCategorie, double, double)
	 */
	@Override
	public List<IAProduit> findByCategorieAndPriceRange( IFrontCategorie arg0, double arg1, double arg2) {	
	
		return central.findByCategorieAndPriceRange(arg0, arg1, arg2);
	}

	
	/* (non-Javadoc)
	 * @see session.h#getCategories()
	 */
	@Override
	public List<IFrontCategorie> getCategories() {
		return central.findAllCategories();
	}
	
	
	
	
	

}
