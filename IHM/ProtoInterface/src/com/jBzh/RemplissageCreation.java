package com.jBzh;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import bean.Fiche;

import com.jBzh.CreationNumericAdapter.CreationNumericAdapterListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class RemplissageCreation extends Activity implements CreationNumericAdapterListener{
	
	//private static ArrayList<Numeric> listN = Numeric.getAListOfNumeric();
	static ArrayList<Numeric> listN = new ArrayList<Numeric>();
	private static CreationNumericAdapter adapter;
	Fiche fiche = new Fiche();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  
	  //Récupération de la fiche
	  Bundle objetbunble  = this.getIntent().getExtras();
	  fiche = (Fiche)((Object)objetbunble.getByteArray("fiche"));
	  System.out.println("fiche remplissageCreation: " + fiche);
	  // Recuperation de la categorie
	  String categorie = objetbunble.getString("categorie");
	  
	  //En fonction de la categorie choisir, on affiche des elements differents
	  if(categorie.equalsIgnoreCase("Personnage")){
		  
		  setContentView(R.layout.remplissagecreation);

	      CreationNumericAdapter adapter = new CreationNumericAdapter(this, listN);

	      this.adapter = adapter;
	     
	      adapter.addListener(this);

	  } else if(categorie.equalsIgnoreCase("CaracteristiquesPrincipales")) {
		  
		  for(String c : fiche.getCaracteristiquesPrincipales().keySet()){
			  if(fiche.getCaracteristiquesPrincipales().get(c).getValeur()!=null){
				  listN.add(new Numeric(c, fiche.getCaracteristiquesPrincipales().get(c).getValeur()));
			  } else {
				  listN.add(new Numeric(c, 0));
			  }
		  }
		  
		  setContentView(R.layout.remplissagecreation);

	      CreationNumericAdapter adapter = new CreationNumericAdapter(this, listN);

	      this.adapter = adapter;
	     
	      adapter.addListener(this);		  
		  
	  }

	  

      
      ListView list = (ListView)findViewById(R.id.list);
      
      list.setAdapter(adapter);
	  
      final Button buttonRetour = (Button) findViewById(R.id.retour);
      buttonRetour.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
          	
          	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
  			Bundle objetbunble = new Bundle();
   
  			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
  			Intent intent = new Intent(RemplissageCreation.this, ListCreationNumeric.class);
  			
  			//On sauvegarde les valeurs changees dans la fiche
  			for(Numeric n : listN){
  				System.out.println(n.getNomNumeric() + " => " + n.getValeur());
  			}
  			
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
