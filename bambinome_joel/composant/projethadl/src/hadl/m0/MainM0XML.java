package hadl.m0;

import hadl.m2.Configuration;
import hadl.m2.parseur.Parseur;

import java.io.File;

public class MainM0XML {

	public static void main(String[] args) throws Exception{
		
		
		Parseur parse= new Parseur();
		
		Configuration config=parse.parse(new File("src/hadl/m0/m0clientserveur.xml"));
		
		System.out.println(config);
	
		
			Object[] tab={"Bambinome"};
		
			config.appelPortIn(1, tab);
		
		System.out.println(config.appelPortOut(1));
		
	}
	
}
