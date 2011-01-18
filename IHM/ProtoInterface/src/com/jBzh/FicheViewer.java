package com.jBzh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dice.Dice;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ExpandableListActivity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleExpandableListAdapter;
import bean.Competence;
import bean.Equipement;
import bean.Fiche;
import bean.Pouvoir;
import bean.vie.Classic;

public class FicheViewer extends ExpandableListActivity {
	
	Fiche fiche = new Fiche();
	ArrayList<ResumNumeric> list = new ArrayList<ResumNumeric>();
	private SeparatedListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    
	    setContentView(R.layout.ficheviewer);
        
	    final Bundle objectbunble  = this.getIntent().getExtras();
	    fiche = (Fiche) objectbunble.getSerializable("fiche");
	   
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
	
		Button buttonFicheToRoll = (Button) findViewById(R.id.FicheToRoll);
	    buttonFicheToRoll.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View v) {
	          	
	          	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
	  			//Bundle objetbunble = new Bundle();
	   
	  			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
	  			Intent intent = new Intent(FicheViewer.this, ChoixJet.class);
	   
	  			//On affecte à l'Intent le Bundle que l'on a créé
	  			intent.putExtras(objectbunble);
	   
	  			//On démarre l'autre Activity
	  			startActivityForResult(intent, 1);

	          }
	      });
	    
	    final Button buttonFicheToJauge = (Button) findViewById(R.id.FicheToJauge);
	    buttonFicheToJauge.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View v) {
	          	
	          	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
	  			//Bundle objetbunble = new Bundle();
	   
	  			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
	  			Intent intent = new Intent(FicheViewer.this, Jauge.class);
	   
	  			//On affecte à l'Intent le Bundle que l'on a créé
	  			intent.putExtras(objectbunble);
	   
	  			//On démarre l'autre Activity
	  			startActivityForResult(intent, 1);

	          }
	      });
	    	  
	    
	    final DialogInterface.OnClickListener Quitte = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
	  			Intent intent = new Intent(FicheViewer.this, ProtoInterface.class);
	   
	  			//On affecte à l'Intent le Bundle que l'on a créé
	  			intent.putExtras(new Bundle());
	   
	  			//On démarre l'autre Activity
	  			startActivityForResult(intent, 1);
				
			}
	      };
	    
	    final Builder builder = new AlertDialog.Builder(this);
	    final Button buttonQuitteJoueur = (Button) findViewById(R.id.QuitteJoueur);
	    buttonQuitteJoueur.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View v) {
	          	
	        	  builder.setTitle("Quitter");
			         
	        	  builder.setMessage("Retour au menu principal : ");
	        	  builder.setPositiveButton("OUI", Quitte);
	        	  builder.setNegativeButton("NON", null);
	        	  builder.show();
	        	  
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

	  private List createChildList() {
		
		ArrayList<ArrayList<HashMap<String,String>>> result = new ArrayList<ArrayList<HashMap<String,String>>>();
		ArrayList<HashMap<String,String>> secList = new ArrayList<HashMap<String,String>>();
		
		//Infos Perso
		//System.out.println(fiche.getNom());
		//System.out.println(fiche.getInfos().entrySet().toString());
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
		
		ArrayList<HashMap<String,String>> secList5 = new ArrayList<HashMap<String,String>>();
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
		result.add( secList6 );
		
		ArrayList<HashMap<String,String>> secList7 = new ArrayList<HashMap<String,String>>();
		//Equipements
		for(Equipement e : fiche.getEquipements()){
			HashMap<String,String> child2 = new HashMap<String,String>();
			child2.put( "nom", e.getNom() );
			child2.put( "valeur", e.getDescription());
			secList7.add( child2 );
		}			
		result.add( secList7 );		
		
		
		return result;
	  }
}
