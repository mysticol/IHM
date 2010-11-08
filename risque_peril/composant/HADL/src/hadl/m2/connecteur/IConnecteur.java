package hadl.m2.connecteur;

import java.util.Observable;

public abstract class IConnecteur extends Observable{
	
	public abstract void glue(Integer port, Object data);
	
}
