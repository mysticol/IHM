package hadl.com;


public class Attachement extends Lien{

	private String nameConnector;
	private String method;
	
	private String nameComposantTo;
	private int portComposantTo;
	
	
	
	
	public String getNameConnector() {
		return nameConnector;
	}
	public void setNameConnector(String name) {
		this.nameConnector = name;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}

	public void setNameComposantTo(String nameComposant) {
		this.nameComposantTo = nameComposant;
	}
	public String getNameComposantTo() {
		return nameComposantTo;
	}
	public void setPortComposantTo(int portComposant) {
		this.portComposantTo = portComposant;
	}
	public int getPortComposantTo() {
		return portComposantTo;
	}

	public Attachement(int portComposantFrom, String nomComposantFrom,
			String nameConnector, String method, String nameComposantTo,
			int portComposantTo) {
		super(portComposantFrom, nomComposantFrom);
		this.nameConnector = nameConnector;
		this.method = method;
		this.nameComposantTo = nameComposantTo;
		this.portComposantTo = portComposantTo;
	}
	@Override
	public String toString() {
		return "Attachement [nameConnector=" + nameConnector + ", method="
				+ method + ", nameComposantTo=" + nameComposantTo
				+ ", portComposantTo=" + portComposantTo
				+ ", getPortComposantFrom()=" + getPortComposantFrom()
				+ ", getNomComposantFrom()=" + getNomComposantFrom()
				+ ", getClass()=" + getClass() + "]";
	}
	


	
	
	
	
}
