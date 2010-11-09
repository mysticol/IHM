package hadl.m2.composant;

import java.util.Observable;

public abstract class IComposant extends Observable {

	public abstract void launch(String port, Object data);
	public abstract void launch(String port);
	public abstract void print();
	public abstract String getNom();
}
