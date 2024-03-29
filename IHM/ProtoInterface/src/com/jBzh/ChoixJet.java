package com.jBzh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import bean.Systeme;

public class ChoixJet extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  	  	  	  
	  ListView lv1;
	  
	  final Bundle objetbunble  = this.getIntent().getExtras();
	  	  
	  //Systeme systeme = (Systeme) objetbunble.getSerializable("systeme");  
	  Systeme systeme = test.createSysteme();
	  objetbunble.putSerializable("systeme", systeme);
	  // recuperation de la liste des types de jets	  
	  final String lv_arr[]= new String[systeme.getListRolls().size()];
	  int i = 0;
	  for(String r : systeme.getListRolls()){
		  lv_arr[i]=r;
		  i++;
	  }

	  setContentView(R.layout.choixjet);
	  lv1=(ListView)findViewById(R.id.listtypejet);
	  // By using setAdpater method in listview we an add string array in list.
	  lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , lv_arr));

	  lv1.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view,
	        int position, long id) {

	    	//On cr�� un objet Bundle, c'est ce qui va nous permetre d'envoyer des donn�es � l'autre Activity
  			//Bundle objetbunble = new Bundle();
	   
	    	objetbunble.putInt("nbDice", 0);
  			objetbunble.putString("type", lv_arr[position] + " : ");
  			objetbunble.putInt("numElem",0);
  			
  			//On cr�� l'Intent qui va nous permettre d'afficher l'autre Activity
  			Intent intent = new Intent(ChoixJet.this, SelectElemJet.class);
   
  			//On affecte � l'Intent le Bundle que l'on a cr��
  			intent.putExtras(objetbunble);
   
  			//On d�marre l'autre Activity
  			startActivityForResult(intent, 1);

	    }
	  });

      final Button buttonRollToJauge = (Button) findViewById(R.id.RollToJauge);
      buttonRollToJauge.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
          	
          	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
  			//Bundle objetbunble = new Bundle();
   
  			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
  			Intent intent = new Intent(ChoixJet.this, /*getParent().getClass()*/ Jauge.class);
   
  			//On affecte à l'Intent le Bundle que l'on a créé
  			intent.putExtras(objetbunble);
   
  			//On démarre l'autre Activity
  			startActivityForResult(intent, 1);

          }
      });
      
      final Button buttonRollToFiche = (Button) findViewById(R.id.RollToFiche);
      buttonRollToFiche.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
          	
          	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
  			//Bundle objetbunble = new Bundle();
   
  			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
  			Intent intent = new Intent(ChoixJet.this, FicheViewer.class);
   
  			//On affecte à l'Intent le Bundle que l'on a créé
  			intent.putExtras(objetbunble);
   
  			//On démarre l'autre Activity
  			startActivityForResult(intent, 1);

          }
      });
 
      final Button buttonLibre = (Button) findViewById(R.id.libre);
      buttonLibre.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
        	  
        	  if(objetbunble.containsKey("type")){
        		  objetbunble.remove("type");
        		  objetbunble.remove("nbDice");
        	  }
        	  
        	  //On créé l'Intent qui va nous permettre d'afficher l'autre Activity
        	  Intent intent = new Intent(ChoixJet.this, DiceLauncher.class);
        	  
        	  //On affecte à l'Intent le Bundle que l'on a créé
        	  intent.putExtras(objetbunble);
        	  
        	  //On démarre l'autre Activity
        	  startActivityForResult(intent, 1);
        	  
          }
      });
      
      
	}	
}
