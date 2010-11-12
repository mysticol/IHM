package hadl.m2.testm2;



import hadl.m2.Composant;
import hadl.m2.Configuration;
import hadl.m2.Connector;
import hadl.m2.com.Attachement;
import hadl.m2.com.Binding;
import hadl.m2.com.BindingType;
import hadl.m2.com.Lien;
import hadl.m2.com.param.InOutMapping;
import hadl.m2.com.param.MappingPortService;

public class MainTestM2 {

	
	public static void main(String[] args) {
		
		Configuration config= new ConfigurationTest();
		
		Composant composun= new ComposantTestUn();
		Composant composdeux= new ComposantTestDeux();
		
		Connector con= new ConnecteurTest();
		
		Lien attach= new Attachement(2, "ComposantUn", "TestConnect", "rien",  1, "ComposantDeux");
		
		Lien bindIn= new Binding(1, "ComposantUn", 1);
		Lien bindOut= new Binding(1, "ComposantDeux", 1, BindingType.OUT);
		
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
			config.removeComposant("ComposantUn");
			
			config.appelPortIn(1, tab);
			
			config.addComposant(composun);
			
			System.out.println(config.appelPortOut(1));
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
}
