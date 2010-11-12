package hadl.m2;

import hadl.m2.com.Attachement;
import hadl.m2.com.Binding;
import hadl.m2.com.BindingType;
import hadl.m2.com.Lien;
import hadl.m2.com.event.EventAttachement;
import hadl.m2.com.event.EventBinding;
import hadl.m2.com.event.EventComposant;
import hadl.m2.com.event.SignalComposant;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

public class Configuration extends BriqueComposant implements Observer {

	private static final long serialVersionUID = 1L;

	// sert pour l'enchainement des actions entre composant et connecteur
	private HashMap<SignalComposant, Attachement> roadMap;
	// pour tous les binding
	private HashMap<SignalComposant, Binding> bindingMap;
	//pour les binding dit "in" et "both"
	private HashMap<Integer, Binding> portServiceMapIng;

	// Conservation de l'ordre dans la file très important !
	// Si on peut exécuter que un et pas toute la file pas grave

	private LinkedList<EventComposant> eventQueue;

	/*
	 * 
	 */

	private HashMap<String, BriqueComposant> bibComposant;
	private HashMap<String, Connector> bibConnector;

	public Configuration(String name) {
		super(name);
		this.roadMap = new HashMap<SignalComposant, Attachement>();
		this.bibComposant = new HashMap<String, BriqueComposant>();
		this.bibConnector = new HashMap<String, Connector>();
		this.portServiceMapIng = new HashMap<Integer, Binding>();
		this.bindingMap = new HashMap<SignalComposant, Binding>();
		this.eventQueue = new LinkedList<EventComposant>();
	}

