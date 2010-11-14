package hadl.m0.descriptionLangage;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import hadl.m2.composant.Composant;
import hadl.m2.composant.IComposant;
import hadl.m2.configuration.Configuration;
import hadl.m2.configuration.IConfiguration;
import hadl.m2.connecteur.Connecteur;
import hadl.m2.connecteur.IConnecteur;

public class MainDescriptionLangage {

	static IConfiguration root;
	static IConfiguration cour;
	static List<IConfiguration> way = new LinkedList<IConfiguration>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DescriptionLangage test = new DescriptionLangage("src/hadl/m0/descriptionLangage/exempleCS.xml");
		root = test.analyse();
		way.add(root);
		start();
	}
	
	public static void start(){		
		String choix = "";
		Boolean fin = false;
		BufferedReader waiter = new BufferedReader(new InputStreamReader(System.in));
				
		cour = root;
		
		do{
			System.out.println(" --------------------------------------------------------");
			System.out.println(" Configuration courante : " + cour.getNom() );
			System.out.println(" ------------------------ MENU --------------------------");
			System.out.println(" 1 - activer l'un des ports de la configuration racine");
			System.out.println(" 2 - afficher le contenu de la configuraton courante");
			System.out.println(" 3 - prendre comme configuration courante l'un des fils de la configuration courante actuelle");
			System.out.println(" 4 - prendre comme configuration courante le pere de la configuration courante actuelle");
			System.out.println(" --------------------------------------------------------");
			System.out.println(" 5 - retirer un composant a la configuration courante");
			System.out.println(" 6 - ajouter un composant a la configuration courante");
			System.out.println(" --------------------------------------------------------");
			System.out.println(" 7 - retirer un connecteur a la configuration courante");
			System.out.println(" 8 - ajouter un connecteur a la configuration courante");
			System.out.println(" --------------------------------------------------------");
			System.out.println(" 9 - retirer un lien a la configuration courante");
			System.out.println("10 - ajouter un lien a la configuration courante");
			System.out.print(" --> choix : ");
			
			try {
				choix = waiter.readLine();
			
				switch(Integer.valueOf(choix)){
				case 1 :for(String p : cour.getListPort()){
							System.out.println("  " + p);
						}
						System.out.print("  --> port a activer : ");
						choix = waiter.readLine();
						root.launch(choix);
						break;
						
				case 2 :System.out.println(" --------------------------------------------------------" );
						cour.print();
						System.out.println(" --------------------------------------------------------" );
						break;
						
				case 3 :System.out.println(" --------------------------------------------------------" );
						int i=0;
						for(IComposant c : cour.getListComposant()){
							if(c instanceof Configuration){
								i++;
								System.out.println("  "+ c.getNom());
							}
						}
						if(i ==0 ){
							System.out.println(" ----> la configuration courante actuelle ne possede pas de configuration interne");
						}else{
							System.out.print(" ----> quel configuration : ");
							choix = waiter.readLine();
							way.add(cour);
							cour = (IConfiguration) cour.getComposant(choix);
						}
						break;		
				case 4 :System.out.println(" --------------------------------------------------------" );
						if(cour.equals(root)){
							System.out.println(" ----> la configuration courante actuelle est la configuration racine");
						}else{
							cour = way.remove(way.size()-1);
						}
						break;
				case 5 :System.out.println(" --------------------------------------------------------" );
						for(IComposant c : cour.getListComposant()){
							System.out.println("  "+ c.getNom());
						}
						System.out.print(" ----> quel composant : ");
						choix = waiter.readLine();
						cour.removeComposant(cour.getComposant(choix));
						break;
				
				case 6 :System.out.println(" --------------------------------------------------------" );
						System.out.print(" ----> composant a ajouter : ");
						choix = waiter.readLine();
						IComposant newCompo;
						try {
							newCompo = (Composant) Class.forName(choix).newInstance();
							cour.addComposant(newCompo);
						} catch (InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						break;
					
				case 7 :System.out.println(" --------------------------------------------------------" );
						for(IConnecteur c : cour.getListConnecteur()){
							System.out.println("  "+ c.getNom());
						}
						System.out.print(" ----> quel connecteur : ");
						choix = waiter.readLine();
						cour.removeConnecteur(cour.getConnecteur(choix));
						break;
						
				case 8 :System.out.println(" --------------------------------------------------------" );
						System.out.print(" ----> composant a ajouter : ");
						choix = waiter.readLine();
						IConnecteur newConnect;
						try {
							newConnect = (Connecteur) Class.forName(choix).newInstance();
							cour.addConnecteur(newConnect);
						} catch (InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						break;
						
				case 9 :System.out.println(" --------------------------------------------------------" );
						System.out.println(" -------------------- Type du lien ---------------------- ");
						System.out.println(" 1 - partant d'un composant");
						System.out.println(" 2 - partant d'un connecteur");
						System.out.println(" 3 - partant de l'interface de la configuration courante");
						System.out.print(" --> choix : ");
						choix = waiter.readLine();
						switch(Integer.valueOf(choix)){
						case 1 :for(IComposant c : cour.getListComposant()){
									System.out.println("  "+ c.getNom());
								}
								System.out.print("  --> composant de depart : ");
								choix = waiter.readLine();
								IComposant compo = cour.getComposant(choix);
								System.out.print("  --> port de depart : ");
								String port = waiter.readLine();
								
								cour.removeAttachement(compo, port);
								
								break;
						case 2 :for(IConnecteur c : cour.getListConnecteur()){
								System.out.println("  "+ c.getNom());
								}
								System.out.print("  --> connecteur de depart : ");
								choix = waiter.readLine();
								IConnecteur connect = cour.getConnecteur(choix);
								System.out.print("  --> port de depart : ");
								String from = waiter.readLine();
								
								cour.removeAttachement(connect, from);
								break;
						case 3 :for(String p : cour.getListPort()){
									System.out.println("  " + p);
								}
								System.out.print("  --> port de depart : ");
								String portConfigDepart = waiter.readLine();
								cour.removeBinding(portConfigDepart);
								break;
						default:System.out.println(" mauvaise demande");
								break;
						}
						break;
						
				case 10:System.out.println(" --------------------------------------------------------" );
						System.out.println(" -------------------- Type du lien ---------------------- ");
						System.out.println(" 1 - composant -> connecteur");
						System.out.println(" 2 - connecteur -> composant");
						System.out.println(" 3 - composant -> configuration courante");
						System.out.println(" 3 - configuration courante -> composant");
						System.out.print(" --> choix : ");
						choix = waiter.readLine();
			
						IComposant compo;
						IConnecteur connect;
						String from;
						String to;
						
						switch(Integer.valueOf(choix)){
						case 1 :for(IComposant c : cour.getListComposant()){
									System.out.println("  "+ c.getNom());
								}
								System.out.print("  --> composant de depart : ");
								choix = waiter.readLine();
								compo = cour.getComposant(choix);
								System.out.print("  --> port de depart : ");
								from = waiter.readLine();
								
								for(IConnecteur c : cour.getListConnecteur()){
									System.out.println("  "+ c.getNom());
								}
								System.out.print("  --> connecteur d'arriver : ");
								choix = waiter.readLine();
								connect = cour.getConnecteur(choix);
								System.out.print("  --> role d'arriver : ");
								to = waiter.readLine();
																
								cour.addAttachement(compo, from, connect, to);
								break;
						case 2 :for(IConnecteur c : cour.getListConnecteur()){
								System.out.println("  "+ c.getNom());
								}
								System.out.print("  --> connecteur de depart : ");
								choix = waiter.readLine();
								connect = cour.getConnecteur(choix);
								System.out.print("  --> role de depart : ");
								from = waiter.readLine();
								
								for(IComposant c : cour.getListComposant()){
									System.out.println("  "+ c.getNom());
								}
								System.out.print("  --> composant d'arriver : ");
								choix = waiter.readLine();
								compo = cour.getComposant(choix);
								System.out.print("  --> port d'arriver : ");
								to = waiter.readLine();
								
								cour.addAttachement(connect, from, compo, to);
								break;
								
						case 3 :for(IComposant c : cour.getListComposant()){
									System.out.println("  "+ c.getNom());
								}
								System.out.print("  --> composant de depart : ");
								choix = waiter.readLine();
								compo = cour.getComposant(choix);
								System.out.print("  --> port de depart : ");
								from = waiter.readLine();
							
								System.out.print("  --> port de la configuration courante : ");
								to = waiter.readLine();
																
								cour.addBinding(compo, from, to);
								break;
						
						case 4 :System.out.print("  --> port de la configuration courante : ");
								from = waiter.readLine();	
								
								for(IComposant c : cour.getListComposant()){
									System.out.println("  "+ c.getNom());
								}
								System.out.print("  --> composant d'arriver : ");
								choix = waiter.readLine();
								compo = cour.getComposant(choix);
								System.out.print("  --> port d'arriver : ");
								to = waiter.readLine();
								
								cour.addBinding(from, compo, to);
								
								break;
								
						default:System.out.println(" mauvaise demande");
								break;
						}
						break;	
										
				default:fin = true;
						break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}while(!fin);	
	}
}
