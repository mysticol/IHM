package hadl.com;

public class Binding extends Lien {

	public Binding(int portComposantFrom, String nomComposantFrom,
			int portBindConfig) {
		super(portComposantFrom, nomComposantFrom);
		this.portBindConfig = portBindConfig;
	}

	private int portBindConfig;
	
	
	
	

	public int getPortBindConfig() {
		return portBindConfig;
	}

	public void setPortBindConfig(int portBindConfig) {
		this.portBindConfig = portBindConfig;
	}

	@Override
	public String toString() {
		return "Binding [portBindConfig=" + portBindConfig
				+ ", getPortComposantFrom()=" + getPortComposantFrom()
				+ ", getNomComposantFrom()=" + getNomComposantFrom()
				+ ", getClass()=" + getClass() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + portBindConfig;
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
		Binding other = (Binding) obj;
		if (portBindConfig != other.portBindConfig)
			return false;
		return true;
	}




	
	
	
}
