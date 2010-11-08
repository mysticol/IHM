package hadl.m2.composant;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class Composant extends IComposant {
		
	protected String contraintes;
	protected String proprietes;

	protected Map<Integer,String> ports;	
	
	/* constructeur */
	public Composant(String contraintes, String proprietes,
			Map<Integer, String> ports) {
		super();
		this.contraintes = contraintes;
		this.proprietes = proprietes;
		this.ports = ports;
	}
	
	public Composant() {
		this.ports = new HashMap<Integer, String>();
	}

	// getters ans setters
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

	protected void setPort(Integer port , String method){
		this.ports.put(port, method);
	}
	
	// method appelée depuis l'exterieur pour lancer une méthode liée à un port
	public void launch(Integer port, Object data){
		try {
			this.getClass().getDeclaredMethod(this.ports.get(port), data.getClass()).invoke(this, data);
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
	}
	
	public void launch(Integer port){
		try {
			this.getClass().getDeclaredMethod(this.ports.get(port)).invoke(this);
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
	}
	
	
}
