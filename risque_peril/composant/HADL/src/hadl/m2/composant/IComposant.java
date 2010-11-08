package hadl.m2.composant;

import java.util.Observable;

public abstract class IComposant extends Observable {

	public abstract void launch(Integer port, Object data);
	public abstract void launch(Integer port);
	public abstract void print();
}
