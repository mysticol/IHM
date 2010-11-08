package hadl.m0.descriptionLangage;

import java.io.File;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class DescriptionLangage {
	
	private Document document;
	
	public DescriptionLangage(String filePath){
		
		try {
			
			// On stocke dans la classe
			SAXBuilder sxb = new SAXBuilder(true);
			this.document = sxb.build(new File(filePath));
			
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
