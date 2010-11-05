package test;

import java.lang.reflect.InvocationTargetException;

import hadl.Composant;
import hadl.Configuration;
import hadl.Connector;
import hadl.com.Attachement;
import hadl.com.Binding;
import hadl.com.Lien;
import hadl.param.InOutMapping;
import hadl.param.MappingPortService;

public class MainTestM2 {

	
	public static void main(String[] args) {
		
		Configuration config= new ConfigurationTest();
		
		Composant composun= new ComposantTestUn();
		Composant composdeux= new ComposantTestDeux();
		
		Connector con= new Connecteur();
		
		Lien attach= new Attachement(2, "ComposantUn", "TestConnect", "rien", "ComposantDeux", 1);
		
		Lien bindIn= new Binding(1, "ComposantUn", 1);
		Lien bindOut= new Binding(1, "ComposantDeux", 1);
		
		InOutMapping inoutun= new InOutMapping(1, 2);
		
		MappingPortService composUnBind= new MappingPortService(1, "message");
		MappingPortService composDeuxBind= new MappingPortService(1, "chainage");
		
		composun.addMappingPortService(composUnBind);
		composun.addMapingInOut(inoutun);
		composdeux.addMappingPortService(composDeuxBind);
		
		
		config.addComposant(composun);
		config.addComposant(composdeux);
		config.addConnector(con);
		
		config.addLien(attach);
		config.addLien(bindOut);
		config.addLien(bindIn);
		
		
		Object [] tab = {"Ponay"};
		
		try {
			config.appelPortIn(1, tab);
			
			System.out.println(config.appelPortOut(1));
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
}