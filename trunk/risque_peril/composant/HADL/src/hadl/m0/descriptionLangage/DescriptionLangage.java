package hadl.m0.descriptionLangage;

import hadl.lib.LogWriter;

import java.io.File;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class DescriptionLangage {
	
	private Document document;
	private LogWriter lw = LogWriter.getInstance();
	
	public DescriptionLangage(String filePath){
		
		//On créé le fichier de log
		lw.initUnique("descriptionLangage");
		
		try {
			// On stocke dans la classe
			SAXBuilder sxb = new SAXBuilder(true);
			this.document = sxb.build(new File(filePath));
			
			lw.write("Stockage du xml en mémoire");
			lw.writesep();
			
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
