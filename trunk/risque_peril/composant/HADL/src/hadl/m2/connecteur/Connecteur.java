package hadl.m2.connecteur;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class Connecteur extends IConnecteur {

	private Map<Integer,String> rolesFrom;
	
	public Connecteur(Map<Integer, String> rolesFrom) {
		super();
		this.rolesFrom = rolesFrom;
	}

	public Connecteur() {
		super();
		this.rolesFrom = new HashMap<Integer, String>();
	}

	public void setGlue(Integer port, String glue){
		this.rolesFrom.put(port, glue);
	}
	
	
	public void glue(Integer port, Object data) {

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

	}
	
	public void glue(Integer port){
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
	}
}
