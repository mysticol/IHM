package com.jBzh;

import java.util.ArrayList;

import com.jBzh.CreationNumericAdapter.CreationNumericAdapterListener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class RemplissageCreation extends Activity implements CreationNumericAdapterListener{
	
	private static ArrayList<Numeric> listN = Numeric.getAListOfNumeric();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  
	  setContentView(R.layout.remplissagecreation);

      CreationNumericAdapter adapter = new CreationNumericAdapter(this, listN);
     
      adapter.addListener(this);
      
      ListView list = (ListView)findViewById(R.id.list);
      
      list.setAdapter(adapter);
	  
	  
	}

	@Override
	public void onClickMoins(Numeric item, int position) {
		item.decr();
		System.out.println(item.getValeur());
	}

	@Override
	public void onClickPlus(Numeric item, int position) {
		item.incr();
		System.out.println(item.getValeur());
	}

	

}
