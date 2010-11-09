package hadl.m2.connecteur;

import java.util.Observable;

public abstract class IConnecteur extends Observable{
	
	public abstract void glue(String rolesFrom, Object data);
	public abstract void print();
	
}
