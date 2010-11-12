package hadl.m2.com.event;

import hadl.m2.com.Binding;

import java.util.Arrays;

public class EventBinding implements EventComposant{

	private Binding bind;
	private Object[] values;
private int port;
	
	public Binding getBind() {
		return bind;
	}

	public void setBind(Binding bind) {
		this.bind = bind;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	public EventBinding( Object[] values, Binding bind, Integer i) {
		this.bind = bind;
		this.values = values;
		port=i;
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
		result = prime * result + ((bind == null) ? 0 : bind.hashCode());
		result = prime * result + port;
		result = prime * result + Arrays.hashCode(values);
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
		EventBinding other = (EventBinding) obj;
		if (bind == null) {
			if (other.bind != null)
				return false;
		} else if (!bind.equals(other.bind))
			return false;
		if (port != other.port)
			return false;
		if (!Arrays.equals(values, other.values))
			return false;
		return true;
	}



	
}
