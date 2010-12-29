package com.jBzh;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CreationNumericAdapter extends BaseAdapter {
	
	private List<Numeric> listNumeric;
	
	//Le contexte dans lequel est présent notre adapter
	private Context mContext;
	    	
	//Un mécanisme pour gérer l'affichage graphique depuis un layout XML
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
	public Object getItem(int arg0) {
		return listNumeric.get(arg0);
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		return null;
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
