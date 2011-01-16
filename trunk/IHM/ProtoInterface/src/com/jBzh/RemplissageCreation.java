package com.jBzh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import bean.Competence;
import bean.Equipement;
import bean.Fiche;
import bean.Pouvoir;
import bean.vie.Classic;

import com.jBzh.CreationNumericAdapter.CreationNumericAdapterListener;
import com.jBzh.CreationNumericTextAdapter.CreationNumericTextAdapterListener;
import com.jBzh.CreationNumericTextAdapter2.CreationNumericTextAdapterListener2;

public class RemplissageCreation extends Activity implements CreationNumericAdapterListener,CreationNumericTextAdapterListener, CreationNumericTextAdapterListener2 {
	
	//private static ArrayList<Numeric> listN = Numeric.getAListOfNumeric();
	ArrayList<Numeric> listN = new ArrayList<Numeric>();
	ArrayList<NumericText> listNText = new ArrayList<NumericText>();
	private CreationNumericAdapter adapter;
	private CreationNumericTextAdapter adapterText;
	private CreationNumericTextAdapter2 adapterText2;
	private RemplissageCreation instance;
	Fiche fiche = new Fiche();
	String categorie;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  
	  //R�cup�ration de la fiche
	  Bundle objetbunble  = this.getIntent().getExtras();
	  fiche = (Fiche)(objetbunble.getSerializable("fiche"));
	  // Recuperation de la categorie
	  categorie = objetbunble.getString("categorie");
	  
	  setContentView(R.layout.remplissagecreation);
	  
      final ListView list = (ListView)findViewById(R.id.list);
	  
      instance = this;
      
