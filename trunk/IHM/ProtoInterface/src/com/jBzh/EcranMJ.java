package com.jBzh;

import java.io.File;
import java.util.LinkedList;

import parseur.ParseurFiche;
import parseur.ParseurModele;
import bean.Fiche;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;

public class EcranMJ extends Activity {
	
	private Fiche fiche = new Fiche();
	private ParseurFiche pFiche = new ParseurFiche();
	private String[] lv_arr = {};
	private String univers;
	private static String pathCampagne = "";
	private ListView lv1;
	private EcranMJ instance;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		// On recupere les arguments
		Bundle objetbunble  = this.getIntent().getExtras();
		
		if(objetbunble.getString("univers")!=null){
			univers = objetbunble.getString("univers");
		}
		
		if(objetbunble.getString("pathCampagne")!=null){
			pathCampagne = objetbunble.getString("pathCampagne");
		}		

		
		setContentView(R.layout.ecranmj);
		lv1=(ListView)findViewById(R.id.list);
		
		lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , lv_arr));
		
		instance = this;
		
//		// By using setAdpater method in listview we an add string array in list.
//		lv1.setAdapter(new ArrayAdapter<String>(instance,android.R.layout.simple_list_item_1 , lv_arr));
//		
//		lv1.setOnItemClickListener(new OnItemClickListener() {
//				public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//							    	
//					//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
//					Bundle objetbunble = new Bundle();
//			   
//					//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
//			  		Intent intent = new Intent(EcranMj.this, FicheViewer.class);
//			   
//			  		//Passage de la fiche à RemplissageCreation
//					objetbunble.putSerializable("fiche", fiche);
//					objetbunble.putString("categorie", lv_arr[position]);
//			
//			
//					//On affecte à l'Intent le Bundle que l'on a créé
//					intent.putExtras(objetbunble);
//			   
//			  		//On démarre l'autre Activity
//					startActivityForResult(intent, 1);
//
//				}
//		  });

		// Bouton quitter
		final DialogInterface.OnClickListener Quitte = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
		  			Intent intent = new Intent(EcranMJ.this, ProtoInterface.class);
		   
	  			//On affecte à l'Intent le Bundle que l'on a créé
	  			intent.putExtras(new Bundle());
	   
	  			//On démarre l'autre Activity
				startActivityForResult(intent, 1);
				
			}
		};
		    
	    final Builder builder = new AlertDialog.Builder(this);
	    final Button buttonQuitteJoueur = (Button) findViewById(R.id.quitter);
	    buttonQuitteJoueur.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View v) {
	          	
	        	  builder.setTitle("Quitter");
			         
	        	  builder.setMessage("Retour au menu principal : ");
	        	  builder.setPositiveButton("OUI", Quitte);
	        	  builder.setNegativeButton("NON", null);
	        	  builder.show();
	        	  
	          }
	    });
	    
	    // Bouton Jet libre
	    final Button buttonLibre = (Button) findViewById(R.id.jetLibre);
	    buttonLibre.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {	        	
	        	
	        	//TODO rajouter les types de des en fonction de l'univers choisi
	        	
	      	  	//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
	      	  	Intent intent = new Intent(EcranMJ.this, DiceLauncherMJ.class);
	        	  
	      	  	//On affecte à l'Intent le Bundle que l'on a créé
	      	  	intent.putExtras(new Bundle());
	        	  
	      	  	//On démarre l'autre Activity
	      	  	startActivityForResult(intent, 1);
	        	  
	        }
	    });	    
		
		// Bouton PNJ
	    final Button buttonPNJ = (Button) findViewById(R.id.pnj);
	    buttonPNJ.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {	        	
	        	
	        	// On recupere la liste des pnjs de la campagne
	        	File dirPnj = new File(pathCampagne + "/PNJ");
	        	
	        	System.out.println("path : " + pathCampagne + "/PNJ");
	        	
	        	lv_arr = new String[dirPnj.listFiles().length];

	        	int i = 0;
	        	for(File f : dirPnj.listFiles()){
	        		System.out.println(f.getName());
	        		lv_arr[i] = f.getName().substring(0, f.getName().length()-4);
	        		i++;
	        	}
	        	
	    		// By using setAdpater method in listview we an add string array in list.
	    		lv1.setAdapter(new ArrayAdapter<String>(instance,android.R.layout.simple_list_item_1, lv_arr));
	    		
	    		lv1.setOnItemClickListener(new OnItemClickListener() {
    				public void onItemClick(AdapterView<?> parent, View view,
    					int position, long id) {
    							    	
    					//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
    					Bundle objetbunble = new Bundle();
    			   
    					//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
    			  		Intent intent = new Intent(EcranMJ.this, FicheViewerMJ.class);
    			   
    			  		// On recupere la fiche voulue
    	    			File tmp = new File(pathCampagne + "/PNJ/" + lv_arr[position] + ".xml");
    	    			fiche = pFiche.parse(tmp);
    			  		
    			  		//Passage de la fiche à RemplissageCreation
    					objetbunble.putSerializable("fiche", fiche);
    					    			
    					//On affecte à l'Intent le Bundle que l'on a créé
    					intent.putExtras(objetbunble);
    			   
    			  		//On démarre l'autre Activity
    					startActivityForResult(intent, 1);
    
    				}
	    		});
	        	
	        	
	        }
	    });		    

		// Bouton PNJ
	    final Button buttonPJ = (Button) findViewById(R.id.pj);
	    buttonPJ.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {	        	
	        		        	
	        	// On recupere la liste des pnjs de la campagne
	        	File dirPnj = new File(pathCampagne + "/PJ");
	        	
	        	System.out.println("path : " + pathCampagne + "/PNJ");	        	
	        	
	        	lv_arr = new String[dirPnj.listFiles().length];

	        	int i = 0;
	        	for(File f : dirPnj.listFiles()){
	        		System.out.println(f.getName());
	        		lv_arr[i] = f.getName().substring(0, f.getName().length()-4);
	        		i++;
	        	}
	        	
	    		// By using setAdpater method in listview we an add string array in list.
	    		lv1.setAdapter(new ArrayAdapter<String>(instance,android.R.layout.simple_list_item_1, lv_arr));
	    		
	    		lv1.setOnItemClickListener(new OnItemClickListener() {
    				public void onItemClick(AdapterView<?> parent, View view,
    					int position, long id) {
    							    	
    					//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
    					Bundle objetbunble = new Bundle();
    			   
    					//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
    			  		Intent intent = new Intent(EcranMJ.this, FicheViewerMJ.class);
    			   
    			  		// On recupere la fiche voulue
    	    			File tmp = new File(pathCampagne + "/PJ/" + lv_arr[position] + ".xml");
    	    			fiche = pFiche.parse(tmp);
    			  		    	    			
    			  		//Passage de la fiche à RemplissageCreation
    					objetbunble.putSerializable("fiche", fiche);
    					    			
    					//On affecte à l'Intent le Bundle que l'on a créé
    					intent.putExtras(objetbunble);
    			   
    			  		//On démarre l'autre Activity
    					startActivityForResult(intent, 1);
    
    				}
	    		});	        	
	        	
	        }
	    });		    
	    
	    
		
	}
		

}
