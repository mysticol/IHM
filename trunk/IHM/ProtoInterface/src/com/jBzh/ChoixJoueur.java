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

public class ChoixJoueur extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
				
		//on lui associe le layout afficahgequestionactivity.xml
    	setContentView(R.layout.choixjoueur);

    	final Spinner UniversSpinner = (Spinner) findViewById(R.id.UniversSpinner);
    	
    	final ArrayList<String> univers = new ArrayList<String>();
    	final ArrayList<String> campagnes = new ArrayList<String>();
    	
    	File universPath = new File(getFilesDir().getAbsolutePath() + "/Fiches");
    	
    	//Récupération des univers existant
    	for(File f : universPath.listFiles()){
    		univers.add(f.getName());
        }
    	
    	
        ArrayAdapter<CharSequence> adapterU = ArrayAdapter.createFromResource(
                this, R.array.univers_array, android.R.layout.simple_spinner_item);
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
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
        Spinner campagneSpinner = (Spinner) findViewById(R.id.CampagneSpinner);
        ArrayAdapter<CharSequence> adapterC = ArrayAdapter.createFromResource(
                this, R.array.campagne_array, android.R.layout.simple_spinner_item);
        adapterC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campagneSpinner.setAdapter(adapterC);

        final Button buttonRetour = (Button) findViewById(R.id.retour);
        buttonRetour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	//On crï¿½ï¿½ un objet Bundle, c'est ce qui va nous permetre d'envoyer des donnï¿½es ï¿½ l'autre Activity
    			Bundle objetbunble = new Bundle();
     
    			//On crï¿½ï¿½ l'Intent qui va nous permettre d'afficher l'autre Activity
    			Intent intent = new Intent(ChoixJoueur.this, ProtoInterface.class);
     
    			//On affecte ï¿½ l'Intent le Bundle que l'on a crï¿½ï¿½
    			intent.putExtras(objetbunble);
     
    			//On dï¿½marre l'autre Activity
    			startActivityForResult(intent, 1);

            }
        });
	}
	
	

}
