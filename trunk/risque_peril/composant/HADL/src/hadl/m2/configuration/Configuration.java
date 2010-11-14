package hadl.m2.configuration;

import java.awt.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import hadl.lib.LogWriter;
import hadl.m2.composant.Composant;
import hadl.m2.composant.IComposant;
import hadl.m2.connecteur.IConnecteur;

public abstract class Configuration extends Composant implements IConfiguration {
	
	// classes permettant de stocker la destination d'un lien
	protected interface To{
		public String toString();
	}
		
	protected class ToConnect implements To{
		IConnecteur connect;
		String rolesFrom;
		public ToConnect(IConnecteur connect, String rolesFrom) {
			super();
			this.connect = connect;
			this.rolesFrom = rolesFrom;
		}
		public String toString() {
			return(connect.getNom() + ":" + rolesFrom);
		}
	}
	
	protected class ToCompo implements To{
		IComposant compo;
		String portIn;
		public ToCompo(IComposant compo, String portIn) {
			super();
			this.compo = compo;
			this.portIn = portIn;
		}
		public String toString() {
			return(compo.getNom()+ ":" + portIn);
		}
	}
	
	protected class ToPortConfig implements To{
		String portConfig;
		public ToPortConfig(String portConfig) {
			super();
			this.portConfig = portConfig;
		}
		public String toString() {
			return(portConfig);
		}
	}
	// -------------------------------------------------------
		
	
	// Attribut
	protected Map<IComposant , Map<String, To>> composInterne;
	protected Map<IConnecteur , Map<String, To>> connectsInterne;
	protected Map<String , To> bindingIn;
	
	protected LogWriter lw;
	// -------------------------------------------------------
	
	// Constructeur 
	public Configuration(String contraintes, String proprietes,
			Map<String, String> portsIn, Map<String, String> portsOut,
			Map<IComposant, Map<String, To>> composInterne,
			Map<IConnecteur, Map<String, To>> connectsInterne,
			Map<String, To> bindingIn,
			String nom) {
		super(contraintes, proprietes, portsIn, portsOut, nom);
		this.composInterne = composInterne;
		this.connectsInterne = connectsInterne;
		this.bindingIn = bindingIn;
		this.lw = LogWriter.getInstance();
		this.lw.init(this.getClass().getName());
		
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
		this.composInterne = new HashMap<IComposant, Map<String,To>>();
		this.connectsInterne = new HashMap<IConnecteur, Map<String,To>>();
		this.bindingIn = new HashMap<String, Configuration.To>();
		this.lw = LogWriter.getInstance();
		this.lw.init(this.getClass().getName());
		this.nom = this.getClass().getName();
	}
	
	public Set<IComposant> getListComposant(){
		return this.composInterne.keySet();
	}
	
	public Set<IConnecteur> getListConnecteur(){
		return this.connectsInterne.keySet();
	}
	
	public IComposant getComposant(String name){
		IComposant compo = null;
		
		for(IComposant c : this.composInterne.keySet()){
			if(c.getNom().equalsIgnoreCase(name)){
				compo = c;
			}
		}
		return compo;
	}
	
	public IConnecteur getConnecteur(String name){
		IConnecteur connect = null;
		
		for(IConnecteur c : this.connectsInterne.keySet()){
			if(c.getNom().equalsIgnoreCase(name)){
				connect = c;
			}
		}
		
		return connect;
	}
	
	public Set<String> getListPort(){
		return this.bindingIn.keySet();
	}
	
	// methode d'affichage d'une configuration
	public void print(){
		System.out.println(this.getNom());
		System.out.println("  | lien interne au depart de son interface :");
		for(String p : this.bindingIn.keySet()){
			System.out.println("  |- " + p + " -> " + this.bindingIn.get(p).toString());
		}
		System.out.println("Composants internes :");
		for(IComposant c : this.composInterne.keySet()){
			System.out.println(" " + c.getNom());
			System.out.println("  | lien au depart de ce composant :");
			for(String p : this.composInterne.get(c).keySet()){
				System.out.println("  |- " + p + " -> " + this.composInterne.get(c).get(p).toString());
			}
		}
		System.out.println("Connecteurs internes :");
		for(IConnecteur c : this.connectsInterne.keySet()){
			System.out.println(" " + c.getNom());
			System.out.println("  | lien au depart de ce connecteur :");
			for(String p : this.connectsInterne.get(c).keySet()){
				System.out.println("  |- "  + p + " -> " + this.connectsInterne.get(c).get(p).toString());
			}
		}
	}
	
	// -------------------------------------------------------
	
