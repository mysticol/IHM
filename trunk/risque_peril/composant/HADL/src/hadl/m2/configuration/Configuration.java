package hadl.m2.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import hadl.m2.composant.Composant;
import hadl.m2.composant.IComposant;
import hadl.m2.connecteur.IConnecteur;

public class Configuration extends Composant implements IConfiguration {
	
	// classes permettant de stocker la destination d'un lien
	protected interface To{}
		
	protected class ToConnect implements To{
		IConnecteur connect;
		Integer rolesFrom;
		public ToConnect(IConnecteur connect, Integer rolesFrom) {
			super();
			this.connect = connect;
			this.rolesFrom = rolesFrom;
		}
	}
	
	protected class ToCompo implements To{
		IComposant compo;
		Integer portIn;
		public ToCompo(IComposant compo, Integer portIn) {
			super();
			this.compo = compo;
			this.portIn = portIn;
		}
	}
	
	protected class ToPort implements To{
		Integer portConfig;
		public ToPort(Integer portConfig) {
			super();
			this.portConfig = portConfig;
		}
	}
	// -------------------------------------------------------
		
	
	// Attribut
	protected Map<IComposant , Map<Integer, To>> composInterne;
	protected Map<IConnecteur , Map<Integer, To>> connectsInterne;
	protected Map<Integer , To> bindingIn;
	// -------------------------------------------------------
	
	// Constructeur 
	
	public Configuration(String contraintes, String proprietes,
			Map<Integer, String> ports,
			Map<IComposant, Map<Integer, To>> composInterne,
			Map<IConnecteur, Map<Integer, To>> connectsInterne,
			Map<Integer, To> bindingIn) {
		super(contraintes, proprietes, ports);
		this.composInterne = composInterne;
		this.connectsInterne = connectsInterne;
		this.bindingIn = bindingIn;
		
		// lancement de l'observation sur les composants internes de la configuration
		for(IComposant c : this.composInterne.keySet()){
			c.addObserver(this);
		}
		
		// lancement de l'observation sur les connecteurs internes de la configuration
		for(IConnecteur c : this.connectsInterne.keySet()){
			c.addObserver(this);
		}
	}

	public Configuration(){
		super();
		
		this.composInterne = new HashMap<IComposant, Map<Integer,To>>();
		this.connectsInterne = new HashMap<IConnecteur, Map<Integer,To>>();
		this.bindingIn = new HashMap<Integer, Configuration.To>();		
		
	}
	// -------------------------------------------------------
	
	// methode de fonctionnement interne
	public void update(Observable o, Object arg) {
		Object[] args = (Object[])arg;
		
		if(o instanceof IComposant){ // l'object qui a levé le notify est un composant
			
			// on recupère le destinataire des données que le composant veux envoyer
			To dest = composInterne.get(o).get(args[0]);
						
			if(dest instanceof ToPort){ // si le lien attaché au port du composant est un lien binding, on lève le notify, qui sera capturé par la configuration supérieure
				// on crée une donnée composer du numero de port visé et des données a envoyer
				Object[] data = new Object[2];
				data[0] = ((ToPort) dest).portConfig;
				data[1] = args[1];
				this.setChanged();
				this.notifyObservers(data);
			}else{ // sinon le lien est un lien vers un connecteur
				// on lance la glue du connecteur en lui passant le roleFrom du connecteur
				((ToConnect)dest).connect.glue(((ToConnect)dest).rolesFrom, args[1]);
			}
		}else if(o instanceof IConnecteur){
			// on recupère le destinataire des données que le composant veux envoyer
			To dest = connectsInterne.get(o).get(args[0]);
			((ToCompo)dest).compo.launch(((ToCompo)dest).portIn, args[1]);
		}else{
			System.out.println("espece de gros connard, tu as notify avec une classe qui a rien a foutre ici !!!!!");
		}

	}
	
	public void launch(Integer port, Object data){
		To dest = this.bindingIn.get(port);
		if(dest instanceof ToCompo){
			((ToCompo)dest).compo.launch(port, data);
		}
	}
	
	public void launch(Integer port){
		To dest = this.bindingIn.get(port);
		if(dest instanceof ToCompo){
			((ToCompo)dest).compo.launch(port);
		}
	}
	
	// -------------------------------------------------------
	
	// methode de gestion dynamique des Objet interne a la configuration
	public void addComposant(IComposant newCompo) {
		newCompo.addObserver(this);
		this.composInterne.put(newCompo, new HashMap<Integer, Configuration.To>());
	}

	public void addConnecteur(IConnecteur newConnect) {
		newConnect.addObserver(this);
		this.connectsInterne.put(newConnect,new HashMap<Integer, Configuration.To>());	
	}

	public void addAttachement(IComposant compoDepart, Integer portOut,	IConnecteur connectArriver, Integer rolesFrom) {
		this.composInterne.get(compoDepart).put(portOut, new ToConnect(connectArriver, rolesFrom)); 
	}

	public void addAttachement(IConnecteur connectDepart, Integer rolesTo, IComposant compoArriver, Integer portIn) {
		this.connectsInterne.get(connectDepart).put(rolesTo, new ToCompo(compoArriver, portIn));
	}

	public void addBinding(Integer portConfigDepart, IComposant compoDest, Integer portCompo) {
		this.bindingIn.put(portConfigDepart, new ToCompo(compoDest,portCompo));
	}
	
	public void addBinding(IComposant compoDepart, Integer portCompo, Integer portConfigDest) {
		this.composInterne.get(compoDepart).put(portCompo, new ToPort(portConfigDest));
	}
	
	public void removeComposant(IComposant compo) {
		this.composInterne.remove(compo);
		for(IConnecteur c : this.connectsInterne.keySet()){
			for(Integer p :  this.connectsInterne.get(c).keySet()){
				To dest = this.connectsInterne.get(c).get(p);
				if((dest instanceof ToCompo) && (((ToCompo)dest).compo == compo)){
					this.connectsInterne.get(c).put(p,null); // TODO si ca pete faire le put en dehor du for
				}
			}
		}
	}

	public void removeConnecteur(IConnecteur connect) {
		this.connectsInterne.remove(connect);
		for(IComposant c : this.composInterne.keySet()){
			for(Integer p :  this.composInterne.get(c).keySet()){
				To dest = this.composInterne.get(c).get(p);
				if((dest instanceof ToConnect) && (((ToConnect)dest).connect == connect)){
					this.composInterne.get(c).put(p,null); // TODO si ca pete faire le put en dehor du for
				}
			}
		}
	}

	public void removeAttachement(IComposant compoDepart, Integer portOut) {
		this.composInterne.get(compoDepart).remove(portOut);
	}

	public void removeAttachement(IConnecteur connectDepart, Integer rolesTo) {
		this.connectsInterne.get(connectDepart).remove(rolesTo);
	}

	public void removeBinding(Integer portConfigDepart) {
		this.bindingIn.remove(portConfigDepart);
	}

	public void removeBinding(IComposant compoDepart, Integer portCompo) {
		this.composInterne.get(compoDepart).remove(portCompo);
	}
}
