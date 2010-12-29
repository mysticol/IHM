package com.jBzh;

import java.util.ArrayList;

import com.jBzh.CreationNumericAdapter.CreationNumericAdapterListener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class RemplissageCreation extends Activity implements CreationNumericAdapterListener{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  
	  setContentView(R.layout.remplissagecreation);
	  
	  //liste de Numeric
	  ArrayList<Numeric> listN = new ArrayList<Numeric>();
	  
	  listN.add(new Numeric("Force",0));
	  listN.add(new Numeric("Agilite",0));
	  
	  
      CreationNumericAdapter adapter = new CreationNumericAdapter(this, listN);
     
      adapter.addListener(this);
      
      ListView list = (ListView)findViewById(R.id.list);
      
      list.setAdapter(adapter);
	  
	  
	}

	@Override
	public void onClickMoins(Numeric item, int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClickPlus(Numeric item, int position) {
		// TODO Auto-generated method stub
		
	}

	

}
