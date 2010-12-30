package com.jBzh;

import bean.Systeme;
import dice.Dice;
import dice.DiceType;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ChoixJet extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  	  	  	  
	  ListView lv1;
	  
	  final Bundle objetbunble  = this.getIntent().getExtras();
	  
	  System.out.println("<<<<<<<<<< <<<<<<<<<<<<");	  
	  Systeme systeme = (Systeme) objetbunble.getSerializable("systeme");
	  System.out.println("<<<<<<<<<< <<<<<<<<<<<<");	  
	  
	  //Systeme systeme = test.createSysteme();
	  
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

	    	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
  			Bundle objetbunble = new Bundle();
   
  			objetbunble.putString("type", lv_arr[position]);
  			
  			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
  			Intent intent = new Intent(ChoixJet.this, SelectElemJet.class);
   
  			//On affecte à l'Intent le Bundle que l'on a créé
  			intent.putExtras(objetbunble);
   
  			//On démarre l'autre Activity
  			startActivityForResult(intent, 1);

	    }
	  });
	  
      final Button buttonRetour = (Button) findViewById(R.id.retour);
      buttonRetour.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
          	
          	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
  			//Bundle objetbunble = new Bundle();
   
  			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
  			Intent intent = new Intent(ChoixJet.this, /*getParent().getClass()*/ ProtoInterface.class);
   
  			//On affecte à l'Intent le Bundle que l'on a créé
  			intent.putExtras(objetbunble);
   
  			//On démarre l'autre Activity
  			startActivityForResult(intent, 1);

          }
      });
      
      final Builder builder = new AlertDialog.Builder(this);
      final Button buttonLibre = (Button) findViewById(R.id.libre);
      buttonLibre.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
        	  Dice d = new Dice();
        	  
        	  builder.setTitle("Jet");
        		         
        	  builder.setMessage((d.roll(5, DiceType.D10)).toString());
        	  builder.setPositiveButton("OK", null);
        	  builder.show();

          }
      });
      
      
	}	
}
