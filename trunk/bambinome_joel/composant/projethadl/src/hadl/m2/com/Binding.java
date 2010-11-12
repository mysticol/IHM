package hadl.m2.com;

public class Binding extends Lien {

	public Binding(int portComposantFrom, String nomComposantFrom,
			int portBindConfig) {
		super(portComposantFrom, nomComposantFrom);
		this.portBindConfig = portBindConfig;
		this.type= BindingType.BOTH;
	}

	private int portBindConfig;
	private BindingType type;
	
	
	

	public Binding(int portComposantFrom, String nomComposantFrom,
			int portBindConfig, BindingType type) {
		super(portComposantFrom, nomComposantFrom);
		this.portBindConfig = portBindConfig;
		this.type = type;
	}

	public int getPortBindConfig() {
		return portBindConfig;
	}

	public void setPortBindConfig(int portBindConfig) {
		this.portBindConfig = portBindConfig;
	}



	public void setType(BindingType type) {
		this.type = type;
	}

	public BindingType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Binding [portBindConfig=" + portBindConfig + ", type=" + type
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + portBindConfig;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (type != other.type)
			return false;
		return true;
	}




	
	
	
}
