package com.jBzh;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import dice.Dice;
import dice.DiceType;

public class DiceLauncher extends Activity {
	/** Called when the activity is first created. */
   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //setContentView(R.layout.main);
    
        System.out.println("<<<<<<<<<<<<<<<<<<<< 1");
        
        final Button buttonPlus = (Button) findViewById(R.id.nbDicePlus);
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	TextView nbDice = (TextView) findViewById(R.id.nbDiceValue);
            	
            	Integer newVal = Integer.valueOf(nbDice.getText().toString()) + 1;
            	
            	nbDice.setText(String.valueOf(newVal));
            }
        });
        
        System.out.println("<<<<<<<<<<<<<<<<<<<< 2");
        
        final Button buttonMoin = (Button) findViewById(R.id.nbDiceMoins);
        buttonMoin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	TextView nbDice = (TextView) findViewById(R.id.nbDiceValue);
            	
            	Integer newVal = Integer.valueOf(nbDice.getText().toString()) - 1;
            	
            	if(newVal >= 1){
            		nbDice.setText(String.valueOf(newVal));
            	}
            	
            }
        });
        
        System.out.println("<<<<<<<<<<<<<<<<<<<< 3");
        
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
	    System.out.println("<<<<<<<<<<<<<<<<<<<< 4");
    }
}