	// methode de fonctionnement interne
	public void update(Observable o, Object arg) {
		Object[] args = (Object[])arg;
		
		if(o instanceof IComposant){ // l'object qui a levé le notify est un composant
			// on test si le lien existe
			if(composInterne.get(o).containsKey(args[0])){
				// on recupère le destinataire des données que le composant veux envoyer
				To dest = composInterne.get(o).get(args[0]);

				if(dest instanceof ToPortConfig){ // si le lien attaché au port du composant est un lien binding, on lève le notify, qui sera capturé par la configuration supérieure
					// on crée une donnée composer du numero de port visé et des données a envoyer
					Object[] data = new Object[2];
					data[0] = ((ToPortConfig) dest).portConfig;
					data[1] = args[1];
					this.setChanged();
					this.notifyObservers(data);
				}else{ // sinon le lien est un lien vers un connecteur
					// on lance la glue du connecteur en lui passant le roleFrom du connecteur
					((ToConnect)dest).connect.glue(((ToConnect)dest).rolesFrom, args[1]);
				}
			}else{
				this.lw.writewarn(this.getClass().getName(), "Le lien activer " + ((IComposant)o).getNom()+":"+ args[0] + " n'existe pas ou plus!!");
			}
		}else if(o instanceof IConnecteur){
			// on test si le lien existe
			if(connectsInterne.get(o).containsKey(args[0])){
				// on recupère le destinataire des données que le composant veux envoyer
				To dest = connectsInterne.get(o).get(args[0]);
				((ToCompo)dest).compo.launch(((ToCompo)dest).portIn, args[1]);
			}else{
				this.lw.writewarn(this.getClass().getName(), "Le lien activer " + ((IComposant)o).getNom()+":"+ args[0] + " n'existe pas ou plus!!");
			}
		}else{
			this.lw.writewarn(this.getClass().getName(), "Une classe non autorisé est gérer pas " + this.getNom());
		}
	}
	
	// method appelée depuis l'exterieur pour lancer une méthode liée à un port
	public void launch(String port, Object data){
		if(this.bindingIn.containsKey(port)){
			To dest = this.bindingIn.get(port);
			if(dest instanceof ToCompo){
				((ToCompo)dest).compo.launch(((ToCompo)dest).portIn, data);
			}
		}else{
			this.lw.writewarn(this.getClass().getName(), "Le Port "+ this.getNom() + ":"+port+" est non lier a un service");
		}
	}
	
	public void launch(String port){
		if(this.bindingIn.containsKey(port)){
			To dest = this.bindingIn.get(port);
			if(dest instanceof ToCompo){
				((ToCompo)dest).compo.launch(((ToCompo)dest).portIn);
			}
		}else{
			this.lw.writewarn(this.getClass().getName(), "Le Port "+ this.getNom() + ":"+port+" est non lier a un service");
		}
	}
	
	// -------------------------------------------------------
	
	// methode de gestion dynamique des Objet interne a la configuration
	public void addComposant(IComposant newCompo) {
		newCompo.addObserver(this);
		this.lw.writejl(this.getClass().getName(), "Ajout du composant : " + newCompo.getNom());
		this.composInterne.put(newCompo, new HashMap<String, Configuration.To>());
	}

	public void addConnecteur(IConnecteur newConnect) {
		newConnect.addObserver(this);
		this.lw.writejl(this.getClass().getName(), "Ajout du connecteur : " + newConnect.getNom());
		this.connectsInterne.put(newConnect,new HashMap<String, Configuration.To>());	
	}

	public void addAttachement(IComposant compoDepart, String portOut,	IConnecteur connectArriver, String rolesFrom) {
		this.lw.writejl(this.getClass().getName(), "Ajout du lien d'attachement : " + compoDepart.getNom()+":"+portOut+" -> "+connectArriver.getNom()+":"+rolesFrom);
		this.composInterne.get(compoDepart).put(portOut, new ToConnect(connectArriver, rolesFrom)); 
	}

	public void addAttachement(IConnecteur connectDepart, String rolesTo, IComposant compoArriver, String portIn) {
		this.lw.writejl(this.getClass().getName(), "Ajout du lien d'attachement : " + connectDepart.getNom()+":"+rolesTo+" -> "+compoArriver.getNom()+":"+portIn);
		this.connectsInterne.get(connectDepart).put(rolesTo, new ToCompo(compoArriver, portIn));
	}

	public void addBinding(String portConfigDepart, IComposant compoDest, String portCompo) {
		this.lw.writejl(this.getClass().getName(), "Ajout du lien binding : " + this.getNom()+":"+portConfigDepart+" -> "+compoDest.getNom()+":"+portCompo);
		this.bindingIn.put(portConfigDepart, new ToCompo(compoDest,portCompo));
	}
	
	public void addBinding(IComposant compoDepart, String portCompo, String portConfigDest) {
		this.lw.writejl(this.getClass().getName(), "Ajout du lien binding : " + compoDepart.getNom()+":"+portCompo+" -> "+ this.getNom()+":"+portConfigDest);
		this.composInterne.get(compoDepart).put(portCompo, new ToPortConfig(portConfigDest));
	}
	
