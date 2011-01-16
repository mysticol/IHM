package com.jBzh;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;

import parseur.EcriteurFiche;
import parseur.ParseurModele;

import bean.Fiche;
import bean.Modele;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ListEditionNumeric extends Activity {
	
	Fiche fiche = new Fiche();
	ParseurModele pModele = new ParseurModele();
	EcriteurFiche eFiche= new EcriteurFiche();
	static String univers;
	static String typePerso;
	private String lv_arr[]={"Personnage","Caracteristiques Principales","Competences","Caracteristiques Secondaires","Vie","Pouvoirs","Equipement"};

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  
	  //Récupération de l'univers
	  Bundle objetbunble  = this.getIntent().getExtras();
	  if(objetbunble.getString("univers")!=null){
		  univers = objetbunble.getString("univers");
	  }
	  
	  if(objetbunble.getString("typePerso")!=null){
		  typePerso = objetbunble.getString("typePerso");
	  }
	  
	  if(objetbunble.getSerializable("fiche")!=null){
		  fiche = (Fiche) objetbunble.getSerializable("fiche");
	  } 
	  
	  ListView lv1;
	  
	  setContentView(R.layout.edition);
	  lv1=(ListView)findViewById(R.id.list);
	  // By using setAdpater method in listview we an add string array in list.
	  lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , lv_arr));

	  lv1.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view,
	        int position, long id) {
	    	
          	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
  			Bundle objetbunble = new Bundle();
   
  			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
  			Intent intent = new Intent(ListEditionNumeric.this, RemplissageEdition.class);
   
  			//Passage de la fiche à RemplissageCreation
			objetbunble.putSerializable("fiche", fiche);
			objetbunble.putString("categorie", lv_arr[position]);
			
			
  			//On affecte à l'Intent le Bundle que l'on a créé
  			intent.putExtras(objetbunble);
   
  			//On démarre l'autre Activity
  			startActivityForResult(intent, 1);

	    }
	  });
	  
      final Button buttonRetour = (Button) findViewById(R.id.retour);
      buttonRetour.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
          	
        	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
  			Bundle objetbunble = new Bundle();
   
  			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
  			Intent intent = new Intent(ListEditionNumeric.this, ChoixEdition.class);
   
  			//On affecte à l'Intent le Bundle que l'on a créé
  			intent.putExtras(objetbunble);
   
  			//On démarre l'autre Activity
  			startActivityForResult(intent, 1);

          }
      });
      
      final Button buttonValider = (Button) findViewById(R.id.validerCreation);
      buttonValider.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
          	
          	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
  			Bundle objetbunble = new Bundle();
   
  			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
  			Intent intent = new Intent(ListEditionNumeric.this, ResumeEdition.class);
    		
  			//tests des dossiers existants(Default, PJ, PNJ,...)
  			File tmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/"+univers+"/Default");
  			if(!tmp.exists()){
  				tmp.mkdir();
  			}
  			tmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/"+univers+"/Default"+"/PJ/");
  			if(!tmp.exists()){
  				tmp.mkdir();
  			}
  			tmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/"+univers+"/Default"+"/PNJ/");
  			if(!tmp.exists()){
  				tmp.mkdir();
  			}
  			tmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/"+univers+"/"+fiche.getCampagne());
  			if(!tmp.exists()){
  				tmp.mkdir();
  			}
  			tmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/"+univers+"/"+fiche.getCampagne()+"/PJ/");
  			if(!tmp.exists()){
  				tmp.mkdir();
  			}
  			tmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/"+univers+"/"+fiche.getCampagne()+"/PNJ/");
  			if(!tmp.exists()){
  				tmp.mkdir();
  			}
  			
  			//Sauvegarde de la fiche
  			
  			if(typePerso.equalsIgnoreCase("PJ")){
	  	  		if(fiche.getCampagne()!=null){
	  				eFiche.convertFicheToFile(fiche, getFilesDir().getAbsolutePath() + "/Fiches/"+univers+"/"+fiche.getCampagne()+"/PJ/"+fiche.getNom()+".xml");
	  			}else{
	  				eFiche.convertFicheToFile(fiche, getFilesDir().getAbsolutePath() + "/Fiches/"+univers+"/Default"+"/PJ/"+fiche.getNom()+".xml");
	  			}
  			}else{
  				if(fiche.getCampagne()!=null){
  					eFiche.convertFicheToFile(fiche, getFilesDir().getAbsolutePath() + "/Fiches/"+univers+"/"+fiche.getCampagne()+"/PNJ/"+fiche.getNom()+".xml");
  				}else{
  					eFiche.convertFicheToFile(fiche, getFilesDir().getAbsolutePath() + "/Fiches/"+univers+"/Default"+"/PNJ/"+fiche.getNom()+".xml");
  				}
  			}
  			
  			
  			//Passage de la fiche à RemplissageEdition
			objetbunble.putSerializable("fiche", fiche);
			
  			//On affecte à l'Intent le Bundle que l'on a créé
  			intent.putExtras(objetbunble);
   
  			//On démarre l'autre Activity
  			startActivityForResult(intent, 1);
          	
          }
      });
      
	}
	
}
