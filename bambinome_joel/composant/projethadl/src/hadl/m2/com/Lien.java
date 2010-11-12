package hadl.m2.com;

public abstract class Lien {

	private int portComposantFrom;
	private String nomComposantFrom;
	public int getPortComposantFrom() {
		return portComposantFrom;
	}
	public void setPortComposantFrom(int portComposantFrom) {
		this.portComposantFrom = portComposantFrom;
	}
	public String getNomComposantFrom() {
		return nomComposantFrom;
	}
	public void setNomComposantFrom(String nomComposantFrom) {
		this.nomComposantFrom = nomComposantFrom;
	}
	public Lien(int portComposantFrom, String nomComposantFrom) {
		super();
		this.portComposantFrom = portComposantFrom;
		this.nomComposantFrom = nomComposantFrom;
	}
	@Override
	public String toString() {
		return "Lien [portComposantFrom=" + portComposantFrom
				+ ", nomComposantFrom=" + nomComposantFrom + ", getClass()="
				+ getClass() + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((nomComposantFrom == null) ? 0 : nomComposantFrom.hashCode());
		result = prime * result + portComposantFrom;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lien other = (Lien) obj;
		if (nomComposantFrom == null) {
			if (other.nomComposantFrom != null)
				return false;
		} else if (!nomComposantFrom.equals(other.nomComposantFrom))
			return false;
		if (portComposantFrom != other.portComposantFrom)
			return false;
		return true;
	}
	
	
	
}
