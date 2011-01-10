package com.jBzh;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import bean.Caracteristique;
import bean.Competence;
import bean.vie.Classic;
import bean.Fiche;

import com.jBzh.CreationNumericAdapter.CreationNumericAdapterListener;
import com.jBzh.CreationNumericTextAdapter.CreationNumericTextAdapterListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class RemplissageCreation extends Activity implements CreationNumericAdapterListener,CreationNumericTextAdapterListener{
	
	//private static ArrayList<Numeric> listN = Numeric.getAListOfNumeric();
	ArrayList<Numeric> listN = new ArrayList<Numeric>();
	ArrayList<NumericText> listNText = new ArrayList<NumericText>();
	private CreationNumericAdapter adapter;
	private CreationNumericTextAdapter adapterText;
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
	  
      ListView list = (ListView)findViewById(R.id.list);
	  
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

	  } else if(categorie.equalsIgnoreCase("CaracteristiquesPrincipales")) {
		  
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
		  
	  }else if(categorie.equalsIgnoreCase("CaracteristiquesSecondaires")) {
		  
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
		  
	  }else if(categorie.equalsIgnoreCase("Pouvoirs")){
		  
		  
	  }else if(categorie.equalsIgnoreCase("Equipement")){
		  
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
  				
  				
  			} else if(categorie.equalsIgnoreCase("CaracteristiquesPrincipales")){
  				
  	  			for(Numeric n : listN){
  	  				fiche.getCaracteristiquesPrincipales().get(n.getNomNumeric()).setValeur(n.getValeur());
  	  			}  				
  				
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
	
	

}
