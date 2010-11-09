package hadl.parseur;

import hadl.Composant;
import hadl.Configuration;
import hadl.Connector;
import hadl.com.Attachement;
import hadl.com.Binding;
import hadl.com.BindingType;
import hadl.com.param.InOutMapping;
import hadl.com.param.MappingPortService;

import java.io.File;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class Parseur {

	

	public Configuration parse(File file) throws Exception {
		Document document;
		Element racine;

		SAXBuilder sxb = new SAXBuilder();

		document = sxb.build(file);
		racine = document.getRootElement();

		return this.parseConfiguration(racine);
	}

	private Configuration parseConfiguration(Element config) {
		Configuration result = null;
		IdXML id = this.parseId(config.getChild("Id"));

		try {
			Class<?> configClass = Class.forName(id.getNomClasse());
			result = (Configuration) configClass.newInstance();

			List<?> listeConfiguration = config.getChildren("Configuration");
			for (Object el : listeConfiguration) {
				result.addComposant(this.parseConfiguration((Element) el));
			}

			List<?> listComposament = config.getChildren("Composant");
			for (Object el : listComposament) {
				result.addComposant(this.parseComposant((Element) el));
			}

			List<?> listConnector = config.getChildren("Connecteur");
			for (Object el : listConnector) {
				
				result.addConnector(this.parseConnecteur((Element) el));
			}

			List<?> listAttachement = config.getChildren("Attachement");
			for (Object el : listAttachement) {
				result.addLien(this.parseAttachement((Element) el));
			}

			List<?> listBinding = config.getChildren("Binding");
			for (Object el : listBinding) {
				result.addLien(this.parseBinding((Element) el));
			}

			List<?> listMappingInOut = config.getChildren("MappingInOut");
			for (Object el : listMappingInOut) {
				result.addMapingInOut(this.parseInOutMapping((Element) el));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	private Composant parseComposant(Element Compo) {
		Composant comp = null;
		IdXML id = this.parseId(Compo.getChild("Id"));

		Class<?> configClass;
		try {
			configClass = Class.forName(id.getNomClasse());
			comp = (Composant) configClass.newInstance();

			List<?> listMappingServicePort = Compo
					.getChildren("MappingServicePort");
			for (Object el : listMappingServicePort) {
				comp.addMappingPortService(this
						.parseMappingPortService((Element) el));
			}

			List<?> listMappingInOut = Compo.getChildren("MappingInOut");
			for (Object el : listMappingInOut) {
				comp.addMapingInOut(this.parseInOutMapping((Element) el));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return comp;
	}

	private Connector parseConnecteur(Element connel) {
		Connector con = null;
		IdXML id = this.parseId(connel.getChild("Id"));

		Class<?> configClass;
		try {
			configClass = Class.forName(id.getNomClasse());
			con = (Connector) configClass.newInstance();
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return con;
	}

	private Attachement parseAttachement(Element attach) {

		return new Attachement(
				Integer.parseInt(attach.getChildText("portComposantFrom")),
				attach.getChildText("nomComposantFrom"),
				attach.getChildText("nameConnector"),
				attach.getChildText("method"), 
				Integer.parseInt(attach.getChildText("portComposantTo")),
				attach.getChildText("nameComposantTo"));
	}

	private Binding parseBinding(Element bind) {
		String type=bind.getChildText("type");
		BindingType biType=BindingType.BOTH;
		if (type!=null){
			biType= BindingType.valueOf(type);
			
		}
		return new Binding(Integer.parseInt(bind
				.getChildText("portComposantFrom")),
				bind.getChildText("nomComposantFrom"), Integer.parseInt(bind
						.getChildText("portBindConfig")), biType);
	}

	private MappingPortService parseMappingPortService(Element el) {
		return new MappingPortService(
				Integer.parseInt(el.getChildText("port")),
				el.getChildText("service"));
	}

	private InOutMapping parseInOutMapping(Element el) {
		return new InOutMapping(Integer.parseInt(el.getChildText("in")),
				Integer.parseInt(el.getChildText("out")));
	}

	private IdXML parseId(Element id) {
		return new IdXML(id.getChildText("Nom"), id.getChildText("Class"));

	}

}
