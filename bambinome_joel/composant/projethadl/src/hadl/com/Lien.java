package hadl.com;

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
	
	
	
}
