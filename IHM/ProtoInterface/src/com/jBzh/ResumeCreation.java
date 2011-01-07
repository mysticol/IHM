package com.jBzh;

import java.util.ArrayList;

import bean.Fiche;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ResumeCreation extends Activity {
	
	Fiche fiche = new Fiche();
	ArrayList<ResumNumeric> list = new ArrayList<ResumNumeric>();
	private ResumeAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  
	//Récupération de la fiche
	  Bundle objetbunble  = this.getIntent().getExtras();
	  fiche = (Fiche)(objetbunble.getSerializable("fiche"));
	  
	  setContentView(R.layout.resumecreation);
	  
	  ListView listv = (ListView)findViewById(R.id.list);
	  
	  //Infos Perso
	  for(String c : fiche.getInfos().keySet()){
		  if(fiche.getInfos().get(c)!=null){
			  list.add(new ResumNumeric(c, fiche.getInfos().get(c)));
		  } else {
			  list.add(new ResumNumeric(c, ""));
		  }
	  }
	  //Caracs Principales
	  for(String c : fiche.getCaracteristiquesPrincipales().keySet()){
		  if(fiche.getCaracteristiquesPrincipales().get(c).getValeur()!=null){
			  list.add(new ResumNumeric(c, fiche.getCaracteristiquesPrincipales().get(c).getValeur().toString()));
		  } else {
			  list.add(new ResumNumeric(c, ""));
		  }
	  }
	  
	  ResumeAdapter adapter = new ResumeAdapter(this, list);

      this.adapter = adapter;

      //adapter.addListener(this);
      
      listv.setAdapter(adapter);
	  
      final Button buttonRetour = (Button) findViewById(R.id.retour);
      buttonRetour.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
          	
          	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
  			Bundle objetbunble = new Bundle();
   
  			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
  			Intent intent = new Intent(ResumeCreation.this, ProtoInterface.class);
  			
  			//On affecte à l'Intent le Bundle que l'on a créé
  			intent.putExtras(objetbunble);
   
  			//On démarre l'autre Activity
  			startActivityForResult(intent, 1);

          }
      });
	  
	}

}
