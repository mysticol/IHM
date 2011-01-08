package com.jBzh;

import bean.Fiche;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Jauge extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    
	    setContentView(R.layout.jaugemodifier);
        
	    final Bundle objectbunble  = this.getIntent().getExtras();
	    
	    final Fiche fiche = (Fiche) objectbunble.getSerializable("fiche");
	    
	    final Button buttonJaugeToRoll = (Button) findViewById(R.id.jaugeToRoll);
	    buttonJaugeToRoll.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View v) {
	          	
	          	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
	  			//Bundle objetbunble = new Bundle();
	   
	  			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
	  			Intent intent = new Intent(Jauge.this, ChoixJet.class);
	   
	  			//On affecte à l'Intent le Bundle que l'on a créé
	  			intent.putExtras(objectbunble);
	   
	  			//On démarre l'autre Activity
	  			startActivityForResult(intent, 1);

	          }
	      });
	    
	    final Button buttonJaugeToFiche = (Button) findViewById(R.id.jaugeToFiche);
	    buttonJaugeToFiche.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View v) {
	          	
	          	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
	  			//Bundle objetbunble = new Bundle();
	   
	  			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
	  			Intent intent = new Intent(Jauge.this, FicheViewer.class);
	   
	  			//On affecte à l'Intent le Bundle que l'on a créé
	  			intent.putExtras(objectbunble);
	   
	  			//On démarre l'autre Activity
	  			startActivityForResult(intent, 1);

	          }
	      });
	    
	    
	    
	    
	    
	    
	}	
}
