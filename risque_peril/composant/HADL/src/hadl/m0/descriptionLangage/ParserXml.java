package hadl.m0.descriptionLangage;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class ParserXml {
	
		Document document;
		static Element racine;
	
	public ParserXml() {
		
	      //On crée une instance de SAXBuilder
		  SAXBuilder sxb = new SAXBuilder(true);
		  try
		      {
	         //On crée un nouveau document JDOM avec en argument le fichier XML
	         //Le parsing est terminé ;)			 
	         document = sxb.build(new File("src/hadl/m0/descriptionLangage/exempleHadl.xml"));
	      }
	      catch(Exception e){}

	      //On initialise un nouvel élément racine avec l'élément racine du document.
	      racine = document.getRootElement();

	      //Méthode définie dans la partie 3.2. de cet article
	      afficheALL();

	}
	
	//Ajouter cette méthodes à la classe JDOM2
	public void afficheALL()
	{
	   //On crée une List contenant tous les noeuds "etudiant" de l'Element racine
	   Element composants = racine.getChild("composants");

	   List listComposants = composants.getChildren();
	   
	   //On crée un Iterator sur notre liste
	   Iterator i = listComposants.iterator();
	   while(i.hasNext())
	   {
	      //On recrée l'Element courant à chaque tour de boucle afin de
	      //pouvoir utiliser les méthodes propres aux Element comme :
	      //sélectionner un noeud fils, modifier du texte, etc...
	      Element courant = (Element)i.next();
	      //On affiche le nom de l'element courant
	      System.out.println(courant.getAttributeValue("nom"));
	   }
	}

}
