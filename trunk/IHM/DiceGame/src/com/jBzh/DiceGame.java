package com.jBzh;

import dice.Dice;
import dice.DiceType;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class DiceGame extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        
        
        
        
        setContentView(R.layout.main);
    
        final Button buttonPlus = (Button) findViewById(R.id.plus);
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	TextView nbDice = (TextView) findViewById(R.id.nbDiceValue);
            	
            	Integer newVal = Integer.valueOf(nbDice.getText().toString()) + 1;
            	
            	nbDice.setText(String.valueOf(newVal));
            }
        });
        
        final Button buttonMoin = (Button) findViewById(R.id.moins);
        buttonMoin.setOnClickListener(new View.OnClickListener() {
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
    
    }
}