package com.jBzh;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class ChoixMJ extends Activity{

	private ChoixMJ instance;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
    	//on lui associe le layout afficahgequestionactivity.xml
    	setContentView(R.layout.choixmj);
    	
    	
    	
    	final Spinner UniversSpinner = (Spinner) findViewById(R.id.UniversSpinner);
    	
    	final ArrayList<String> univers = new ArrayList<String>();
    	final ArrayList<String> campagnes = new ArrayList<String>();
    	final ArrayList<String> persos = new ArrayList<String>();
    	
    	this.instance = this;
    	
    	File universPath = new File(getFilesDir().getAbsolutePath() + "/Fiches");
    	
    	//Récupération des univers existant
    	for(File f : universPath.listFiles()){
    		univers.add(f.getName());
        }
    	
    	ArrayAdapter<String> adapterU = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, univers);
        adapterU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UniversSpinner.setAdapter(adapterU); 
        
		String universChoisi = UniversSpinner.getSelectedItem().toString();
    	//Récupération des campagnes existantes
        File campagnePath = new File(getFilesDir().getAbsolutePath() + "/Fiches/"+universChoisi);
        for(File f : campagnePath.listFiles()){
        		campagnes.add(f.getName());
        }
        
        UniversSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				campagnes.clear();
				File path = new File(getFilesDir().getAbsolutePath() + "/Fiches/"+UniversSpinner.getSelectedItem().toString());
				for(File f : path.listFiles()){
	        		campagnes.add(f.getName());
				}
				
		        final Spinner campagneSpinner = (Spinner) findViewById(R.id.CampagneSpinner);
		        ArrayAdapter<String> adapterC = new ArrayAdapter<String>(instance, android.R.layout.simple_spinner_item, campagnes);
		        adapterC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		        System.out.println("campagneSpinner : " + campagneSpinner);
		        campagneSpinner.setAdapter(adapterC);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});      
        
        final Spinner campagneSpinner = (Spinner) findViewById(R.id.CampagneSpinner);
        ArrayAdapter<String> adapterC = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, campagnes);
        adapterC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campagneSpinner.setAdapter(adapterC);
        
        
        final Button buttonValider = (Button) findViewById(R.id.valider);
        buttonValider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	//On cr�� un objet Bundle, c'est ce qui va nous permetre d'envoyer des donn�es � l'autre Activity
    			Bundle objetbunble = new Bundle();
     
    			//On cr�� l'Intent qui va nous permettre d'afficher l'autre Activity
    			Intent intent = new Intent(ChoixMJ.this, EcranMJ.class);
    			
    			String pathCampagne = "/Fiches/"+UniversSpinner.getSelectedItem().toString()+"/"+campagneSpinner.getSelectedItem().toString();
    			
    			//Passage du path de la campagne
    			objetbunble.putString("pathCampagne", getFilesDir().getAbsolutePath() + pathCampagne);
    			
    			//Passage de l'univers choisi
    			objetbunble.putString("univers", UniversSpinner.getSelectedItem().toString());
    			
    			//On affecte � l'Intent le Bundle que l'on a cr��
    			intent.putExtras(objetbunble);
     
    			//On d�marre l'autre Activity
    			startActivityForResult(intent, 1);

            }
        });
        
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
