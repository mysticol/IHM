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




	
	
	
}
