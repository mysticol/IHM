package hadl.com;

import java.io.Serializable;

public class SignalComposant implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1476259902237096043L;
	private String name;
	private int port;
	
	public SignalComposant(String name, int port) {
		this.name = name;
		this.port = port;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + port;
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
		SignalComposant other = (SignalComposant) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (port != other.port)
			return false;
		return true;
	}

	
	
	@Override
	public String toString() {
		return "Signal Composant: "+name+" "+port;
	}
	
	
	
	
	
}
