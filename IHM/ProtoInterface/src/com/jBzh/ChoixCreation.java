package com.jBzh;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

public class ChoixCreation extends Activity{

	private Spinner UniversSpinner;
	private String typePerso="PJ";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//on lui associe le layout afficahgequestionactivity.xml
    	setContentView(R.layout.choixcreation);
    	    	
    	UniversSpinner = (Spinner) findViewById(R.id.UniversSpinner);
    	
    	ArrayList<String> listUnivers = new ArrayList<String>();
    	
    	File dirTmp = new File(getFilesDir().getAbsolutePath() + "/Systeme/Modeles");
    	for(File f : dirTmp.listFiles()){
    		listUnivers.add(f.getName());
    	}
    	
    	
    	ArrayAdapter<String> adapterU = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listUnivers);
        adapterU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UniversSpinner.setAdapter(adapterU);  
        
        OnClickListener radio_listener = new OnClickListener() {
            public void onClick(View v) {
                RadioButton rb = (RadioButton) v;
                typePerso = rb.getText().toString();
            }
        };
        
        final RadioButton radio_pj = (RadioButton) findViewById(R.id.pj);
        final RadioButton radio_pnj = (RadioButton) findViewById(R.id.pnj);
        radio_pj.setChecked(true);
        radio_pj.setOnClickListener(radio_listener);
        radio_pnj.setOnClickListener(radio_listener);
        
        final Button buttonRetour = (Button) findViewById(R.id.retour);
        buttonRetour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	//On cr�� un objet Bundle, c'est ce qui va nous permetre d'envoyer des donn�es � l'autre Activity
    			Bundle objetbunble = new Bundle();
     
    			//On cr�� l'Intent qui va nous permettre d'afficher l'autre Activity
    			Intent intent = new Intent(ChoixCreation.this, ProtoInterface.class);
     
    			//On affecte � l'Intent le Bundle que l'on a cr��
    			intent.putExtras(objetbunble);
     
    			//On d�marre l'autre Activity
    			startActivityForResult(intent, 1);

            }
        });
        
        final Button buttonValider = (Button) findViewById(R.id.valider);
        buttonValider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	//On cr�� un objet Bundle, c'est ce qui va nous permetre d'envoyer des donn�es � l'autre Activity
    			Bundle objetbunble = new Bundle();
     
    			//On cr�� l'Intent qui va nous permettre d'afficher l'autre Activity
    			Intent intent = new Intent(ChoixCreation.this, ListCreationNumeric.class);
    			
    			
    			//Passage de l'univers � ListCreationNumeric
    			objetbunble.putString("univers", UniversSpinner.getSelectedItem().toString());
    			System.out.println("passage du typePerso: "+typePerso);
    			objetbunble.putString("typePerso", typePerso);
    			
    			//On affecte � l'Intent le Bundle que l'on a cr��
    			intent.putExtras(objetbunble);
     
    			//On d�marre l'autre Activity
    			startActivityForResult(intent, 1);

            }
        });
        
        
        
	}
	
	

}
