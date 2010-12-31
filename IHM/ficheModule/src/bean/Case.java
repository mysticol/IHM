package bean;

import java.io.Serializable;

public class Case implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 307714418934627886L;
	
	private String label;
	private boolean coche;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isCoche() {
		return coche;
	}
	public void setCoche(boolean coche) {
		this.coche = coche;
	}
	public Case(String label, boolean coche) {
		this.label = label;
		this.coche = coche;
	}
	public Case() {

	}
	
	
	
}
