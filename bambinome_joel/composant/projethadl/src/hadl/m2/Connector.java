package hadl.m2;



public abstract class Connector extends ObjectHadl {

	
	private static final long serialVersionUID = -8833020644466656361L;
	
		
	public Connector(String name) {
		super(name);
		
	}



	public Object[] glueFromTo(Object[] arges){
		return arges;
	}
	
	public Object[] glueToFrom(Object[] arges){
		return arges;
	}
	
}
