package hadl.m2.composant;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class Composant extends IComposant {
	
	// attribut
	protected String contraintes;
	protected String proprietes;

	protected Map<String,String> portsIn;
	protected Map<String,String> portsOut;
	
	/* constructeur */
	public Composant(String contraintes, String proprietes,
			Map<String, String> portsIn, Map<String,String> portsOut) {
		super();
		this.contraintes = contraintes;
		this.proprietes = proprietes;
		this.portsIn = portsIn;
		this.portsOut = portsOut;
	}
	
	public Composant() {
		this.portsIn = new HashMap<String, String>();
		this.portsOut = new HashMap<String, String>();
	}
	// -------------------------------
	
	// getters and setters
	public String getContraintes() {
		return contraintes;
	}
	public void setContraintes(String contraintes) {
		this.contraintes = contraintes;
	}
	public String getProprietes() {
		return proprietes;
	}
	public void setProprietes(String proprietes) {
		this.proprietes = proprietes;
	}

	public void setPortIn(String port , String method){
		this.portsIn.put(port, method);
	}
	
	public void setPortOut(String method , String port){
		this.portsOut.put(method, port);
	}
	
	// method d'affichage d'un connecteur
	public void print(){
		System.out.println(this.getClass().getName());
		System.out.println("Contraintes : " + contraintes );
		System.out.println("Proprietes : " + proprietes );
		System.out.println("Port d'entr�es :");
		for(String p : this.portsIn.keySet()){
			System.out.println("     - " + p + " -> " + this.portsIn.get(p));
		}
		System.out.println("Port de sorties :");
		for(String m : this.portsOut.keySet()){
			System.out.println("     - " + m + " -> " + this.portsOut.get(m));
		}
	}
	
	// m�thode d'activation d'un port en sortie
	public void notifier(String methodeName , Object data){
		if(this.portsOut.containsKey(methodeName)){
			Object[] datas = new Object[2];
			datas[0] = this.portsOut.get(methodeName);
			datas[1] = data;
			this.setChanged();
			this.notifyObservers(datas);
		}else{
			System.out.println("!! service non lier a un port !!");
		}
	}
	
	// method appel�e depuis l'exterieur pour lancer une m�thode li�e � un port
	public void launch(String port, Object data){
		if(this.portsIn.containsKey(port)){
			try {
				this.getClass().getDeclaredMethod(this.portsIn.get(port), data.getClass()).invoke(this, data);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("!! Port non lier a un service !!");
		}
	}
	
	public void launch(String port){
		if(this.portsIn.containsKey(port)){
			try {
				this.getClass().getDeclaredMethod(this.portsIn.get(port)).invoke(this);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println(" !! Port non lier a un service !! ");
		}

	}
}