	  //En fonction de la categorie choisie, on affiche des elements differents
	  if(categorie.equalsIgnoreCase("Personnage")){
		  
		  if(fiche.getNom()!=null){
			  listNText.add(new NumericText("nom", fiche.getNom()));
		  } else {
			  listNText.add(new NumericText("nom", ""));
		  }
		  if(fiche.getPoid()!=null){
			  listNText.add(new NumericText("poids", fiche.getPoid()));
		  } else {
			  listNText.add(new NumericText("poids", ""));
		  }
		  if(fiche.getTaille()!=null){
			  listNText.add(new NumericText("taille", fiche.getTaille()));
		  } else {
			  listNText.add(new NumericText("taille", ""));
		  }
		  if(fiche.getAge()!=null){
			  listNText.add(new NumericText("age", fiche.getAge()));
		  } else {
			  listNText.add(new NumericText("age", ""));
		  }
		  if(fiche.getConcept()!=null){
			  listNText.add(new NumericText("concept", fiche.getConcept()));
		  } else {
			  listNText.add(new NumericText("concept", ""));
		  }
		  if(fiche.getXp()!=null){
			  listNText.add(new NumericText("experience", fiche.getXp()));
		  } else {
			  listNText.add(new NumericText("experience", ""));
		  }
		  if(fiche.getCampagne()!=null){
			  listNText.add(new NumericText("campagne", fiche.getCampagne()));
		  } else {
			  listNText.add(new NumericText("campagne", ""));
		  }
		  
		  CreationNumericTextAdapter adapter = new CreationNumericTextAdapter(this, listNText);

	      this.adapterText = adapter;

	      adapter.addListener(this);
	      
	      list.setAdapter(adapterText);

	  } else if(categorie.equalsIgnoreCase("Caracteristiques Principales")) {
		  
		  for(String c : fiche.getCaracteristiquesPrincipales().keySet()){
			  if(fiche.getCaracteristiquesPrincipales().get(c).getValeur()!=null){
				  listN.add(new Numeric(c, fiche.getCaracteristiquesPrincipales().get(c).getValeur()));
			  } else {
				  listN.add(new Numeric(c, 0));
			  }
		  }

	      CreationNumericAdapter adapter = new CreationNumericAdapter(this, listN);

	      this.adapter = adapter;
	     
	      adapter.addListener(this);
	      
	      list.setAdapter(this.adapter);
	      		  
	  } else if(categorie.equalsIgnoreCase("Competences")) {
		  
		  //r�cup�ration des comp�tences
		  for(Competence c : fiche.getListCompetences()){
				  listN.add(new Numeric(c.getNom(),c.getValeur()));
		  }  
		  
	      CreationNumericAdapter adapter = new CreationNumericAdapter(this, listN);

	      this.adapter = adapter;
	     
	      adapter.addListener(this);
	      
	      list.setAdapter(this.adapter);
		  
	  }else if(categorie.equalsIgnoreCase("Caracteristiques Secondaires")) {
		  
		  for(String c : fiche.getCaracteristiquesSecondaire().keySet()){
			  if(fiche.getCaracteristiquesSecondaire().get(c).getValeur()!=null){
				  listN.add(new Numeric(c, fiche.getCaracteristiquesSecondaire().get(c).getValeur()));
			  } else {
				  listN.add(new Numeric(c, 0));
			  }
		  }
		  
		  CreationNumericAdapter adapter = new CreationNumericAdapter(this, listN);

	      this.adapter = adapter;
	     
	      adapter.addListener(this);
	      
	      list.setAdapter(this.adapter);
		  
	  }else if(categorie.equalsIgnoreCase("Vie")){
		  
		  if(((Classic)fiche.getBarreDeVie()).getActuel()!=null){
			  listN.add(new Numeric("Vie", ((Classic)fiche.getBarreDeVie()).getActuel()));
		  } else{
			  listN.add(new Numeric("Vie", 0));
		  }
		  
	      CreationNumericAdapter adapter = new CreationNumericAdapter(this, listN);

	      this.adapter = adapter;
	     
	      adapter.addListener(this);
	      
	      list.setAdapter(this.adapter);	
	      
	  }else if(categorie.equalsIgnoreCase("Pouvoirs")){
		  
		  // On rempli la liste des pouvoirs deja rempli, si besoin
		  if(fiche.getPouvoirs()!=null){
			  
			  for(Pouvoir p : fiche.getPouvoirs()){
				  listNText.add(new NumericText(p.getNom(), p.getDescription()));				  
			  }
			  
		  }
		  
		  if(listNText.size()==0){
			  listNText.add(new NumericText("", ""));
		  }
		  
		 final CreationNumericTextAdapter2 adapter = new CreationNumericTextAdapter2(this, listNText);

	      this.adapterText2 = adapter;

	      adapter.addListener(this);
	      
	      list.setAdapter(adapterText2);	
		  
		  // On cree les boutons + et - a la main
		  
		  LinearLayout llh5 = new LinearLayout(this);
		  llh5.setOrientation(LinearLayout.HORIZONTAL);
		  llh5.setPadding(240, 0, 0, 0);
		  
		  
		  
		  // Creation du bouton -
		  final Button boutonMoins = new Button (this);
		  boutonMoins.setId(22);
		  boutonMoins.setWidth(40);
		  boutonMoins.setHeight(50);
		  boutonMoins.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				//TODO on enleve une ligne
				if(listNText.size()!=0){
					
					listNText.remove((listNText.size())-1);
					
					CreationNumericTextAdapter2 adapterMoins = new CreationNumericTextAdapter2(instance, listNText);
	
				    adapterText2 = adapterMoins;
	
				    adapter.addListener(instance);
				      
				    list.setAdapter(adapterText2);
				}
			}
		  });
		  boutonMoins.setText("-");
		  boutonMoins.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		  
		  llh5.addView(boutonMoins);
		  
		// Creation du bouton +
		  final Button boutonPLus = new Button (this);
		  
		  boutonPLus.setId(21);
		  boutonPLus.setWidth(40);
		  boutonPLus.setHeight(50);
		  boutonPLus.setOnClickListener(new View.OnClickListener() {
			
			 
			@Override
			public void onClick(View v) {
				
				//TODO on ajoute une ligne
					
					listNText.add(new NumericText("", ""));
					
					CreationNumericTextAdapter2 adapterPlus = new CreationNumericTextAdapter2(instance, listNText);
	
				    adapterText2 = adapterPlus;
	
				    adapter.addListener(instance);
				      
				    list.setAdapter(adapterText2);
				
			}
		  });
		  boutonPLus.setText("+");
		  boutonPLus.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		  
		  llh5.addView(boutonPLus);
		  
		  
		  addContentView(llh5, new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		  
		  
		  
		  
		  

		  
  
		  
	  }else if(categorie.equalsIgnoreCase("Equipement")){
		  
		  // On rempli la liste des equipements deja rempli, si besoin
		  if(fiche.getEquipements()!=null){
			  
			  for(Equipement p : fiche.getEquipements()){
				  listNText.add(new NumericText(p.getNom(), p.getDescription()));				  
			  }
			  
		  }
		  
		  if(listNText.size()==0){
			  listNText.add(new NumericText("", ""));
		  }
		  
		 final CreationNumericTextAdapter2 adapter = new CreationNumericTextAdapter2(this, listNText);

	      this.adapterText2 = adapter;

	      adapter.addListener(this);
	      
	      list.setAdapter(adapterText2);	
		  
		  // On cree les boutons + et - a la main
		  
		  LinearLayout llh5 = new LinearLayout(this);
		  llh5.setOrientation(LinearLayout.HORIZONTAL);
		  llh5.setPadding(240, 0, 0, 0);
		  
		  
		  
		  // Creation du bouton -
		  final Button boutonMoins = new Button (this);
		  boutonMoins.setId(22);
		  boutonMoins.setWidth(40);
		  boutonMoins.setHeight(50);
		  boutonMoins.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				//TODO on enleve une ligne
				if(listNText.size()!=0){
					
					listNText.remove((listNText.size())-1);
					
					CreationNumericTextAdapter2 adapterMoins = new CreationNumericTextAdapter2(instance, listNText);
	
				    adapterText2 = adapterMoins;
	
				    adapter.addListener(instance);
				      
				    list.setAdapter(adapterText2);
				}
			}
		  });
		  boutonMoins.setText("-");
		  boutonMoins.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		  
		  llh5.addView(boutonMoins);
		  
		// Creation du bouton +
		  final Button boutonPLus = new Button (this);
		  
		  boutonPLus.setId(21);
		  boutonPLus.setWidth(40);
		  boutonPLus.setHeight(50);
		  boutonPLus.setOnClickListener(new View.OnClickListener() {
			
			 
			@Override
			public void onClick(View v) {
				
				//TODO on ajoute une ligne
					
					listNText.add(new NumericText("", ""));
					
					CreationNumericTextAdapter2 adapterPlus = new CreationNumericTextAdapter2(instance, listNText);
	
				    adapterText2 = adapterPlus;
	
				    adapter.addListener(instance);
				      
				    list.setAdapter(adapterText2);
				
			}
		  });
		  boutonPLus.setText("+");
		  boutonPLus.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		  
		  llh5.addView(boutonPLus);
		  
		  
		  addContentView(llh5, new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		  
		  
	  }
    
	  
      final Button buttonRetour = (Button) findViewById(R.id.retour);
      buttonRetour.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
          	
          	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
  			Bundle objetbunble = new Bundle();
   
  			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
  			Intent intent = new Intent(RemplissageCreation.this, ListCreationNumeric.class);
  			
  			//On sauvegarde les valeurs changees dans la fiche
  			if(categorie.equalsIgnoreCase("Personnage")){
  				
  				HashMap<String, String> infos = new HashMap<String, String>();
  				
	
  				fiche.setCampagne(listNText.get(listNText.size()-1).getValeur());
  				fiche.setXp(listNText.get(listNText.size()-2).getValeur());
  				fiche.setConcept(listNText.get(listNText.size()-3).getValeur());
  				fiche.setAge(listNText.get(listNText.size()-4).getValeur());
  				fiche.setTaille(listNText.get(listNText.size()-5).getValeur());
  				fiche.setPoid(listNText.get(listNText.size()-6).getValeur());
  				fiche.setNom(listNText.get(listNText.size()-7).getValeur());
  				
  				infos.put("Campagne", fiche.getCampagne());
  				infos.put("Experience", fiche.getXp());
  				infos.put("Concept", fiche.getConcept());
  				infos.put("Age", fiche.getAge());
  				infos.put("Taille", fiche.getTaille());
  				infos.put("Poids", fiche.getPoid());
  				infos.put("Nom", fiche.getNom());
  				
  				fiche.setInfos(infos);
  				
  				
  				for(NumericText nt : listNText){
  					System.out.println("Enregistrer Personnage : " + nt.getNomNumeric() + " => " + nt.getValeur());
  				}
  				
  				
  			} else if(categorie.equalsIgnoreCase("Caracteristiques Principales")){
  				
  	  			for(Numeric n : listN){
  	  				fiche.getCaracteristiquesPrincipales().get(n.getNomNumeric()).setValeur(n.getValeur());
  	  				fiche.getCaracteristiquesPrincipales().get(n.getNomNumeric()).setMaximum(n.getValeur());
  	  			}  				
  				
  			} else if(categorie.equalsIgnoreCase("Competences")){
  				
  	  			for(Numeric n : listN){
  	  				for(List<Competence> lc : fiche.getCompetences().values()){
  	  					for(Competence c : lc){
  	  						if(c.getNom().equalsIgnoreCase(n.getNomNumeric())){
  	  							c.setValeur(n.getValeur());
  	  						}
  	  					}
  	  				}
  	  			}
  			} else if(categorie.equalsIgnoreCase("Caracteristiques Secondaires")){
  				
  	  			for(Numeric n : listN){
  	  				fiche.getCaracteristiquesSecondaire().get(n.getNomNumeric()).setValeur(n.getValeur());
  	  				fiche.getCaracteristiquesSecondaire().get(n.getNomNumeric()).setMaximum(n.getValeur());
  	  			}  				
  				
  			} else if(categorie.equalsIgnoreCase("Vie")){
  				
  				fiche.getBarreDeVie().setActuel(listN.get(0).getValeur());
  				fiche.getBarreDeVie().setTotal(listN.get(0).getValeur());
  				
  			} else if(categorie.equalsIgnoreCase("Pouvoirs")){
  				
  				LinkedList<Pouvoir> lp = fiche.getPouvoirs();
  				lp.clear();
  				
  				for(NumericText nt : listNText){
  					lp.add(new Pouvoir(nt.getNomNumeric(), nt.getValeur()));
  				}
  				
  				fiche.setPouvoirs(lp);
  				
  			} else if(categorie.equalsIgnoreCase("Equipement")){
  				
  				LinkedList<Equipement> lp = fiche.getEquipements();
  				lp.clear();
  				
  				for(NumericText nt : listNText){
  					lp.add(new Equipement(nt.getNomNumeric(), nt.getValeur()));
  				}
  				
  				fiche.setEquipements(lp);
  			}
  			 			
  			//Passage de la fiche à RemplissageCreation
			objetbunble.putSerializable("fiche", fiche);
   
  			//On affecte à l'Intent le Bundle que l'on a créé
  			intent.putExtras(objetbunble);
   
  			//On démarre l'autre Activity
  			startActivityForResult(intent, 1);

          }
      });
      
	  
	}

	@Override
	public void onClickMoins(Numeric item, int position) {
		item.decr();
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onClickPlus(Numeric item, int position) {
		item.incr();
		adapter.notifyDataSetChanged();		
	}

	@Override
	public void onTextChanged(NumericText item, int position) {
		System.out.println("On a change : " + item.getNomNumeric() + " : " + item.getValeur());
		adapterText.notifyDataSetChanged();
	}
	
	@Override
	public void onTextChanged2(NumericText item, int position) {
		System.out.println("On a change : " + item.getNomNumeric() + " : " + item.getValeur());
		adapterText2.notifyDataSetChanged();
	}	

}
