package com.jBzh;

import java.io.File;

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

public class ListCreationNumeric extends Activity {
	
	Fiche fiche;
	ParseurModele pModele;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  
	  //Récupération de l'univers
	  Bundle objetbunble  = this.getIntent().getExtras();
	  String univers = objetbunble.getString("univers");
	  
	  //Création de la fiche par rapport au modèle
	  File dirTmp = new File(getFilesDir().getAbsolutePath() + "/Systeme/Modeles/"+univers);
	  for(File f : dirTmp.listFiles()){
		  fiche = pModele.parseToEmptyFiche(f);  
  	  }
	  
	  
	  ListView lv1;
	  String lv_arr[]={"Personnage","caracteristiquesPrincipales","competences","caracteristiquesSecondaire","vie"};

	  setContentView(R.layout.creation);
	  lv1=(ListView)findViewById(R.id.list);
	  // By using setAdpater method in listview we an add string array in list.
	  lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , lv_arr));

	  lv1.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view,
	        int position, long id) {
	      // When clicked, show a toast with the TextView text
	      /*Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
	          Toast.LENGTH_SHORT).show();*/
	    }
	  });
	  
      final Button buttonRetour = (Button) findViewById(R.id.retour);
      buttonRetour.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
          	
          	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
  			Bundle objetbunble = new Bundle();
   
  			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
  			Intent intent = new Intent(ListCreationNumeric.this, ChoixCreation.class);
   
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
  			Intent intent = new Intent(ListCreationNumeric.this, RemplissageCreation.class);
   
  			//Passage de la fiche à RemplissageCreation
			//objetbunble.put("fiche", (Object)fiche);
			
  			//On affecte à l'Intent le Bundle que l'on a créé
  			intent.putExtras(objetbunble);
   
  			//On démarre l'autre Activity
  			startActivityForResult(intent, 1);
          	
          }
      });
      
	}
	
	
	
	
	
	
	
	
	
	

}
