package hadl.m2.test;

import java.util.Observable;
import java.util.Observer;

public class GestionConfig implements Observer{
	
	public GestionConfig() {

		TestEnvoiCoucou compo1 = new TestEnvoiCoucou();
		TestNom compo2 = new TestNom();
		TestConnect connect = new TestConnect();
		
		ConfigTest config = new ConfigTest();
		config.addObserver(this);
		
		config.addComposant(compo1);
		config.addComposant(compo2);
		config.addConnecteur(connect);
		
		config.addAttachement(compo1, 2, connect, 1);
		config.addAttachement(connect, 2, compo2, 1);
		
		config.addBinding(1, compo1, 1);
		config.addBinding(compo2, 2, 2);
		
		config.launch(1);
	}

	public void update(Observable o, Object arg) {
		Object[] args = (Object[]) arg;
		System.out.println(args[1]);
	}

	
}
