package com.jBzh;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
	
	Fiche fiche = new Fiche();
	ParseurModele pModele = new ParseurModele();
	static String univers;
	private String lv_arr[]={"Personnage","CaracteristiquesPrincipales","Competences","CaracteristiquesSecondaire","Vie","Pouvoirs","Equipement"};

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  
	  //Récupération de l'univers
	  Bundle objetbunble  = this.getIntent().getExtras();
	  if(objetbunble.getString("univers")!=null){
		  univers = objetbunble.getString("univers");
	  }
	  
	  //Création de la fiche par rapport au modèle
	  System.out.println(getFilesDir().getAbsolutePath() + "/Systeme/Modeles/"+univers);
	  File dirTmp = new File(getFilesDir().getAbsolutePath() + "/Systeme/Modeles/"+univers);
	  for(File f : dirTmp.listFiles()){
		  fiche = pModele.parseToEmptyFiche(f);
  	  }
	  
	  
	  ListView lv1;
	  
	  setContentView(R.layout.creation);
	  lv1=(ListView)findViewById(R.id.list);
	  // By using setAdpater method in listview we an add string array in list.
	  lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , lv_arr));

	  lv1.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view,
	        int position, long id) {
	    	
          	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
  			Bundle objetbunble = new Bundle();
   
  			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
  			Intent intent = new Intent(ListCreationNumeric.this, RemplissageCreation.class);
   
  			//Transformation de la fiche en byte[]
  			byte[] ficheByte=null;
			try {
				ficheByte = getBytes(fiche);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println("ficheListCreationNumeric : " + fiche.getCaracteristiquesPrincipales().size());
  			System.out.println("ficheListCreationNumeric : " + ficheByte);
			
  			//Passage de la fiche à RemplissageCreation
			objetbunble.putByteArray("fiche", ficheByte);
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
   
  			//Transformation de la fiche en byte[]
  			byte[] ficheByte=null;
			try {
				ficheByte = getBytes(fiche);
			} catch (IOException e) {
				e.printStackTrace();
			}
  			
  			//Passage de la fiche à RemplissageCreation
			objetbunble.putByteArray("fiche", ficheByte);
			
  			//On affecte à l'Intent le Bundle que l'on a créé
  			intent.putExtras(objetbunble);
   
  			//On démarre l'autre Activity
  			startActivityForResult(intent, 1);
          	
          }
      });
      
	}
	
	
	public static byte[] getBytes(Object obj) throws java.io.IOException{
	      ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
	      ObjectOutputStream oos = new ObjectOutputStream(bos); 
	      oos.writeObject(obj);
	      oos.flush(); 
	      oos.close(); 
	      bos.close();
	      byte [] data = bos.toByteArray();
	      return data;
	  }
	
	
	
	
	
	
	

}
