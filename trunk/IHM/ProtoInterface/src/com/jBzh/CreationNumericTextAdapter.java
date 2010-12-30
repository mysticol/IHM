package com.jBzh;

import java.util.ArrayList;
import java.util.List;

import com.jBzh.CreationNumericAdapter.CreationNumericAdapterListener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
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
		        
		//(3) : Renseignement des valeurs
	
		System.out.println("dans le getview : " + String.valueOf(listNumeric.get(position).getValeur()));
		
		valeurNumeric.setText(listNumeric.get(position).getValeur());
		nomNumeric.setText(listNumeric.get(position).getNomNumeric());

		
		//On retourne l'item créé.
		return layoutItem;
	}
	
   /* private ArrayList<CreationNumericAdapterListener> mListListener = new ArrayList<CreationNumericAdapterListener>();
    public void addListener(CreationNumericAdapterListener aListener) {
    	mListListener.add(aListener);
    }
	
    public interface CreationNumericAdapterListener {
    	public void onClickMoins(Numeric item, int position);
    	public void onClickPlus(Numeric item, int position);
    }
    
    private void sendListener(Numeric item, int position, Boolean clicPlus) {
        for(int i = mListListener.size()-1; i >= 0; i--) {
        	if(clicPlus){
        		mListListener.get(i).onClickPlus(item, position);
        	} else {
        		mListListener.get(i).onClickMoins(item, position);
        	}
        }
    }*/

}
