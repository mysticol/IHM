package hadl.testxml;

import java.io.File;

import hadl.Configuration;
import hadl.parseur.Parseur;

public class Maintestxml {

	
	
	public static void main(String[] args) throws Exception {
		
		Parseur parse= new Parseur();
		
		Configuration config=parse.parse(new File("src/hadl/testxml/testlangage.xml"));
		
		
		Object [] tab = {"Ponay"};
		
	
			
			
			config.appelPortIn(1, tab);
			
			
			
			System.out.println(config.appelPortOut(1));
			
	
		
		
		
	}
}
