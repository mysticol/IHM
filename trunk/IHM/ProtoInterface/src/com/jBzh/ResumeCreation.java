package com.jBzh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleExpandableListAdapter;
import bean.Competence;
import bean.Fiche;

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
		System.out.println(fiche.getNom());
		System.out.println(fiche.getInfos().entrySet().toString());
		for(String c : fiche.getInfos().keySet()){
			System.out.println(c);
			System.out.println(fiche.getInfos().get(c));
			HashMap<String,String> child = new HashMap<String,String>();
			child.put( "nom", c );
			child.put( "valeur", fiche.getInfos().get(c) );
			secList.add( child );
		}
		result.add( secList );
		
		ArrayList<HashMap<String,String>> secList2 = new ArrayList<HashMap<String,String>>();
		//Caractéristiques Principales
		for(String c : fiche.getCaracteristiquesPrincipales().keySet()){
			System.out.println(fiche.getCaracteristiquesPrincipales().get(c).getValeur().toString());
			HashMap<String,String> child = new HashMap<String,String>();
			child.put( "nom", c );
			child.put( "valeur", fiche.getCaracteristiquesPrincipales().get(c).getValeur().toString());
			secList2.add( child );
		  }
		result.add( secList2 );
		
		ArrayList<HashMap<String,String>> secList3 = new ArrayList<HashMap<String,String>>();
		//Compétences
		for(Competence c : fiche.getListCompetences()){
			HashMap<String,String> child = new HashMap<String,String>();
			child.put( "nom", c.getNom() );
			child.put( "valeur", c.getValeur().toString());
			secList3.add( child );
		  }
		result.add( secList3 );
		
		ArrayList<HashMap<String,String>> secList4 = new ArrayList<HashMap<String,String>>();
		//Caractéristiques Secondaires
		for(String c : fiche.getCaracteristiquesSecondaire().keySet()){
			HashMap<String,String> child = new HashMap<String,String>();
			child.put( "nom", c );
			child.put( "valeur", fiche.getCaracteristiquesSecondaire().get(c).getValeur().toString());
			secList4.add( child );
		  }
		result.add( secList4 );
		
		/*ArrayList<HashMap<String,String>> secList5 = new ArrayList<HashMap<String,String>>();
		//Vie
			HashMap<String,String> child = new HashMap<String,String>();
			child.put( "nom", "Vie" );
			child.put( "valeur", ((Classic)fiche.getBarreDeVie()).getActuel().toString());
			secList5.add( child );
			
		result.add( secList5 );
		
		ArrayList<HashMap<String,String>> secList6 = new ArrayList<HashMap<String,String>>();
		//Pouvoirs
		for(Pouvoir p : fiche.getPouvoirs()){
			HashMap<String,String> child2 = new HashMap<String,String>();
			child2.put( "nom", p.getNom() );
			child2.put( "valeur", p.getDescription());
			secList6.add( child2 );
		}			
		result.add( secList6 );*/
		
		return result;
	  }
}


