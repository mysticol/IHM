package com.jBzh;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

public class CreationNumericAdapter extends BaseAdapter {
	
	private List<Numeric> listNumeric;
	
	//Le contexte dans lequel est pr�sent notre adapter
	private Context mContext;
	    	
	//Un m�canisme pour g�rer l'affichage graphique depuis un layout XML
	private LayoutInflater mInflater;
	
	// Constructeur
	public CreationNumericAdapter(Context context, List<Numeric> listNum) {
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
		
		//(1) : R�utilisation des layouts
		if (convertView == null) {
			//Initialisation de notre item � partir du  layout XML "personne_layout.xml"
		    layoutItem = (LinearLayout) mInflater.inflate(R.layout.numeric_layout, parent, false);
		} else {
		  	layoutItem = (LinearLayout) convertView;
		}
		  
		//(2) : R�cup�ration des TextView de notre layout      

		        
		//(3) : Renseignement des valeurs       

		  
		//(4) Changement de la couleur du fond de notre item

		
		//On retourne l'item cr��.
		return layoutItem;
	}
	
    private ArrayList<CreationNumericAdapterListener> mListListener = new ArrayList<CreationNumericAdapterListener>();
    public void addListener(CreationNumericAdapterListener aListener) {
    	mListListener.add(aListener);
    }
	
    public interface CreationNumericAdapterListener {
    	public void onClickMoins(Numeric item, int position);
    	public void onClickPlus(Numeric item, int position);
    }

}
