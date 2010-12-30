package com.jBzh;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ChoixCreation extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//on lui associe le layout afficahgequestionactivity.xml
    	setContentView(R.layout.choixcreation);
    	
    	Spinner UniversSpinner = (Spinner) findViewById(R.id.UniversSpinner);
        /*ArrayAdapter<CharSequence> adapterU = ArrayAdapter.createFromResource(
                this, R.array.univers_array, android.R.layout.simple_spinner_item);
        adapterU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UniversSpinner.setAdapter(adapterU);*/
    	
    	ArrayList<String> listUnivers = new ArrayList<String>();
    	
    	File root = getFilesDir();
    	File dirTmp = new File(getFilesDir().getAbsolutePath() + "/Systeme/Modeles");
    	for(File f : dirTmp.listFiles()){
    		listUnivers.add(f.getName());
    	}
    	
    	
    	ArrayAdapter<String> adapterU = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listUnivers);
        adapterU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UniversSpinner.setAdapter(adapterU);  
        
        
        final Button buttonRetour = (Button) findViewById(R.id.retour);
        buttonRetour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
    			Bundle objetbunble = new Bundle();
     
    			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
    			Intent intent = new Intent(ChoixCreation.this, ProtoInterface.class);
     
    			//On affecte à l'Intent le Bundle que l'on a créé
    			intent.putExtras(objetbunble);
     
    			//On démarre l'autre Activity
    			startActivityForResult(intent, 1);

            }
        });
        
        final Button buttonValider = (Button) findViewById(R.id.valider);
        buttonValider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
    			Bundle objetbunble = new Bundle();
     
    			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
    			Intent intent = new Intent(ChoixCreation.this, ListCreationNumeric.class);
     
    			//On affecte à l'Intent le Bundle que l'on a créé
    			intent.putExtras(objetbunble);
     
    			//On démarre l'autre Activity
    			startActivityForResult(intent, 1);

            }
        });
        
        
        
	}
	
	

}
