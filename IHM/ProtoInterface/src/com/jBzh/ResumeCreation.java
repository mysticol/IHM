package com.jBzh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bean.Fiche;
import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;

public class ResumeCreation extends ExpandableListActivity {
	
	Fiche fiche = new Fiche();
	ArrayList<ResumNumeric> list = new ArrayList<ResumNumeric>();
	private SeparatedListAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  
	//R�cup�ration de la fiche
	  Bundle objetbunble  = this.getIntent().getExtras();
	  fiche = (Fiche)(objetbunble.getSerializable("fiche"));
	  
	  setContentView(R.layout.resumecreation);
	  
	  
	  SimpleExpandableListAdapter expListAdapter =
			new SimpleExpandableListAdapter(
				this,
				createGroupList(),	// groupData describes the first-level entries
				R.layout.group_row,	// Layout for the first-level entries
				new String[] { "categorie" },	// Key in the groupData maps to display
				new int[] { R.id.categorie },		// Data under "categorie" key goes into this TextView
				createChildList(),	// childData describes second-level entries
				R.layout.child_row,	// Layout for second-level entries
				new String[] { "nom", "valeur" }	,// Keys in childData maps to display
				new int[] { R.id.nom, R.id.valeur}	// Data under the keys above go into these TextViews
			);
		setListAdapter( expListAdapter );
	  

      final Button buttonRetour = (Button) findViewById(R.id.retourMenu);
      buttonRetour.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
          	
          	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
  			Bundle objetbunble = new Bundle();
   
  			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
  			Intent intent = new Intent(ResumeCreation.this, ProtoInterface.class);
  			
  			//On affecte à l'Intent le Bundle que l'on a créé
  			intent.putExtras(objetbunble);
   
  			//On démarre l'autre Activity
  			startActivityForResult(intent, 1);

          }
      });
	  
	}
	
	

	/**
	  * Creates the group list out of the colors[] array according to
	  * the structure required by SimpleExpandableListAdapter. The resulting
	  * List contains Maps. Each Map contains one entry with key "colorName" and
	  * value of an entry in the colors[] array.
	  */
		private ArrayList<HashMap<String,String>> createGroupList() {
		  String[] colors = {"Personnage","Caracteristiques Principales","Compétences","Caractéristiques Secondaires","Vie","Pouvoirs","Equipement"};
		  ArrayList<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();
		  for( int i = 0 ; i < colors.length ; ++i ) {
			HashMap<String,String> m = new HashMap<String,String>();
		    m.put( "categorie",colors[i] );
			result.add( m );
		  }
		  return result;
	    }

	/**
	  * Creates the child list out of the shades[] array according to the
	  * structure required by SimpleExpandableListAdapter. The resulting List
	  * contains one list for each group. Each such second-level group contains
	  * Maps. Each such Map contains two keys: "shadeName" is the name of the
	  * shade and "rgb" is the RGB value for the shade.
	  */
	  private List createChildList() {
		
		ArrayList<ArrayList<HashMap<String,String>>> result = new ArrayList<ArrayList<HashMap<String,String>>>();
		ArrayList<HashMap<String,String>> secList = new ArrayList<HashMap<String,String>>();
		
		//Infos Perso
		for(String c : fiche.getInfos().keySet()){
			HashMap<String,String> child = new HashMap<String,String>();
			child.put( "nom", c );
			child.put( "valeur", fiche.getInfos().get(c) );
			secList.add( child );
		}
		result.add( secList );
		
		ArrayList<HashMap<String,String>> secList2 = new ArrayList<HashMap<String,String>>();
		//Caract�ristiques Principales
		for(String c : fiche.getCaracteristiquesPrincipales().keySet()){
			HashMap<String,String> child = new HashMap<String,String>();
			child.put( "nom", c );
			child.put( "valeur", fiche.getCaracteristiquesPrincipales().get(c).getValeur().toString());
			secList2.add( child );
		  }
		result.add( secList2 );
		
		return result;
	  }
}


