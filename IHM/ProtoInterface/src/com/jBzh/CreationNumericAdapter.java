package com.jBzh;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
		  
		//(2) : R�cup�ration de la valeur du numeric de notre layout      
		TextView valeurNumeric = (TextView) layoutItem.findViewById(R.id.ValeurNumeric);
		TextView nomNumeric = (TextView) layoutItem.findViewById(R.id.NomNumeric);
		        
		//(3) : Renseignement des valeurs
	
		System.out.println("dans le getview : " + String.valueOf(listNumeric.get(position).getValeur()));
		
		valeurNumeric.setText(String.valueOf(listNumeric.get(position).getValeur()));
		nomNumeric.setText(listNumeric.get(position).getNomNumeric());
		
		// On ajoute les listeners
		Button plus = (Button) layoutItem.findViewById(R.id.Plus);
		Button moins = (Button) layoutItem.findViewById(R.id.Moins);
		
		plus.setTag(position);
		moins.setTag(position);
		
		plus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Lorsque l'on clique sur le nom, on r�cup�re la position de la "Personne"
				Integer position = (Integer)v.getTag();
						
				//On pr�vient les listeners qu'il y a eu un clic sur le TextView "TV_Nom".
				sendListener(listNumeric.get(position), position, true);
				v.invalidate();
			}
		});
		
		moins.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Lorsque l'on clique sur le nom, on r�cup�re la position de la "Personne"
				Integer position = (Integer)v.getTag();
						
				//On pr�vient les listeners qu'il y a eu un clic sur le TextView "TV_Nom".
				sendListener(listNumeric.get(position), position, false);
				v.invalidate();
			}
		});		
		
		
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
    
    private void sendListener(Numeric item, int position, Boolean clicPlus) {
        for(int i = mListListener.size()-1; i >= 0; i--) {
        	if(clicPlus){
        		mListListener.get(i).onClickPlus(item, position);
        	} else {
        		mListListener.get(i).onClickMoins(item, position);
        	}
        }
    }

}
