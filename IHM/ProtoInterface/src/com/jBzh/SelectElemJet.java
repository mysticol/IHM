package com.jBzh;

import java.util.LinkedList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import bean.Competence;
import bean.Fiche;
import bean.Systeme;

public class SelectElemJet extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.selectelemjet);
	  
	  ListView lv1;
	  final Bundle objetbunble  = this.getIntent().getExtras();
	  final Integer numElem = objetbunble.getInt("numElem");
	  final Systeme sys = (Systeme) objetbunble.getSerializable("systeme");
	  final Fiche fiche = (Fiche) objetbunble.getSerializable("fiche");
	  
	  final String typeJet = objetbunble.getString("type").split(" ")[0];
	  
	  String elmeType = sys.getRoll(typeJet).getElems().get(numElem);
	  final String lv_arr[];
	  final Integer listeVal[];
	  int i = 0;
	  
	  // on recherche les type d'element a afficher
	  if(elmeType.equalsIgnoreCase("caracP")){
		  System.out.println("<<<<<< caracP >>>>>>");
		  lv_arr = new String[fiche.getCaracteristiquesPrincipales().keySet().size()+1];
		  listeVal = new Integer[fiche.getCaracteristiquesPrincipales().keySet().size()+1];
		  for(String s : fiche.getCaracteristiquesPrincipales().keySet()){
			  listeVal[i] = fiche.getCaracteristiquesPrincipales().get(s).getValeur();
			  lv_arr[i++] = s;
		  }
	  }else if(elmeType.equalsIgnoreCase("caracS")){
		  System.out.println("<<<<<< caracS >>>>>>");
		  lv_arr = new String[fiche.getCaracteristiquesSecondaire().keySet().size()+1];
		  listeVal = new Integer[fiche.getCaracteristiquesSecondaire().keySet().size()+1];
		  
		  for(String s : fiche.getCaracteristiquesSecondaire().keySet()){
			  listeVal[i] = fiche.getCaracteristiquesSecondaire().get(s).getValeur();
			  lv_arr[i++] = s;
		  }
	  }else if(elmeType.equalsIgnoreCase("comp")){
		  System.out.println("<<<<<< comp >>>>>>");
		  LinkedList<Competence> lcomp = fiche.getListCompetences();
		  lv_arr = new String[lcomp.size()+1];
		  listeVal = new Integer[lcomp.size()+1];
		  for(Competence c : lcomp){
			  listeVal[i] = c.getValeur();
			  lv_arr[i++] = c.getNom();
		  }
	  }else{
		  System.out.println("<<<<<< Mauvais Systeme >>>>>>");
		  lv_arr = null;
		  listeVal = null;
	  }
	  lv_arr[i] = "Défaut";
	  listeVal[i] = 0 ;
	  	  
	  ((TextView)findViewById(R.id.affichejet)).setText(objetbunble.getString("type"));
	  
	  lv1=(ListView)findViewById(R.id.listelemjet);
	  // By using setAdpater method in listview we an add string array in list.
	  lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , lv_arr));

	  lv1.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view,
	        int position, long id) {
	    		System.out.println(">>>>>> clic sur " + lv_arr[position]);
	    	//On cr�� un objet Bundle, c'est ce qui va nous permetre d'envoyer des donn�es � l'autre Activity
  			//Bundle objetbunble = new Bundle();
   
    		objetbunble.putString("type", objetbunble.getString("type") + lv_arr[position]);
  			objetbunble.putInt("numElem", numElem+1);
  			objetbunble.putInt("nbDice",objetbunble.getInt("nbDice") + listeVal[position]);
  			
  			//On cr�� l'Intent qui va nous permettre d'afficher l'autre Activity
  			Intent intent;
  			if(numElem+1 < sys.getRoll(typeJet).getElems().size()){
  				objetbunble.putString("type", objetbunble.getString("type") + " + ");
  				intent = new Intent(SelectElemJet.this,SelectElemJet.class);
  			}else{
  				objetbunble.putString("diceType", sys.getRoll(typeJet).getDice().toString());
  				intent = new Intent(SelectElemJet.this,DiceLauncher.class);
  			}

  			//On affecte � l'Intent le Bundle que l'on a cr��
  			intent.putExtras(objetbunble);
   
  			//On d�marre l'autre Activity
  			startActivityForResult(intent, 1);

	    }
	  });
	  
      final Button buttonRetour = (Button) findViewById(R.id.retour);
      buttonRetour.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
          	 
  			//On cr�� l'Intent qui va nous permettre d'afficher l'autre Activity
  			Intent intent = new Intent(SelectElemJet.this, ChoixJet.class);
   
  			//On affecte � l'Intent le Bundle que l'on a cr��
  			intent.putExtras(objetbunble);
   
  			//On d�marre l'autre Activity
  			startActivityForResult(intent, 1);

          }
      });
	
	}	
}
