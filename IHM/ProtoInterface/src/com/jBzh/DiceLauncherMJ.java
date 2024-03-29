package com.jBzh;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import dice.Dice;
import dice.DiceType;

public class DiceLauncherMJ extends Activity {
	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
        
        setContentView(R.layout.dicelauncher);
        
        final Bundle objectbunble  = this.getIntent().getExtras();
        
        if(objectbunble.containsKey("diceType")){
        	// si le jet est deja parametrer on set les parametre directement
        	TextView rollType = (TextView) findViewById(R.id.rollTypeText);
        	rollType.setText(objectbunble.getString("type"));
        	
        	// on recupere le nombre de dés a lancer
        	TextView nbDice = (TextView) findViewById(R.id.nbDiceValue);
        	Integer newVal = objectbunble.getInt("nbDice");
        	nbDice.setText(String.valueOf(newVal));
   
        	// on recupere le type de dés
        	String diceTypeString = objectbunble.getString("diceType");
        	Spinner diceTypeSpinner = (Spinner) findViewById(R.id.diceTypeSpinner);
        	for(int i = 0 ; i < 10 ; i++){
        		diceTypeSpinner.setSelection(i);
        		if(diceTypeString.equalsIgnoreCase((String)diceTypeSpinner.getSelectedItem())){
        			break;
        		}
        	}
        }
                
        final Button buttonDicePlus = (Button) findViewById(R.id.nbDicePlus);
        buttonDicePlus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	TextView nbDice = (TextView) findViewById(R.id.nbDiceValue);
            	
            	Integer newVal = Integer.valueOf(nbDice.getText().toString()) + 1;
            	
            	nbDice.setText(String.valueOf(newVal));
            }
        });
        
        final Button buttonDiceMoin = (Button) findViewById(R.id.nbDiceMoins);
        buttonDiceMoin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	TextView nbDice = (TextView) findViewById(R.id.nbDiceValue);
            	
            	Integer newVal = Integer.valueOf(nbDice.getText().toString()) - 1;
            	
            	if(newVal >= 1){
            		nbDice.setText(String.valueOf(newVal));
            	}
            	
            }
        });
        
        final Builder builder = new AlertDialog.Builder(this);
	    final Button buttonLancer = (Button) findViewById(R.id.launchButton);
	    buttonLancer.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	Integer nbDice = Integer.valueOf((((TextView) findViewById(R.id.nbDiceValue)).getText().toString()));
	        	DiceType typeDice = DiceType.stringToDiceType(((Spinner) findViewById(R.id.diceTypeSpinner)).getSelectedItem().toString());
	        
	        	builder.setTitle("Jet");
		         
	        	builder.setMessage(Dice.roll(nbDice, typeDice).toString());
	        	builder.setPositiveButton("OK", null);
	        	builder.show();
	        	
	       
	        	
	        }
	    }); 
	    
	    final Button buttonRetour = (Button) findViewById(R.id.retourButton);
	      buttonRetour.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View v) {
	          	 
	  			//On cr�� l'Intent qui va nous permettre d'afficher l'autre Activity
	  			Intent intent = new Intent(DiceLauncherMJ.this, EcranMJ.class);
	   
	  			//On affecte � l'Intent le Bundle que l'on a cr��
	  			intent.putExtras(objectbunble);
	   
	  			//On d�marre l'autre Activity
	  			startActivityForResult(intent, 1);

	          }
	      });
	    
    }
}
