package hadl.m0.descriptionLangage;

import hadl.lib.LogWriter;
import hadl.m2.composant.Composant;
import hadl.m2.composantSimple.ComposantSimple;
import hadl.m2.configuration.Configuration;
import hadl.m2.connecteur.Connecteur;
import hadl.m2.connecteur.IConnecteur;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class DescriptionLangage {
	
	private Document document;
	private LogWriter lw = LogWriter.getInstance();
	
	private Element racine;
	
	public DescriptionLangage(String filePath){
		
		//On créé le fichier de log
		lw.initUnique("descriptionLangage");
		
		try {
			// On stocke dans la classe
			SAXBuilder sxb = new SAXBuilder(true);
			this.document = sxb.build(new File(filePath));
			
			racine = document.getRootElement();
			
			lw.write("Stockage du xml en mémoire");
			lw.writesep();
			lw.write("");
			
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Configuration analyse() {
		
		// On est à la racine. On est donc au niveau de la configuration englobante.
		Configuration configEnglobante = createConfiguration(racine);
		
		lw.close();
		
		return configEnglobante;
	}
	
	
	public Composant createComposant(Element composant) {
		
		try {
		
			String nomComposant = composant.getAttributeValue("nom");
			
			lw.writesep();
			
			lw.writejl("On créé le composant " + nomComposant);
			
			ComposantSimple composantObject = (ComposantSimple) Class.forName(composant.getAttributeValue("classPath")).newInstance();
			composantObject.setNom(nomComposant);
			
			lw.writejl("On récupère les contraintes et propriétés de " + nomComposant);
			
			composantObject.setContraintes(composant.getChild("contraintes").getText());
			composantObject.setProprietes(composant.getChild("proprietes").getText());
			
			//TODO Ajouter ici la méthode permettant de créer des ports tout seul
			
			lw.write("On associe les ports avec les services :");
			
			List<Element> listServices = composant.getChildren("service");
			
			for(Element service : listServices){
				
				String nomService = service.getAttributeValue("nom");
				
				List<Element> listPorts = service.getChildren();
				
				for(Element port : listPorts) {
					
					String nomPort = port.getAttributeValue("nom");
					
					if(port.getAttributeValue("type").equalsIgnoreCase("in")) {
						// port entrant associé au service
						lw.write("\t Port " + nomPort + " entrant associé au service " + nomService);
						
						composantObject.setPortIn(nomPort, nomService);
					} else {
						// port sortant associé au service
						lw.write("\t Port " + nomPort + " sortant associé au service " + nomService);
						
						composantObject.setPortOut(nomService, nomPort);
					}

				}
				
			}
			
			lw.writesep();
			lw.write("");
			
			return composantObject;			
		
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}	
	
	
	public Configuration createConfiguration(Element configuration) {
		
		try {
		
			String nomConfiguration = configuration.getAttributeValue("nom");
			
			lw.writesep();
			lw.writejl("On créé la configuration " + nomConfiguration);
	
			Configuration configurationObject = (Configuration) Class.forName(configuration.getAttributeValue("classPath")).newInstance();
			configurationObject.setNom(nomConfiguration);			
			
			lw.writejl("On récupère les contraintes et propriétés de " + nomConfiguration);
			
			configurationObject.setContraintes(configuration.getChild("contraintes").getText());
			configurationObject.setProprietes(configuration.getChild("proprietes").getText());
				
			// On cherche pour commencer à lister tous les composants présents dans la
			// configuration.
			
			Element composants = configuration.getChild("composants");
			List<Element> listComposants = composants.getChildren();
				
			lw.writejl("On créé les composants");
			
			for(Element composant : listComposants){
				
				if (composant.getName().equalsIgnoreCase("composant")){
					configurationObject.addComposant(createComposant(composant));
					
				} else if (composant.getName().equalsIgnoreCase("configuration")) {
					configurationObject.addComposant(createConfiguration(composant));
					
				} else {
					// On a trouvé un composant de type inconnu : il faut respecter la dtd dtdHadl
					lw.writeerr("Composant de type inconnu");
				}
				
			}
			
			Element connecteurs = configuration.getChild("connecteurs");
			List<Element> listConnecteurs = connecteurs.getChildren();
			
			lw.write("On créé les connecteurs : ");
			
			for(Element connecteur : listConnecteurs){
				
				// On créé le connecteur
				lw.write("\tConnecteur " + connecteur.getAttributeValue("nom") + " créé");
				
				Connecteur connecteurObject = (Connecteur) Class.forName(connecteur.getAttributeValue("classPath")).newInstance();
				connecteurObject.setNom(connecteur.getAttributeValue("nom"));
				
				List<Element> listGlues = connecteur.getChildren("glue");
				
				for(Element glue : listGlues){
					
					Element roleTo = glue.getChild("roleTo");
					
					List<Element> listRolesFrom = glue.getChildren("roleFrom");
					
					for(Element roleFrom : listRolesFrom){
						lw.write("\t\tRoleFrom : " + roleFrom.getAttributeValue("nom") + " ; Glue : " + glue.getAttributeValue("nom") + " ; RoleTo : " + roleTo.getAttributeValue("nom"));
						
						connecteurObject.setGlue(roleFrom.getAttributeValue("nom"), glue.getAttributeValue("nom"), roleTo.getAttributeValue("nom"));
					}
					
				}
				
				configurationObject.addConnecteur(connecteurObject);
						
			}
			
			lw.write("");
			
			
			Element attachements = configuration.getChild("attachements");
			List<Element> listAttachements = attachements.getChildren();
			
			lw.write("On créé les liens d'attachements : ");
			
			for(Element attachement : listAttachements){
				
				// On créé le lien d'attachement		
				if(attachement.getAttributeValue("compoToConnec").equalsIgnoreCase("oui")){
					// Attachement Composant -> Connecteur
					lw.write("\t" + "(Comp:port -> Conn:role) " + attachement.getChildText("fromComposant")+ ":" +  attachement.getChildText("fromPort") + "  ->  " + attachement.getChildText("toConnecteur") + ":" + attachement.getChildText("toRole"));			
					
					IConnecteur test = configurationObject.getConnecteur("rpc");
					
					configurationObject.addAttachement(configurationObject.getComposant(attachement.getChildText("fromComposant")), attachement.getChildText("fromPort"), configurationObject.getConnecteur(attachement.getChildText("toConnecteur")), attachement.getChildText("toRole"));					
				} else {
					// Attachement Connecteur -> Composant
					lw.write("\t" + "(Conn:role -> Comp:port) " + attachement.getChildText("toConnecteur") + ":" + attachement.getChildText("toRole") + "  ->  " + attachement.getChildText("fromComposant")+ ":" +  attachement.getChildText("fromPort"));			
					
					configurationObject.addAttachement(configurationObject.getConnecteur(attachement.getChildText("toConnecteur")), attachement.getChildText("toRole"), configurationObject.getComposant(attachement.getChildText("fromComposant")), attachement.getChildText("fromPort"));					
				}
				
			}
			
			lw.write("");
			
			
			Element bindings = configuration.getChild("bindings");
			List<Element> listBindings = bindings.getChildren();
			
			lw.write("On créé les liens binding :");

			for(Element binding : listBindings){
				//On créé un lien binding
				if(binding.getAttributeValue("in").equalsIgnoreCase("oui")){
					// On à affaire à un binding in
					lw.write("\t" + binding.getChildText("portConfig") + "  ->  " + binding.getChildText("composantToBind") + ":" + binding.getChildText("portComposant"));
					
					configurationObject.addBinding(binding.getChildText("portConfig"), configurationObject.getComposant(binding.getChildText("composantToBind")), binding.getChildText("portComposant"));					
				} else {
					// On à affaire à un binding out
					lw.write("\t" + binding.getChildText("composantToBind") + ":" + binding.getChildText("portComposant") + "  ->  " + binding.getChildText("portConfig"));
					
					configurationObject.addBinding(configurationObject.getComposant(binding.getChildText("composantToBind")), binding.getChildText("portComposant"), binding.getChildText("portConfig"));					
				}
				
			}
			
			lw.writesep();
			lw.write("");
			
			
			return configurationObject;			
		
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}		
	
	
	
	
	
	

}