	public void removeComposant(IComposant compo) {
		
		if(this.composInterne.containsKey(compo)){
			
			this.lw.write(this.getClass().getName(), "Debut de la destruction du Composant : " +compo.getNom());
			// on recherche dans les connecteur les lien attachement vers le composant a suprimer
			for(IConnecteur c : this.connectsInterne.keySet()){
				LinkedList<String> compoToRemove = new LinkedList<String>();
				for(String p :  this.connectsInterne.get(c).keySet()){
					To dest = this.connectsInterne.get(c).get(p);
					if((dest instanceof ToCompo) && (((ToCompo)dest).compo.equals(compo))){
						// si le lien tester est un lien vers le composant a suprimer on le stock dans une liste
						compoToRemove.add(p);
						// this.connectsInterne.get(c).put(p,null);
					}
				}
				// on suprime les lien d'attachement lier au composant
				for(String p : compoToRemove){
					this.lw.write(this.getClass().getName(), "     Destruction du lien : " + c.getNom()+ ":" + p + " -> " + this.connectsInterne.get(c).get(p).toString());
					this.connectsInterne.get(c).remove(p);
				}
			}
			
			// on recherche les lien binding vers le composant a suprimer
			LinkedList<String> compoToRemove = new LinkedList<String>();
			for(String p : this.bindingIn.keySet()){
				ToCompo c = (ToCompo) this.bindingIn.get(p);
				if(c.compo.equals(compo)){
					// si le lien tester est un lien vers le composant a suprimer on le stock dans une liste
					compoToRemove.add(p);
				}
			}
			// on suprime les lien binding lier au composant
			for(String p : compoToRemove){
				this.lw.write(this.getClass().getName(), "     Destruction du lien : " + this.getNom()+ ":" + p + " -> " + this.bindingIn.get(p).toString());
				this.bindingIn.remove(p);
			}
			
			// on suprime le composant
			for(String p  : this.composInterne.get(compo).keySet() ){
				this.lw.write(this.getClass().getName(), "     Destruction du lien : " + this.getNom()+ ":" + p + " -> " + this.composInterne.get(compo).get(p).toString());
			}
			compo.deleteObserver(this);
			this.composInterne.remove(compo);
			this.lw.writejl(this.getClass().getName(), "Fin de la destruction du Composant : " + compo.getNom());
		}else{
			this.lw.writewarn(this.getClass().getName(),"Impossible de le suprimer le Composant : "+ compo.getNom()+ " : Composant inexistant");
		}
	}

	public void removeConnecteur(IConnecteur connect) {
		if(this.connectsInterne.containsKey(connect)){
			this.lw.write(this.getClass().getName(), "Debut de la destruction du Connecteur : " + connect.getNom());
			// on recherche dans les composant les lien attachement vers le connecteur a suprimer
			for(IComposant c : this.composInterne.keySet()){
				LinkedList<String> connectToRemove = new LinkedList<String>();
				for(String p :  this.composInterne.get(c).keySet()){
					To dest = this.composInterne.get(c).get(p);
					if((dest instanceof ToConnect) && (((ToConnect)dest).connect.equals(connect))){
						// si le lien tester est un lien vers le connecteur a suprimer on le stock dans une liste
						connectToRemove.add(p);
					}
				}
				// on suprime les lien lier au composant
				for(String p : connectToRemove){
					this.lw.write(this.getClass().getName(), "     Destruction du lien : " + c.getNom()+ ":" + p + " -> " + this.composInterne.get(c).get(p).toString());
					this.composInterne.get(c).remove(p);
				}
			}
			
			// on suprime le connecteur
			for(String p  : this.connectsInterne.get(connect).keySet() ){
				this.lw.write(this.getClass().getName(), "     Destruction du lien : " + this.getNom()+ ":" + p + " -> " + this.connectsInterne.get(connect).get(p).toString());
			}
			connect.deleteObserver(this);
			this.connectsInterne.remove(connect);
			this.lw.writejl(this.getClass().getName(), "Fin de la destruction du Connecteur : " + connect.getNom());
		}else{
			this.lw.writewarn(this.getClass().getName(),"Impossible de le suprimer le Connecteur : "+ connect.getNom()+ " : Connecteur inexistant");
		}
	}

	public void removeAttachement(IComposant compoDepart, String portOut) {
		this.lw.writejl(this.getClass().getName(), "Destruction du lien d'attachement : " + compoDepart.getNom()+":"+portOut+" -> "+ this.composInterne.get(compoDepart).get(portOut).toString());
		this.composInterne.get(compoDepart).remove(portOut);
	}

	public void removeAttachement(IConnecteur connectDepart, String rolesTo) {
		this.lw.writejl(this.getClass().getName(), "Destruction du lien d'attachement : " + connectDepart.getNom()+":"+rolesTo+" -> "+ this.composInterne.get(connectDepart).get(rolesTo).toString());
		this.connectsInterne.get(connectDepart).remove(rolesTo);
	}

	public void removeBinding(String portConfigDepart) {
		this.lw.writejl(this.getClass().getName(), "Destruction du lien binding : " + this.getNom()+":"+portConfigDepart+" -> "+ this.bindingIn.get(portConfigDepart).toString());
		this.bindingIn.remove(portConfigDepart);
	}

	public void removeBinding(IComposant compoDepart, String portCompo) {
		this.lw.writejl(this.getClass().getName(), "Destruction du lien binding : " + compoDepart.getNom()+":"+portCompo+" -> "+ this.composInterne.get(compoDepart).get(portCompo).toString());
		this.composInterne.get(compoDepart).remove(portCompo);
	}
}
