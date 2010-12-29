package com.jBzh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SelectElemJet extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  
	  ListView lv1;
	  Bundle objetbunble  = this.getIntent().getExtras();
	  
	  // TODO recup la liste des type de jet possible !!!
	  String lv_arr[]={"force","intel","charisme"};

	  setContentView(R.layout.selectelemjet);
	  	  
	  ((TextView)findViewById(R.id.affichejet)).setText(objetbunble.getString("type"));
	  
	  lv1=(ListView)findViewById(R.id.listelemjet);
	  // By using setAdpater method in listview we an add string array in list.
	  lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , lv_arr));

	  lv1.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view,
	        int position, long id) {

	    	//On cr�� un objet Bundle, c'est ce qui va nous permetre d'envoyer des donn�es � l'autre Activity
  			Bundle objetbunble = new Bundle();
   
  			//On cr�� l'Intent qui va nous permettre d'afficher l'autre Activity
  			Intent intent = new Intent(SelectElemJet.this, getParent().getClass());
   
  			//On affecte � l'Intent le Bundle que l'on a cr��
  			intent.putExtras(objetbunble);
   
  			//On d�marre l'autre Activity
  			startActivityForResult(intent, 1);

	    }
	  });
	  
      final Button buttonRetour = (Button) findViewById(R.id.retour);
      buttonRetour.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
          	
          	//On cr�� un objet Bundle, c'est ce qui va nous permetre d'envoyer des donn�es � l'autre Activity
  			Bundle objetbunble = new Bundle();
   
  			//On cr�� l'Intent qui va nous permettre d'afficher l'autre Activity
  			Intent intent = new Intent(SelectElemJet.this, getParent().getClass());
   
  			//On affecte � l'Intent le Bundle que l'on a cr��
  			intent.putExtras(objetbunble);
   
  			//On d�marre l'autre Activity
  			startActivityForResult(intent, 1);

          }
      });
	
	}	
}
