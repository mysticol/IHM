package com.jBzh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ChoixJoueur extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//on lui associe le layout afficahgequestionactivity.xml
    	setContentView(R.layout.choixjoueur);
    	
    	Spinner UniversSpinner = (Spinner) findViewById(R.id.UniversSpinner);
        ArrayAdapter<CharSequence> adapterU = ArrayAdapter.createFromResource(
                this, R.array.univers_array, android.R.layout.simple_spinner_item);
        adapterU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UniversSpinner.setAdapter(adapterU);
        
        Spinner campagneSpinner = (Spinner) findViewById(R.id.CampagneSpinner);
        ArrayAdapter<CharSequence> adapterC = ArrayAdapter.createFromResource(
                this, R.array.campagne_array, android.R.layout.simple_spinner_item);
        adapterC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campagneSpinner.setAdapter(adapterC);
        
        final Button buttonRetour = (Button) findViewById(R.id.retour);
        buttonRetour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	//On cr�� un objet Bundle, c'est ce qui va nous permetre d'envoyer des donn�es � l'autre Activity
    			Bundle objetbunble = new Bundle();
     
    			//On cr�� l'Intent qui va nous permettre d'afficher l'autre Activity
    			Intent intent = new Intent(ChoixJoueur.this, ProtoInterface.class);
     
    			//On affecte � l'Intent le Bundle que l'on a cr��
    			intent.putExtras(objetbunble);
     
    			//On d�marre l'autre Activity
    			startActivityForResult(intent, 1);

            }
        });
	}
	
	

}
