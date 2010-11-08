package hadl.com.event;

import hadl.com.Attachement;

public class EventAttachement implements EventComposant{

	private Attachement attach;
	private SignalComposant signal;
	private Object value;
	public EventAttachement(Object value,Attachement attach, SignalComposant signal
			) {
		this.attach = attach;
		this.signal = signal;
		this.value = value;
	}
	public Attachement getAttach() {
		return attach;
	}
	public void setAttach(Attachement attach) {
		this.attach = attach;
	}
	public SignalComposant getSignal() {
		return signal;
	}
	public void setSignal(SignalComposant signal) {
		this.signal = signal;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attach == null) ? 0 : attach.hashCode());
		result = prime * result + ((signal == null) ? 0 : signal.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		EventAttachement other = (EventAttachement) obj;
		if (attach == null) {
			if (other.attach != null)
				return false;
		} else if (!attach.equals(other.attach))
			return false;
		if (signal == null) {
			if (other.signal != null)
				return false;
		} else if (!signal.equals(other.signal))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
	
	
	
}