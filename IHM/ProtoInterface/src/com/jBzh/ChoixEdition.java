package com.jBzh;

import java.io.File;
import java.util.ArrayList;

import parseur.ParseurFiche;

import bean.Fiche;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class ChoixEdition extends Activity{
	
	private String typePerso="PJ";
	private Fiche fiche;
	private ParseurFiche pFiche = new ParseurFiche();
	private ChoixEdition instance;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
				
		//on lui associe le layout afficahgequestionactivity.xml
    	setContentView(R.layout.choixedition);

    	final Spinner UniversSpinner = (Spinner) findViewById(R.id.universspinner);
    	
    	final ArrayList<String> univers = new ArrayList<String>();
    	final ArrayList<String> campagnes = new ArrayList<String>();
    	final ArrayList<String> persos = new ArrayList<String>();
    	
    	this.instance = this;
    	
    	File universPath = new File(getFilesDir().getAbsolutePath() + "/Fiches");
    	
    	//R�cup�ration des univers existant
    	for(File f : universPath.listFiles()){
    		univers.add(f.getName());
        }
    	
    	
    	ArrayAdapter<String> adapterU = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, univers);
        adapterU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UniversSpinner.setAdapter(adapterU); 

        String universChoisi = UniversSpinner.getSelectedItem().toString();
    	//R�cup�ration des campagnes existantes
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
        
        final Spinner campagneSpinner = (Spinner) findViewById(R.id.CampagneSpinner);
        ArrayAdapter<String> adapterC = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, campagnes);
        adapterC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campagneSpinner.setAdapter(adapterC);
        
        
        OnClickListener radio_listener = new OnClickListener() {
            public void onClick(View v) {
                RadioButton rb = (RadioButton) v;
                typePerso = rb.getText().toString();
                
				persos.clear();
				File path = new File(getFilesDir().getAbsolutePath() + "/Fiches/"+UniversSpinner.getSelectedItem().toString()+"/"+campagneSpinner.getSelectedItem().toString()+"/"+getTypePerso()+"/");
				for(File f : path.listFiles()){
	        		persos.add(f.getName().substring(0, f.getName().length()-4));
				}
				
		        final Spinner persoSpinner = (Spinner) findViewById(R.id.persoSpinner);
		        ArrayAdapter<String> adapterP = new ArrayAdapter<String>(instance, android.R.layout.simple_spinner_item, persos);
		        adapterP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		        persoSpinner.setAdapter(adapterP);
                
            }
        };
        
        final RadioButton radio_pj = (RadioButton) findViewById(R.id.pj);
        final RadioButton radio_pnj = (RadioButton) findViewById(R.id.pnj);
        radio_pj.setChecked(true);
        radio_pj.setOnClickListener(radio_listener);
        radio_pnj.setOnClickListener(radio_listener);        
        
        
        
        String campagneChoisie = campagneSpinner.getSelectedItem().toString();
    	//R�cup�ration des persos existants
        File persoPath = new File(getFilesDir().getAbsolutePath() + "/Fiches/"+universChoisi+"/"+campagneChoisie+"/"+getTypePerso()+"/");
        for(File f : persoPath.listFiles()){
        		persos.add(f.getName().substring(0, f.getName().length()-4));
        }

        campagneSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				persos.clear();
				File path = new File(getFilesDir().getAbsolutePath() + "/Fiches/"+UniversSpinner.getSelectedItem().toString()+"/"+campagneSpinner.getSelectedItem().toString()+"/"+getTypePerso()+"/");
				for(File f : path.listFiles()){
	        		persos.add(f.getName().substring(0, f.getName().length()-4));
				}
				
		        final Spinner persoSpinner = (Spinner) findViewById(R.id.persoSpinner);
		        ArrayAdapter<String> adapterP = new ArrayAdapter<String>(instance, android.R.layout.simple_spinner_item, persos);
		        adapterP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		        persoSpinner.setAdapter(adapterP);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        	
		});
        
        final Spinner persoSpinner = (Spinner) findViewById(R.id.persoSpinner);
        ArrayAdapter<String> adapterP = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, persos);
        adapterP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        persoSpinner.setAdapter(adapterP);

        final Button buttonRetour = (Button) findViewById(R.id.retour);
        buttonRetour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	//On cr�� un objet Bundle, c'est ce qui va nous permetre d'envoyer des donn�es � l'autre Activity
    			Bundle objetbunble = new Bundle();
     
    			//On cr�� l'Intent qui va nous permettre d'afficher l'autre Activity
    			Intent intent = new Intent(ChoixEdition.this, ProtoInterface.class);
     
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
    			Intent intent = new Intent(ChoixEdition.this, ListEditionNumeric.class);
    			
    			File tmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/"+UniversSpinner.getSelectedItem().toString()+"/"+campagneSpinner.getSelectedItem().toString()+"/"+getTypePerso()+"/"+persoSpinner.getSelectedItem().toString()+".xml");
    			fiche = pFiche.parse(tmp);
    			
    			System.out.println("Barre de vie apres chargement (Edition) : " + fiche.getBarreDeVie().getActuel());
    			
    			//Passage de l'univers � ListCreationNumeric
    			objetbunble.putString("univers", UniversSpinner.getSelectedItem().toString());
    			objetbunble.putString("typePerso", typePerso);
    			objetbunble.putSerializable("fiche", fiche);
    			
    			//On affecte � l'Intent le Bundle que l'on a cr��
    			intent.putExtras(objetbunble);
     
    			//On d�marre l'autre Activity
    			startActivityForResult(intent, 1);

            }
        });
	}

	public String getTypePerso() {
		return typePerso;
	}

	public void setTypePerso(String typePerso) {
		this.typePerso = typePerso;
	}
	
	

}
