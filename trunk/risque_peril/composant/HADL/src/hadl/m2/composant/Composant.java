package hadl.m2.composant;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class Composant extends IComposant {
		
	protected String contraintes;
	protected String proprietes;

	protected Map<Integer,String> portsIn;
	protected Map<String,Integer> portsOut;
	
	/* constructeur */
	public Composant(String contraintes, String proprietes,
			Map<Integer, String> portsIn, Map<String,Integer> portsOut) {
		super();
		this.contraintes = contraintes;
		this.proprietes = proprietes;
		this.portsIn = portsIn;
		this.portsOut = portsOut;
	}
	
	public Composant() {
		this.portsIn = new HashMap<Integer, String>();
		this.portsOut = new HashMap<String, Integer>();
	}

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

	protected void setPortIn(Integer port , String method){
		this.portsIn.put(port, method);
	}
	
	protected void setPortOut(String method , Integer port){
		this.portsOut.put(method, port);
	}
	
	public void print(){
		System.out.println(this.getClass().getName());
		System.out.println("Contraintes : " + contraintes );
		System.out.println("Proprietes : " + proprietes );
		System.out.println("Port d'entrées :");
		for(Integer p : this.portsIn.keySet()){
			System.out.println("     - " + p + " -> " + this.portsIn.get(p));
		}
		System.out.println("Port de sorties :");
		for(String m : this.portsOut.keySet()){
			System.out.println("     - " + m + " -> " + this.portsOut.get(m));
		}
	}
	
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
	
	// method appelée depuis l'exterieur pour lancer une méthode liée à un port
	public void launch(Integer port, Object data){
		if(this.portsIn.containsKey(port)){
			try {
				this.getClass().getDeclaredMethod(this.portsIn.get(port), data.getClass()).invoke(this, data);
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
			System.out.println("!! Port non lier a un service !!");
		}
	}
	
	public void launch(Integer port){
		if(this.portsIn.containsKey(port)){
			try {
				this.getClass().getDeclaredMethod(this.portsIn.get(port)).invoke(this);
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
			System.out.println(" !! Port non lier a un service !! ");
		}

	}
}
