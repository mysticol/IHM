package com.jBzh;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RemplissageNumeric extends ListActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);

	  //on lui associe le layout afficahgequestionactivity.xml
  	  setContentView(R.layout.creation);
	  
	  String[] countries = new String[] {"Afghanistan", "Albania", "Algeria", "American Samoa"};
	  
	  setListAdapter(new ArrayAdapter<String>(this, R.layout.creation, countries));

	  ListView lv = getListView();
	  lv.setTextFilterEnabled(true);

	  lv.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view,
	        int position, long id) {
	      // When clicked, show a toast with the TextView text
	      /*Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
	          Toast.LENGTH_SHORT).show();*/
	    }
	  });
	  
      /*final Button buttonRetour = (Button) findViewById(R.id.retour);
      buttonRetour.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
          	
          	//On cr�� un objet Bundle, c'est ce qui va nous permetre d'envoyer des donn�es � l'autre Activity
  			Bundle objetbunble = new Bundle();
   
  			//On cr�� l'Intent qui va nous permettre d'afficher l'autre Activity
  			Intent intent = new Intent(RemplissageNumeric.this, ChoixCreation.class);
   
  			//On affecte � l'Intent le Bundle que l'on a cr��
  			intent.putExtras(objetbunble);
   
  			//On d�marre l'autre Activity
  			startActivityForResult(intent, 1);

          }
      });*/
      
      /*final Button buttonValider = (Button) findViewById(R.id.validerCreation);
      buttonValider.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
          	
          	//On cr�� un objet Bundle, c'est ce qui va nous permetre d'envoyer des donn�es � l'autre Activity
  			Bundle objetbunble = new Bundle();
   
  			//On cr�� l'Intent qui va nous permettre d'afficher l'autre Activity
  			Intent intent = new Intent(Creation.this, CreationParam.class);
   
  			//On affecte � l'Intent le Bundle que l'on a cr��
  			intent.putExtras(objetbunble);
   
  			//On d�marre l'autre Activity
  			startActivityForResult(intent, 1);
          	
          }
      });*/
      
	}
	
	
	
	
	
	
	
	
	
	

}
