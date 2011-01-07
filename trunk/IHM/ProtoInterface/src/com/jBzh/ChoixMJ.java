package com.jBzh;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ChoixMJ extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
    	//on lui associe le layout afficahgequestionactivity.xml
    	setContentView(R.layout.choixmj);
    	
    	
    	
    	Spinner UniversSpinner = (Spinner) findViewById(R.id.UniversSpinner);
        /*ArrayAdapter<CharSequence> adapterU = ArrayAdapter.createFromResource(
                this, android.R.layout.simple_spinner_item, android.R.layout.simple_spinner_item);
        adapterU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UniversSpinner.setAdapter(adapterU);*/
    	
    	//String[] array = {"aaaa", "bbb"};
    	ArrayList<String> univers = new ArrayList<String>();
    	ArrayList<String> campagnes = new ArrayList<String>();
    	
    	File root = getFileStreamPath("Fiches");
    	
    	//Récupération des univers existant
    	for(File f : root.listFiles()){
    		univers.add(f.getName());
        }
    	
    	//Récupération des campagnes existantes
    	for(File f : root.listFiles()){
    		campagnes.add(f.getName());
        }
    	
    	
    	ArrayAdapter<String> adapterU = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, univers);
        adapterU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UniversSpinner.setAdapter(adapterU);    	
        
        Spinner campagneSpinner = (Spinner) findViewById(R.id.CampagneSpinner);
        ArrayAdapter<String> adapterC = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, campagnes);
        adapterC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campagneSpinner.setAdapter(adapterC);
        
        final Button buttonRetour = (Button) findViewById(R.id.retour);
        buttonRetour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	//On cr�� un objet Bundle, c'est ce qui va nous permetre d'envoyer des donn�es � l'autre Activity
    			Bundle objetbunble = new Bundle();
     
    			//On cr�� l'Intent qui va nous permettre d'afficher l'autre Activity
    			Intent intent = new Intent(ChoixMJ.this, ProtoInterface.class);
     
    			//On affecte � l'Intent le Bundle que l'on a cr��
    			intent.putExtras(objetbunble);
     
    			//On d�marre l'autre Activity
    			startActivityForResult(intent, 1);

            }
        });
        
        

	}
	

}
