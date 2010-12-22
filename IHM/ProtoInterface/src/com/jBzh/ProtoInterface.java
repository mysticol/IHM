package com.jBzh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProtoInterface extends Activity {
	
	private static final int CODE_DE_MON_ACTIVITE = 1;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final Button buttonMJ = (Button) findViewById(R.id.MJ);
        buttonMJ.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
    			Bundle objetbunble = new Bundle();
     
    			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
    			Intent intent = new Intent(ProtoInterface.this, ChoixMJ.class);
     
    			//On affecte à l'Intent le Bundle que l'on a créé
    			intent.putExtras(objetbunble);
     
    			//On démarre l'autre Activity
    			startActivityForResult(intent, CODE_DE_MON_ACTIVITE);

            }
        });
        
        final Button buttonJoueur = (Button) findViewById(R.id.Joueur);
        buttonJoueur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
    			Bundle objetbunble = new Bundle();
     
    			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
    			Intent intent = new Intent(ProtoInterface.this, ChoixJoueur.class);
     
    			//On affecte à l'Intent le Bundle que l'on a créé
    			intent.putExtras(objetbunble);
     
    			//On démarre l'autre Activity
    			startActivityForResult(intent, CODE_DE_MON_ACTIVITE);

            }
        });
    }
}