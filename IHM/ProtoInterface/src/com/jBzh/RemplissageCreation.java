package com.jBzh;

import java.util.ArrayList;

import com.jBzh.CreationNumericAdapter.CreationNumericAdapterListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class RemplissageCreation extends Activity implements CreationNumericAdapterListener{
	
	private static ArrayList<Numeric> listN = Numeric.getAListOfNumeric();
	private static CreationNumericAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  
	  setContentView(R.layout.remplissagecreation);

      CreationNumericAdapter adapter = new CreationNumericAdapter(this, listN);

      this.adapter = adapter;
     
      adapter.addListener(this);
      
      ListView list = (ListView)findViewById(R.id.list);
      
      list.setAdapter(adapter);
	  
      final Button buttonRetour = (Button) findViewById(R.id.retour);
      buttonRetour.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
          	
          	//On cr�� un objet Bundle, c'est ce qui va nous permetre d'envoyer des donn�es � l'autre Activity
  			Bundle objetbunble = new Bundle();
   
  			//On cr�� l'Intent qui va nous permettre d'afficher l'autre Activity
  			Intent intent = new Intent(RemplissageCreation.this, ListCreationNumeric.class);
   
  			//On affecte � l'Intent le Bundle que l'on a cr��
  			intent.putExtras(objetbunble);
   
  			//On d�marre l'autre Activity
  			startActivityForResult(intent, 1);

          }
      });
	  
	}

	@Override
	public void onClickMoins(Numeric item, int position) {
		item.decr();
		adapter.notifyDataSetChanged();
		System.out.println(item.getValeur());
	}

	@Override
	public void onClickPlus(Numeric item, int position) {
		item.incr();
		adapter.notifyDataSetChanged();		
		System.out.println(item.getValeur());
	}

	

}
