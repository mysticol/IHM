package hadl.m2.connecteur;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class Connecteur extends IConnecteur {

	protected Map<Integer,String> rolesFrom;
	protected Map<String,Integer> rolesTo;
	
	public Connecteur(Map<Integer, String> rolesFrom,Map<String,Integer> rolesTo) {
		super();
		this.rolesFrom = rolesFrom;
		this.rolesTo = rolesTo;
	}

	public Connecteur() {
		super();
		this.rolesFrom = new HashMap<Integer, String>();
		this.rolesTo = new HashMap<String, Integer>();
	}

	public void setGlue(Integer roleFrom, String glue, Integer roleTo){
		this.rolesFrom.put(roleFrom, glue);
		this.rolesTo.put(glue, roleTo);
	}
	
	public void print(){
		System.out.println(this.getClass().getName());
		System.out.println("Roles from :");
		for(Integer p : this.rolesFrom.keySet()){
			System.out.println("     - " + p + " -> " + this.rolesFrom.get(p));
		}
		System.out.println("Roles to :");
		for(String m : this.rolesTo.keySet()){
			System.out.println("     - " + m + " -> " + this.rolesTo.get(m));
		}
	}
	
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
	
	public void glue(Integer port, Object data) {
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
	
	public void glue(Integer port){
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