	/*
	 * déléguation de service de la config vers le composant correspondant.
	 */
	public final void appelPortIn(Integer i, Object[] agrs)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		// Récupération du bind correspondant
		Binding bind = this.portServiceMapIng.get(i);
		// on récupére le composant sur lequel on délègue
		BriqueComposant comp = bibComposant.get(bind.getNomComposantFrom());
		// délégation du service, si il est présent
		if (comp != null) {
			comp.appelPortIn(bind.getPortComposantFrom(), agrs);
		} else {
			//si le composant n'est pas là on ajoute un évènement dans la liste d'évènement à retraiter ensuite
			eventQueue.add(new EventBinding(agrs, bind, i));
		}
	}

	@Override
	public final void update(Observable o, Object arg) {

		if (arg instanceof SignalComposant) {
			System.out.println("Signal recu par la configuration "+ getName() + " : " +arg);
			SignalComposant recu = (SignalComposant) arg;
			// On va récupérer d'abord dans la map d'attachement
			Attachement gAttach = roadMap.get(recu);
			// ensuite dans la map du bind
			Binding gBind = bindingMap.get(recu);

			// si attachement on va appeler la glue puis le port de l'autre
			// méthode
			if (gAttach != null) {
				System.out.println("Signal lié a l'attachement : "+gAttach);
				Object Value = bibComposant.get(recu.getName()).appelPortOut(
						recu.getPort());
				// on tranfert à la méthode de traitement

				BriqueComposant cible = bibComposant.get(gAttach
						.getNameComposantTo());
				Connector conn = bibConnector.get(gAttach.getNameConnector());

				if (conn == null || cible == null) {
					eventQueue.add(new EventAttachement(Value, gAttach, recu));
				} else {
					traitementEvent(new EventAttachement(Value, gAttach, recu));
				}

				// sinon si c'est un binding on va notifier la brique composant du dessus
			} else if (gBind != null) {
				System.out.println("Signal lié au binding : "+gBind);
				// récupération de la sortie du bind
				Integer portOut = this.getOutForIn(gBind.getPortBindConfig());

				// on met dans notre port la valeur
				this.setValuePort(portOut, bibComposant.get(recu.getName())
						.appelPortOut(recu.getPort()));
				// on notifi que tout est dans notre port
				// que on a juste a getport tout ça
				this.setChanged();
				this.notifyObservers(new SignalComposant(getName(), portOut));

			}

		}
	}

	//ajout d'un lien
	public final void addLien(Lien li) {

		System.out.println("Ajout du lien" + li.toString());
		// ajout d'un lien simple dans la road map

		// ajout dans le maping binding si le lien est un binding
		if (li instanceof Binding) {
			Binding temp = (Binding) li;
			//si c'est un binding de type in ou both on enregistre le binding en entré
			if (((Binding) li).getType() != BindingType.OUT) {
				this.portServiceMapIng.put(((Binding) li).getPortBindConfig(),
						temp);
			}
			//enregistrement du binding en sortie
			if( ((Binding) li).getType() != BindingType.IN){
			this.bindingMap.put(new SignalComposant(li.getNomComposantFrom(),
					li.getPortComposantFrom()), (Binding) li);
			}
		} else if (li instanceof Attachement) {
			//enregistrement simple d'un attachement
			this.roadMap.put(
					new SignalComposant(li.getNomComposantFrom(), li
							.getPortComposantFrom()), (Attachement) li);
		}

	}

	/*
	 * Ajout d'un composant et reprise du fonctionnement si besoin
	 */
	public final void addComposant(BriqueComposant brique) {
		// diagnosticComposant(brique.getName());
		System.out.println("Ajout de la brique composant" + brique.toString());
		bibComposant.put(brique.getName(), brique);
		brique.addObserver(this);
		this.retraitementEventQueue();
	}

	/*
	 * Ajout d'un connecteur et reprise du fonctionnement si besoin
	 */
	public final void addConnector(Connector con) {
		// diagnosticConnecteur(con.getName());
		System.out.println("Ajout du connecteur" + con.toString());
		bibConnector.put(con.getName(), con);
		this.retraitementEventQueue();
	}

	// on enlève l'objet mais pas la référence
	public final void removeComposant(String brique) {
		diagnosticComposant(brique);
		BriqueComposant comp = bibComposant.get(brique);
		comp.deleteObserver(this);
		bibComposant.put(brique, null);
	}

	// on enlève l'objet mais pas la référence
	public final void removeConnector(String con) {
		diagnosticConnecteur(con);
		bibConnector.put(con, null);
	}

	// suppression définitive
	public final void deleteComposant(String com) {
		diagnosticComposant(com);
		BriqueComposant comp = bibComposant.get(com);
		comp.deleteObserver(this);
		bibComposant.remove(comp);
	}

	// suppression définitive
	public final void deleteConnector(String con) {
		diagnosticConnecteur(con);
		bibConnector.remove(con);
	}

	// Inverse de l'ajout de lien
	// on supprime simplement le lien des maps
	public final void deleteLien(Lien li) {

		if (li instanceof Binding) {
			Binding temp = (Binding) li;

			this.portServiceMapIng.remove(temp.getPortBindConfig());

			this.bindingMap.remove(new SignalComposant(temp
					.getNomComposantFrom(), temp.getPortComposantFrom()));

		} else if (li instanceof Attachement) {
			this.roadMap.remove(new SignalComposant(li.getNomComposantFrom(),
					li.getPortComposantFrom()));
		}

	}

	/**
	 * Methode faisant le diagnostic de ce qui ne fonctionnera plus sans la
	 * brique com^posant portant le nom
	 * 
	 * @param nom
	 */
	private void diagnosticComposant(String nom) {
		System.out.println("En retirant le composant portant le nom: " + nom
				+ " \n" + "cela affectera les éléments suivant :");

		String attachNeed = "Attachement(s) : ";
		for (Attachement attach : roadMap.values()) {
			if (attach.getNameComposantTo().equalsIgnoreCase(nom)
					|| attach.getNomComposantFrom().equalsIgnoreCase(nom)) {
				attachNeed += "\n" + attach.toString();
			}
		}
		System.out.println(attachNeed);

		String bindingNeed = "Bindind(s) : ";
		for (Binding attach : bindingMap.values()) {
			if (attach.getNomComposantFrom().equalsIgnoreCase(nom)) {
				bindingNeed += "\n" + attach.toString();
			}
		}
		
		for (Binding attach : bindingMap.values()) {
			if (attach.getNomComposantFrom().equalsIgnoreCase(nom)) {
				bindingNeed += "\n" + attach.toString();
			}
		}
		
		System.out.println(bindingNeed);

	}

	/**
	 * Methode faisant le diagnostic de ce qui ne fonctionnera plus sans le
	 * connecteur portant le nom
	 * 
	 * @param nom
	 */
	private void diagnosticConnecteur(String nom) {

		System.out.println("En retirant le connecteur portant le nom: " + nom
				+ " \n" + "cela affectera les éléments suivant :");

		String attachNeed = "Attachement(s) : ";
		for (Attachement attach : roadMap.values()) {
			if (attach.getNameConnector().equalsIgnoreCase(nom)) {
				attachNeed += "\n" + attach.toString();
			}
		}
		System.out.println(attachNeed);

	}

	/**
	 * Methode qui permet de lancer le traitement des évènements qui ont eut
	 * lieu pendant qu'un composant ou connecteur manquait
	 */
	private final void retraitementEventQueue() {

		// on traite si la file n'est pas vide
		boolean traitementFile = !eventQueue.isEmpty();

		// tant que on doit traiter
		while (traitementFile) {
			// on va prendre le premier élément de la liste
			EventComposant com = eventQueue.peekFirst();
			// si il y a un élément
			if (com != null) {
				// on le traite
				traitementFile = traitementEvent(com);
				if (traitementFile) {
					// si le traitement s'est bien passé on enlève l'évement de
					// la liste
					// sinon on s'arrête
					eventQueue.removeFirst();
				}
			} else {
				// sinon on s'arrête
				traitementFile = false;
			}
		}

	}

	/**
	 * Traitement effectif d'un évènement.
	 * 
	 * @param ev
	 * @return
	 */
	private final boolean traitementEvent(EventComposant ev) {

		boolean result = false;
		if (ev instanceof EventAttachement) {
			EventAttachement event = (EventAttachement) ev;
			if (this.roadMap.containsKey(event.getSignal())) {
				Attachement gAttach = event.getAttach();
				Object Value = event.getValue();

				BriqueComposant cible = bibComposant.get(gAttach
						.getNameComposantTo());
				Connector conn = bibConnector.get(gAttach.getNameConnector());

				if (cible != null && conn != null) {
					try {
						Object[] tab = { Value };
						Method m = this.getMethodByName(conn,
								gAttach.getMethod(), tab);
						if (m != null) {
							// invocation du lien attachement en passant les
							// argument passer a la glue
							Object[] tav = { m.invoke(conn, Value) };
							cible.appelPortIn(gAttach.getPortComposantTo(), tav);
							result = true;
						} else {
							throw new InvocationTargetException(new Throwable(
									"Methode Inconnue"));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				result = true;
			}
		} else if (ev instanceof EventBinding) {
			// retraitement d'un appel event bind
			EventBinding event = (EventBinding) ev;

			// Récupération du bind correspondant
			Binding bind = event.getBind();
			Object[] args = event.getValues();

			if (portServiceMapIng.get(event.getPort()).equals(bind)) {
				// on récupére le composant sur lequel on délègue
				BriqueComposant comp = bibComposant.get(bind
						.getNomComposantFrom());
				// délégation du service, si il est présent
				try {
					comp.appelPortIn(bind.getPortComposantFrom(), args);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				result = true;
			}

		}

		return result;
	}

}
