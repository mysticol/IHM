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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result
				+ ((nameComposantTo == null) ? 0 : nameComposantTo.hashCode());
		result = prime * result
				+ ((nameConnector == null) ? 0 : nameConnector.hashCode());
		result = prime * result + portComposantTo;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attachement other = (Attachement) obj;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (nameComposantTo == null) {
			if (other.nameComposantTo != null)
				return false;
		} else if (!nameComposantTo.equals(other.nameComposantTo))
			return false;
		if (nameConnector == null) {
			if (other.nameConnector != null)
				return false;
		} else if (!nameConnector.equals(other.nameConnector))
			return false;
		if (portComposantTo != other.portComposantTo)
			return false;
		return true;
	}
	


	
	
	
	
}
