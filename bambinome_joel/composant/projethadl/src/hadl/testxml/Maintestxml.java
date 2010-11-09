package hadl.testxml;

import java.io.File;

import hadl.parseur.Parseur;

public class Maintestxml {

	
	
	public static void main(String[] args) throws Exception {
		
		Parseur parse= new Parseur();
		
		parse.parse(new File("src/hadl/testxml/testlangage.xml"));
		
		
	}
}
