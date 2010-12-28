package bean;

public class Case {

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
