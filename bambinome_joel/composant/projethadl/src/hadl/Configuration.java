package hadl;

import hadl.com.Attachement;
import hadl.com.Binding;
import hadl.com.Lien;
import hadl.com.SignalComposant;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import java.util.Observable;
import java.util.Observer;

public class Configuration extends BriqueComposant implements Observer {

	private static final long serialVersionUID = 1L;

	// sert pour l'enchainement des actions entre composant et connecteur
	private HashMap<SignalComposant, Attachement> roadMap;
	// sert pour les sorties et entré en bind
	private HashMap<SignalComposant, Binding> bindingMap;
	private HashMap<Integer, Binding> portServiceMapIng;

	/*
	 * TODO: // Rajouter une file d'évément qui permet de reprendre le
	 * traitement si on lancer le bousin mettre un booléen start/stop pour à
	 * froid a chaud tout ça permettrais des choses p'etre intéressante
	 */
	

	// Conservation de l'ordre dans la file très important !
	// Si on peut exécuter que un et pas toute la file pas grave

	

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
		// délégation du service
		comp.appelPortIn(bind.getPortComposantFrom(), agrs);
	}

	@Override
	public final void update(Observable o, Object arg) {

		if (arg instanceof SignalComposant) {
			System.out.println(arg);
			SignalComposant recu = (SignalComposant) arg;
			// On va récupérer d'abord dans la map d'attachement
			Attachement gAttach = roadMap.get(recu);
			// ensuite dans la map du bind
			Binding gBind = bindingMap.get(recu);

			// si attachement on va appeler la glue puis le port de l'autre
			// méthode
			if (gAttach != null) {
				System.out.println(gAttach);
				Attachement temp = (Attachement) gAttach;
				Object Value = bibComposant.get(recu.getName()).appelPortOut(
						recu.getPort());

				BriqueComposant cible = bibComposant.get(temp
						.getNameComposantTo());
				Connector conn = bibConnector.get(temp.getNameConnector());

				try {
					Object[] tab = { Value };
					Method m = this
							.getMethodByName(conn, temp.getMethod(), tab);
					if (m != null) {
						// invocation du lien attachement en passant les
						// argument passer a la glue

						Object[] tav = { m.invoke(conn, Value) };

						cible.appelPortIn(temp.getPortComposantTo(), tav);
					} else {
						throw new InvocationTargetException(new Throwable(
								"Methode Inconnue"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				// sinon si il y a un binding on fini
			} else if (gBind != null) {

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

	public final void addLien(Lien li) {

		// ajout d'un lien simple dans la road map

		// ajout dans le maping binding si le lien est un binding
		if (li instanceof Binding) {
			Binding temp = (Binding) li;
			this.portServiceMapIng
					.put(((Binding) li).getPortBindConfig(), temp);
			this.bindingMap.put(new SignalComposant(li.getNomComposantFrom(),
					li.getPortComposantFrom()), (Binding) li);

		} else if (li instanceof Attachement) {
			this.roadMap.put(
					new SignalComposant(li.getNomComposantFrom(), li
							.getPortComposantFrom()), (Attachement) li);
		}

	}

	private final void reload() {

	}
	
	/*
	 * Ajout d'un composant et reprise du fonctionnement si besoin
	 */
	public final void addComposant(BriqueComposant brique) {
		bibComposant.put(brique.getName(), brique);
		brique.addObserver(this);
		this.reload();
	}

	/*
	 * Ajout d'un connecteur et reprise du fonctionnement si besoin
	 */
	public final void addConnector(Connector con) {
		bibConnector.put(con.getName(), con);
		this.reload();
	}

	// on enlève l'objet mais pas la référence
	public final void removeComposant(String brique) {
		BriqueComposant comp = bibComposant.get(brique);
		comp.deleteObserver(this);
		bibComposant.put(brique, null);
	}

	// on enlève l'objet mais pas la référence
	public final void removeConnector(String con) {
		bibConnector.put(con, null);
	}

	//	suppression définitive
	public final void deleteComposant(String com) {
		BriqueComposant comp = bibComposant.get(com);
		comp.deleteObserver(this);
		bibComposant.remove(comp);
	}
	
	//	suppression définitive
	public final void deleteConnector(String con) {
		bibConnector.remove(con);
	}

	//Inverse de l'ajout de lien
	//on supprime simplement le lien des maps
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

}
