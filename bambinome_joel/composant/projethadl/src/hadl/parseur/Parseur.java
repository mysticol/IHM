package hadl.parseur;

import hadl.Composant;
import hadl.Configuration;
import hadl.Connector;
import hadl.com.Attachement;
import hadl.com.Binding;

import java.io.File;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;



public class Parseur {

	/**
	 * TODO : parseur pour le langage qui renvoie une collection de config ou une config.
	 * 
	 */
	
	
	public void parse(File file) throws Exception{
		Document document ;
		Element racine;
		
		SAXBuilder sxb = new SAXBuilder();

		document = sxb.build(file);
		racine=document.getRootElement();
		
		
		
		

		
		
		
	}
	
	
	private Configuration parseConfig(Element config){
		Configuration result= null;
		
		
		
		
		
		return result;
	}
	
	
	
	
	private Composant parseComposant(Element Compo){
		Composant comp= null;
		return comp;
	}
	       
	private Connector parseConnector(Element attach){
		
		return null;
	}
	
	
	
	private Attachement parseAttachement(Element attach){
		
		return null;
	}
	
	
	private Binding parseBinding(Element bind){
		
		return null;
	}
	
	
	
	
	
	
}
