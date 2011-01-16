package com.jBzh;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResumeAdapter extends BaseAdapter {
	
	private List<ResumNumeric> list;
	
	//Le contexte dans lequel est présent notre adapter
	private Context mContext;
	    	
	//Un mécanisme pour gérer l'affichage graphique depuis un layout XML
	private LayoutInflater mInflater;
	
	// Constructeur
	public ResumeAdapter(Context mContext, List<ResumNumeric> list) {
		super();
		this.list = list;
		this.mContext = mContext;
		this.mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return list.size();
	}


	@Override
	public Object getItem(int position) {
		return list.get(position);
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
			//Initialisation de notre item à partir du  layout XML "resume_layout.xml"
		    layoutItem = (LinearLayout) mInflater.inflate(R.layout.resume_layout, parent, false);
		} else {
		  	layoutItem = (LinearLayout) convertView;
		}
		  
		//(2) : Récupération de la valeur du numeric de notre layout      
		TextView resumNom = (TextView) layoutItem.findViewById(R.id.ResumNom);
		TextView resumValeur = (TextView) layoutItem.findViewById(R.id.ResumValeur);
		        
		//(3) : Renseignement des valeurs
		resumNom.setText(list.get(position).getResumNom());
		resumValeur.setText(list.get(position).getResumValeur());
		
		
		//On retourne l'item créé
		return layoutItem;
	}
	
}
