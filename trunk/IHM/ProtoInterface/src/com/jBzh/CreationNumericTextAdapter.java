package com.jBzh;

import java.util.ArrayList;
import java.util.List;

import com.jBzh.CreationNumericAdapter.CreationNumericAdapterListener;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CreationNumericTextAdapter extends BaseAdapter{

private List<NumericText> listNumeric;
	
	//Le contexte dans lequel est présent notre adapter
	private Context mContext;
	    	
	//Un mécanisme pour gérer l'affichage graphique depuis un layout XML
	private LayoutInflater mInflater;
	
	// Constructeur
	public CreationNumericTextAdapter(Context context, List<NumericText> listNum) {
		listNumeric = listNum;
		mContext = context;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return listNumeric.size();
	}

	@Override
	public Object getItem(int position) {
		return listNumeric.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout layoutItem;
		
		//(1) : Réutilisation des layouts
		if (convertView == null) {
			//Initialisation de notre item à partir du  layout XML "personne_layout.xml"
		    layoutItem = (LinearLayout) mInflater.inflate(R.layout.numerictext_layout, parent, false);
		} else {
		  	layoutItem = (LinearLayout) convertView;
		}
		  
		//(2) : Récupération de la valeur du numericText de notre layout      
		EditText valeurNumeric = (EditText) layoutItem.findViewById(R.id.ValeurNumericText);
		TextView nomNumeric = (TextView) layoutItem.findViewById(R.id.NomNumericText);
		        
		//(3) : Rens eignement des valeurs
	
		System.out.println("dans le getview : " + String.valueOf(listNumeric.get(position).getValeur()));
		
		valeurNumeric.setText(listNumeric.get(position).getValeur());
		nomNumeric.setText(listNumeric.get(position).getNomNumeric());

		/*valeurNumeric.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
				Integer position = (Integer)v.getTag();
				//On prévient les listeners qu'il y a eu un clic sur l'EditText.
				sendListener(listNumeric.get(position), position, true);
				v.invalidate();
				}
			}
		});*/
		valeurNumeric.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// If the event is a key-down event on the "enter" button
		        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
		            (keyCode == KeyEvent.KEYCODE_ENTER)) {
		        	
		        	Integer position = (Integer)v.getTag();
		        	
					//On prévient les listeners qu'il y a eu un clic sur l'EditText.
					sendListener(listNumeric.get(position), position, true);
					v.invalidate();
					return true;
		        }
		        return false;
			}
		});
		
		return layoutItem;
		
	}
	
    private ArrayList<CreationNumericTextAdapterListener> mListListener = new ArrayList<CreationNumericTextAdapterListener>();
    public void addListener(CreationNumericTextAdapterListener aListener) {
    	mListListener.add(aListener);
    }
	
    public interface CreationNumericTextAdapterListener {
    	public void onTextChanged(NumericText item, int position);
    }
    
    private void sendListener(NumericText item, int position, Boolean clicPlus) {
        for(int i = mListListener.size()-1; i >= 0; i--) {
        		mListListener.get(i).onTextChanged(item, position);
        }
    }

}
