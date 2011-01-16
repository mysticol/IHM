package com.jBzh;

import java.util.ArrayList;
import java.util.List;

import com.jBzh.CreationNumericAdapter.CreationNumericAdapterListener;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EditionNumericTextAdapter2 extends BaseAdapter{

private List<NumericText> listNumeric;
private Integer firstTime;
	
	//Le contexte dans lequel est pr�sent notre adapter
	private Context mContext;
	    	
	//Un m�canisme pour g�rer l'affichage graphique depuis un layout XML
	private LayoutInflater mInflater;
	
	// Constructeur
	public EditionNumericTextAdapter2(Context context, List<NumericText> listNum) {
		listNumeric = listNum;
		mContext = context;
		mInflater = LayoutInflater.from(mContext);
		firstTime = listNumeric.size();
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
		    layoutItem = (LinearLayout) mInflater.inflate(R.layout.numerictext_layout2, parent, false);
		} else {
		  	layoutItem = (LinearLayout) convertView;
		}
		  
		//(2) : R�cup�ration de la valeur du numericText de notre layout      
		EditText valeurNumeric = (EditText) layoutItem.findViewById(R.id.ValeurNumericText);
		EditText nomNumeric = (EditText) layoutItem.findViewById(R.id.NomNumericText);
		        
		//(3) : Rens eignement des valeurs
		
		valeurNumeric.setTag(position);
		nomNumeric.setTag(position);
	
		//System.out.println("dans le getview : " + String.valueOf(listNumeric.get(position).getValeur()));
		//System.out.println("CreationNumTextAdapter : " + listNumeric.get(position).getNomNumeric() + " => " + listNumeric.get(position).getValeur());
			
		/*if(!valeurNumeric.getEditableText().toString().equalsIgnoreCase(listNumeric.get(position).getValeur())){
			listNumeric.get(position).setValeur(valeurNumeric.getEditableText().toString());
		}*/
		if(listNumeric.get(position).getValeur()!=null){
			valeurNumeric.setText(listNumeric.get(position).getValeur());
		}
		if(listNumeric.get(position).getNomNumeric()!=null){
			nomNumeric.setText(listNumeric.get(position).getNomNumeric());
		}	
		
		valeurNumeric.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// If the event is a key-down event on the "enter" button
		        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
		            (keyCode == KeyEvent.KEYCODE_ENTER)) {
		        	
		        	Integer position = (Integer)v.getTag();
		        	
		        	listNumeric.get(position).setValeur(((EditText)v).getText().toString());
		        	
					//On pr�vient les listeners qu'il y a eu un clic sur l'EditText.
					sendListener(listNumeric.get(position), position);
					v.invalidate();
					return true;
		        }
		        return false;
			}
		});
		
		nomNumeric.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// If the event is a key-down event on the "enter" button
		        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
		            (keyCode == KeyEvent.KEYCODE_ENTER)) {
		        	
		        	Integer position = (Integer)v.getTag();
		        	
		        	listNumeric.get(position).setNomNumeric(((EditText)v).getText().toString());
		        	
					//On pr�vient les listeners qu'il y a eu un clic sur l'EditText.
					sendListener(listNumeric.get(position), position);
					v.invalidate();
					return true;
		        }
		        return false;
			}
		});
		
		return layoutItem;
		
	}
	
    private ArrayList<EditionNumericTextAdapterListener2> mListListener = new ArrayList<EditionNumericTextAdapterListener2>();
    public void addListener(EditionNumericTextAdapterListener2 aListener) {
    	mListListener.add(aListener);
    }
	
    public interface EditionNumericTextAdapterListener2 {
    	public void onTextChanged2(NumericText item, int position);
    }
    
    private void sendListener(NumericText item, int position) {
        for(int i = mListListener.size()-1; i >= 0; i--) {
        		mListListener.get(i).onTextChanged2(item, position);
        }
    }

}
