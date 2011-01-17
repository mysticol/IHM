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
import bean.Caracteristique;

public class CreationJaugeAdapter extends BaseAdapter {

	private List<Caracteristique> listJauge;
	
	//Le contexte dans lequel est pr�sent notre adapter
	private Context mContext;
	    	
	//Un m�canisme pour g�rer l'affichage graphique depuis un layout XML
	private LayoutInflater mInflater;
	
	// Constructeur
	public CreationJaugeAdapter(Context context, List<Caracteristique> listJauge) {
		this.listJauge = listJauge;
		mContext = context;
		mInflater = LayoutInflater.from(mContext);
	}

		
	@Override
	public int getCount() {
		return listJauge.size();
	}

	@Override
	public Object getItem(int arg0) {
		return listJauge.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout layoutItem;
		
		//(1) : R�utilisation des layouts
		if (convertView == null) {
			//Initialisation de notre item � partir du  layout XML "personne_layout.xml"
		    layoutItem = (LinearLayout) mInflater.inflate(R.layout.jaugelayout, parent, false);
		} else {
		  	layoutItem = (LinearLayout) convertView;
		}
		
		//(2) : Récupération de la valeur du numeric de notre layout   
		TextView nomJauge = (TextView) layoutItem.findViewById(R.id.NomJauge);
		TextView valeurJauge = (TextView) layoutItem.findViewById(R.id.ValeurJauge);
		TextView maxJauge = (TextView) layoutItem.findViewById(R.id.MaxJauge);
		
		//(3) : Renseignement des valeurs
		nomJauge.setText(listJauge.get(position).getNom() + " : ");
		valeurJauge.setText(String.valueOf(listJauge.get(position).getValeur()));
		maxJauge.setText(String.valueOf(listJauge.get(position).getMaximum()));
		
		// On ajoute les listeners
		Button plus = (Button) layoutItem.findViewById(R.id.JaugePlus);
		Button moins = (Button) layoutItem.findViewById(R.id.JaugeMoins);
		
		plus.setTag(position);
		moins.setTag(position);
		
		plus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Lorsque l'on clique sur le nom, on r�cup�re la position de la "Personne"
				Integer position = (Integer)v.getTag();
						
				//On prévient les listeners qu'il y a eu un clic sur le TextView "TV_Nom".
				sendListener(listJauge.get(position), position, true);
				v.invalidate();
			}
			
		});
		
		moins.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Lorsque l'on clique sur le nom, on r�cup�re la position de la "Personne"
				Integer position = (Integer)v.getTag();
						
				//On prévient les listeners qu'il y a eu un clic sur le TextView "TV_Nom".
				sendListener(listJauge.get(position), position, false);
				v.invalidate();
			}
		});	
				
		//On retourne l'item créé.
		return layoutItem;
	}

	private ArrayList<CreationJaugeAdapterListener> mListListener = new ArrayList<CreationJaugeAdapterListener>();
    public void addListener(CreationJaugeAdapterListener aListener) {
    	mListListener.add(aListener);
    }
	
    public interface CreationJaugeAdapterListener {
    	public void onClickMoins(Caracteristique item, int position);
    	public void onClickPlus(Caracteristique item, int position);
    }
    
    private void sendListener(Caracteristique item, int position, Boolean clicPlus) {
        for(int i = mListListener.size()-1; i >= 0; i--) {
        	if(clicPlus){
        		mListListener.get(i).onClickPlus(item, position);
        	} else {
        		mListListener.get(i).onClickMoins(item, position);
        	}
        }
    }

}
