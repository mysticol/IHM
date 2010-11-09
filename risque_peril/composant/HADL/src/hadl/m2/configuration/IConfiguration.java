package hadl.m2.configuration;

import hadl.m2.composant.IComposant;
import hadl.m2.connecteur.IConnecteur;

import java.util.Observer;

public interface IConfiguration extends Observer {

	public void addComposant(IComposant newCompo);
	public void addConnecteur(IConnecteur newConnect);
	
	public void addAttachement(IComposant compoDepart , String portOut , IConnecteur connectDest , String rolesFrom);
	public void addAttachement(IConnecteur connectDepart , String rolesTo , IComposant compoDest , String portIn );
	public void addBinding(String portConfigDepart , IComposant compoDest , String portCompo );
	public void addBinding(IComposant compoDepart , String portCompo , String portConfigDest );
	
	public void removeComposant(IComposant compo);
	public void removeConnecteur(IConnecteur connect);
	
	public void removeAttachement(IComposant compoDepart , String portOut);
	public void removeAttachement(IConnecteur connectDepart , String rolesTo);
	public void removeBinding(String portConfigDepart);
	public void removeBinding(IComposant compoDepart , String portCompo);
	
	
}
