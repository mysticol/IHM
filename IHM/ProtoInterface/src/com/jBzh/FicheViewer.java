package com.jBzh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import bean.Fiche;

public class FicheViewer extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    
	    setContentView(R.layout.ficheviewer);
        
	    final Bundle objectbunble  = this.getIntent().getExtras();
	    
	    final Fiche fiche = (Fiche) objectbunble.getSerializable("fiche");
	    
	    final Button buttonFicheToRoll = (Button) findViewById(R.id.FicheToRoll);
	    buttonFicheToRoll.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View v) {
	          	
	          	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
	  			//Bundle objetbunble = new Bundle();
	   
	  			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
	  			Intent intent = new Intent(FicheViewer.this, ChoixJet.class);
	   
	  			//On affecte à l'Intent le Bundle que l'on a créé
	  			intent.putExtras(objectbunble);
	   
	  			//On démarre l'autre Activity
	  			startActivityForResult(intent, 1);

	          }
	      });
	    
	    final Button buttonFicheToJauge = (Button) findViewById(R.id.FicheToJauge);
	    buttonFicheToJauge.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View v) {
	          	
	          	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
	  			//Bundle objetbunble = new Bundle();
	   
	  			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
	  			Intent intent = new Intent(FicheViewer.this, Jauge.class);
	   
	  			//On affecte à l'Intent le Bundle que l'on a créé
	  			intent.putExtras(objectbunble);
	   
	  			//On démarre l'autre Activity
	  			startActivityForResult(intent, 1);

	          }
	      });
	    
	    	    
	}	
}
