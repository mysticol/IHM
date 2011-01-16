package com.jBzh;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import bean.Caracteristique;
import bean.Fiche;

import com.jBzh.CreationJaugeAdapter.CreationJaugeAdapterListener;

public class Jauge extends Activity implements CreationJaugeAdapterListener {

	ArrayList<Caracteristique> listN = new ArrayList<Caracteristique>();
	private CreationJaugeAdapter adapter;	
	private Fiche fiche;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	    setContentView(R.layout.jaugemodifier);
        	    
	    final Bundle objectbunble  = this.getIntent().getExtras();
	    fiche = (Fiche) objectbunble.getSerializable("fiche");
	    final ListView listJauge = (ListView)findViewById(R.id.listJauge);
	    
	    // creation de l'adapter
	    CreationJaugeAdapter adapter = new CreationJaugeAdapter(this, fiche.getJauges());
	    this.adapter = adapter;
	    adapter.addListener(this);
	    listJauge.setAdapter(this.adapter);

	    final Button buttonJaugeToRoll = (Button) findViewById(R.id.jaugeToRoll);
	    buttonJaugeToRoll.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View v) {
	          	
	          	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
	  			//Bundle objetbunble = new Bundle();
	   
	  			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
	  			Intent intent = new Intent(Jauge.this, ChoixJet.class);
	   
	  			//On affecte à l'Intent le Bundle que l'on a créé
	  			intent.putExtras(objectbunble);
	   
	  			//On démarre l'autre Activity
	  			startActivityForResult(intent, 1);

	          }
	      });
	    
	    final Button buttonJaugeToFiche = (Button) findViewById(R.id.jaugeToFiche);
	    buttonJaugeToFiche.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View v) {
	          	
	          	//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
	  			//Bundle objetbunble = new Bundle();
	   
	  			//On créé l'Intent qui va nous permettre d'afficher l'autre Activity
	  			Intent intent = new Intent(Jauge.this, FicheViewer.class);
	   
	  			//On affecte à l'Intent le Bundle que l'on a créé
	  			intent.putExtras(objectbunble);
	   
	  			//On démarre l'autre Activity
	  			startActivityForResult(intent, 1);

	          }
	      });
	    	    
	}

	@Override
	public void onClickMoins(Caracteristique item, int position) {
		
		EditText changeVal = (EditText) findViewById(R.id.ValChangeJauge);
		
		item.setValeur(item.getValeur() - Integer.valueOf(changeVal.getText().toString()));
		
		adapter.notifyDataSetChanged();
		
	}

	@Override
	public void onClickPlus(Caracteristique item, int position) {
		
		EditText changeVal = (EditText) findViewById(R.id.ValChangeJauge);
		
		item.setValeur(item.getValeur() + Integer.valueOf(changeVal.getText().toString()));
		
		adapter.notifyDataSetChanged();
		
	}	
}
