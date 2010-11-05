package hadl;

import hadl.param.MappingPortService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;




public  class Composant extends BriqueComposant  {
	
	

	private static final long serialVersionUID = 1L;
	private HashMap<Integer, String> portServiceMapIng;

	public Composant(String name) {
		super(name);
		this.portServiceMapIng= new HashMap<Integer, String>();
	}

	
	public final void  appelPortIn(Integer i, Object[] agrs) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Integer portOut=getOutForIn(i);
		
		Method m= this.getMethodByName(this, portServiceMapIng.get(i));
		if( m!=null){
			this.setValuePort(portOut, m.invoke(this, agrs));
			this.setChanged();
			this.notifyObservers(new SignalComposant(getName(), portOut));
		}else{
			throw new InvocationTargetException(new Throwable("Methode Inconnue"));
		}
	}
	
	
	public final void addMappingPortService( MappingPortService mapping){
		portServiceMapIng.put(mapping.getPort(), mapping.getService());
	}
	
	public final void removeMappingPortService( int port){
		portServiceMapIng.remove(port);
	}
}
