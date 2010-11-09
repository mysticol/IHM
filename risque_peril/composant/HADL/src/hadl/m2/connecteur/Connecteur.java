package hadl.m2.connecteur;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class Connecteur extends IConnecteur {

	// attribut  
	protected String nom;
	protected Map<String,String> rolesFrom;
	protected Map<String,String> rolesTo;
	
	// constructor
	public Connecteur(Map<String, String> rolesFrom,Map<String,String> rolesTo, String nom) {
		super();
		this.nom = nom ;
		this.rolesFrom = rolesFrom;
		this.rolesTo = rolesTo;
	}

	public Connecteur() {
		super();
		this.nom = this.getClass().getName();;
		this.rolesFrom = new HashMap<String, String>();
		this.rolesTo = new HashMap<String, String>();
	}
	// ---------------------------------------

	// methode de mise a jour des liaison entre les glues et les roles
	public void setGlue(String roleFrom, String glue, String roleTo){
		this.rolesFrom.put(roleFrom, glue);
		this.rolesTo.put(glue, roleTo);
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	// method d'affichage d'un connecteur
	public void print(){
		System.out.println(this.getClass().getName());
		System.out.println("Roles from :");
		for(String p : this.rolesFrom.keySet()){
			System.out.println("     - " + p + " -> " + this.rolesFrom.get(p));
		}
		System.out.println("Roles to :");
		for(String m : this.rolesTo.keySet()){
			System.out.println("     - " + m + " -> " + this.rolesTo.get(m));
		}
	}
	
	// méthode d'activation d'un port en sortie
	public void notifier(String methode , Object data){
		if(this.rolesTo.containsKey(methode)){
			Object[] datas = new Object[2];
			datas[0] = this.rolesTo.get(methode);
			datas[1] = data;
			this.setChanged();
			this.notifyObservers(datas);
		}else{
			System.out.println("!! service non lier a un port !!");
		}
	}
	
	// methode a appelé pour lancer les glues, activation d'un port d'entré
	public void glue(String port, Object data) {
		if(this.rolesFrom.containsKey(port)){
			try {
				this.getClass().getDeclaredMethod(this.rolesFrom.get(port), data.getClass()).invoke(this, data);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println(" !! role non lier a une glue !! ");
		}
	}
	
	public void glue(String port){
		if(this.rolesFrom.containsKey(port)){
			try {
				this.getClass().getDeclaredMethod(this.rolesFrom.get(port)).invoke(this);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println(" !! role non lier a une glue !! ");
		}
	}
}
