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
		
	      //On cr�e une instance de SAXBuilder
		  SAXBuilder sxb = new SAXBuilder(true);
		  try
		      {
	         //On cr�e un nouveau document JDOM avec en argument le fichier XML
	         //Le parsing est termin� ;)			 
	         document = sxb.build(new File("src/hadl/m0/descriptionLangage/exempleHadl.xml"));
	      }
	      catch(Exception e){}

	      //On initialise un nouvel �l�ment racine avec l'�l�ment racine du document.
	      racine = document.getRootElement();

	      //M�thode d�finie dans la partie 3.2. de cet article
	      afficheALL();

	}
	
	//Ajouter cette m�thodes � la classe JDOM2
	public void afficheALL()
	{
	   //On cr�e une List contenant tous les noeuds "etudiant" de l'Element racine
	   Element composants = racine.getChild("composants");

	   List listComposants = composants.getChildren();
	   
	   //On cr�e un Iterator sur notre liste
	   Iterator i = listComposants.iterator();
	   while(i.hasNext())
	   {
	      //On recr�e l'Element courant � chaque tour de boucle afin de
	      //pouvoir utiliser les m�thodes propres aux Element comme :
	      //s�lectionner un noeud fils, modifier du texte, etc...
	      Element courant = (Element)i.next();
	      //On affiche le nom de l'element courant
	      System.out.println(courant.getAttributeValue("nom"));
	   }
	}

}
