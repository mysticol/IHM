package com.jBzh;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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
	}
	
	

}
