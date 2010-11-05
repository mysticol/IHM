package hadl;




import hadl.com.Attachement;
import hadl.com.Binding;
import hadl.com.Lien;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class Configuration extends BriqueComposant implements Observer {

	private static final long serialVersionUID = 1L;

	//sert pour l'enchainement des actions entre composant et connecteur
	private HashMap<SignalComposant, Lien> roadMap;
	private HashMap<Integer, Binding> portServiceMapIng;
	
	private HashMap<String, BriqueComposant> bibComposant;
	private HashMap<String , Connector> bibConnector;
	
	
	public Configuration(String name) {
		super(name);
		this.roadMap= new HashMap<SignalComposant, Lien>();
		this.bibComposant= new HashMap<String, BriqueComposant>();
		this.bibConnector= new HashMap<String, Connector>();	
		this.portServiceMapIng= new HashMap<Integer, Binding>();
	}

	/*
	 * déléguation de service de la config vers le composant correspondant.
	 */
	public final void  appelPortIn(Integer i, Object[] agrs) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		//Récupération du bind correspondant
		Binding bind=this.portServiceMapIng.get(i);
		//on récupére le composant sur lequel on délègue
		BriqueComposant comp=bibComposant.get(bind.getNomComposantFrom());
		//délégation du service
		comp.appelPortIn(bind.getPortComposantFrom(), agrs);		
	}
	
	
	
	@Override
	public final void update(Observable o, Object arg)  {
		System.out.println("notify");
		if (arg instanceof SignalComposant){
			System.out.println(arg);
			SignalComposant recu=(SignalComposant) arg;
			Lien glien=roadMap.get(recu);
			if (glien !=null){
				System.out.println("truc" + glien);
				//le binding sert pour notify les observer de la configuration
				if (glien instanceof Binding){
					Binding temp= (Binding) glien;
					
					//récupération de la sortie du bind
					Integer portOut=this.getOutForIn(temp.getPortBindConfig());
					
					//on met dans notre port la valeur
					this.setValuePort(portOut, bibComposant.get(recu.getName()).appelPortOut(recu.getPort()));
					//on notifi que tout est dans notre port
					//que on a juste a getport tout ça
					this.setChanged();
					this.notifyObservers(new SignalComposant(getName(), portOut));
					
					
					//si attachement on va appeler la glue puis le port de l'autre méthode
				}else if (glien instanceof Attachement) {
					Attachement temp= (Attachement) glien;
					Object Value= bibComposant.get(recu.getName()).appelPortOut(recu.getPort());
					
					BriqueComposant cible= bibComposant.get(temp.getNameComposantTo());
					Connector conn= bibConnector.get(temp.getNameConnector());
					
					try {
						
						Method m= this.getMethodByName(conn, temp.getMethod());
						if( m!=null){
							//invocation du lien attachement en passant les argument passer a la glue
							
							Object [] tav= {m.invoke(conn, Value)};
							
							cible.appelPortIn(temp.getPortComposantTo(), tav );
						}else{
							throw new InvocationTargetException(new Throwable("Methode Inconnue"));
						}
						
						
					
						
					} catch (Exception e) {
						e.printStackTrace();
					} 
					
					
				}
			}
		}		
	}

	
	public final void  addLien(Lien li){
		
		//ajout d'un lien simple dans la road map
		this.roadMap.put(new SignalComposant(li.getNomComposantFrom(), li.getPortComposantFrom()),li);
		//ajout dans le maping binding si le lien est un binding
		if( li instanceof Binding ){
			Binding temp = (Binding) li;
			this.portServiceMapIng.put(((Binding) li).getPortBindConfig(), temp);
			
		}
		
		
	}
	
	public final void  addComposant(BriqueComposant brique){
		bibComposant.put(brique.getName(), brique);
		brique.addObserver(this);
	}
	
	public final void  addConnector(Connector con){
		bibConnector.put(con.getName(), con);
	}
	
	public final void removeLien(Lien li){
		//TODO faire des trucs voir si remove pas quad on remove un composant
	}
	public final void  removeComposant(String brique){
		bibComposant.remove(brique);
	}
	
	public final void  removeConnector(String con){
		bibConnector.remove(con);
	}

	
	
}
